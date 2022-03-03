package io.github.road.gateio.api;

import cn.hutool.core.lang.reflect.MethodHandleUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.road.gateio.exchange.extension.GateApiExtension;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Slf4j
@RestController
public class ExchangeApi {


    private Map<String, String> methodNameInTable = Maps.newConcurrentMap();
    private Map<String, Method> methodTable = Maps.newConcurrentMap();
    private Map<String, String> methodNameOutTable = Maps.newConcurrentMap();


    @PostMapping("router/methods")
    public String methods() {
        List<Map<String, String>> ret = Lists.newArrayList();
        methodNameInTable.forEach((methodName, inType) -> {
            HashMap<String, String> one = Maps.newHashMap();
            one.put("methodName", methodName);
            one.put("inType", inType);
            one.put("outType", methodNameOutTable.get(methodName));
            ret.add(one);
        });
        return JSON.toJSONString(ret, true);
    }


    @PostMapping("router/{methodName}")
    public String hello(@PathVariable("methodName") String methodName, @RequestParam("body") String body)
            throws ClassNotFoundException {

        //出入参类型
        String inType = methodNameInTable.get(methodName);
        String outType = methodNameOutTable.get(methodName);

        log.info("入参类型，[{}][{}][{}]", methodName, inType, outType);

        Method method = methodTable.get(methodName);
        if (method == null) {
            return "不存在的方法";
        }
        GateApiExtension extension = GateApiExtension.create();

        Object inParam;
        Object ret;

        //说明是实体
        if (StringUtils.startsWith(inType, "io.github.road.gateio.exchange.model.dto")) {
            inParam = JSON.parseObject(body, Class.forName(inType));
            ret = MethodHandleUtil.invoke(extension, method, inParam);
        } else if (StringUtils.equals(inType, "no_in_param")) {
            //没有入参
            ret = MethodHandleUtil.invoke(extension, method);
        } else {
            //基本类型
            inParam = body;
            ret = MethodHandleUtil.invoke(extension, method, inParam);
        }
        log.info("返回，[{}][{}][{}]", methodName, body, ret);

        //如果是基本类型，返回字符串   //否则返回json
        if (StringUtils.startsWith(outType, "io.github.road.gateio.exchange.model.dto")) {
            return JSON.toJSONString(ret);
        } else {
            return ret.toString();
        }
    }


    @PostConstruct
    private void init() {
        Class<GateApiExtension> extensionClass = GateApiExtension.class;
        Method[] methods = extensionClass.getMethods();

        List<String> excludes = Lists
                .newArrayList("create", "auth", "wait", "equals", "toString", "hashCode", "getClass", "notify", "notifyAll");

        for (Method method : methods) {
            String methodName = method.getName();
            if (excludes.contains(methodName)) {
                continue;
            }

            if (method.getModifiers() == Modifier.PRIVATE) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            String in = "no_in_param";
            if (parameterTypes.length != 0) {
                in = parameterTypes[0].getName();
            }
            String returnTypeName = method.getReturnType().getName();
            methodNameInTable.putIfAbsent(methodName, in);
            methodNameOutTable.putIfAbsent(methodName, returnTypeName);
            methodTable.putIfAbsent(methodName, method);
        }
        log.info("路由初始化完毕。");
    }


}