package io.github.road.gateio.exchange.model.dto.request;

import io.github.road.gateio.tookit.ToJSON;
import lombok.Data;

/**
 * 查询币种信息
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class CurrencyPairRequestDTO implements ToJSON {

    private String pair;

}
