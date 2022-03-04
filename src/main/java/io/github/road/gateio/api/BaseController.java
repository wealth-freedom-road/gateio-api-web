package io.github.road.gateio.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.road.gateio.exchange.model.dto.ExchangeUserSecretDTO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
public class BaseController {


    protected ExchangeUserSecretDTO get(String uid) throws IOException {

        String path = uid + "_monkey.txt";

        String s = FileUtils.readFileToString(new File(path), Charset.defaultCharset());

        JSONObject jsonObject = JSON.parseObject(s);

        ExchangeUserSecretDTO secretDTO = new ExchangeUserSecretDTO();
        secretDTO.setKey(jsonObject.getString("apiKey"));
        secretDTO.setSecret(jsonObject.getString("secretKey"));

        return secretDTO;
    }
}
