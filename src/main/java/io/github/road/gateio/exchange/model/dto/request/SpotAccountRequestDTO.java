package io.github.road.gateio.exchange.model.dto.request;

import io.github.road.gateio.tookit.ToJSON;
import lombok.Data;

/**
 * 现货账户查询
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class SpotAccountRequestDTO implements ToJSON {

    //USDT ETH 不是交易对
    private String currency;
}
