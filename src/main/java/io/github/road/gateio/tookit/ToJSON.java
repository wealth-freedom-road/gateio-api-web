package io.github.road.gateio.tookit;

import com.alibaba.fastjson.JSON;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
public interface ToJSON {


    default String ToJSON() {
        return JSON.toJSONString(this);
    }
}
