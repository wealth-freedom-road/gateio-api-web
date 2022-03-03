package io.github.road.gateio.exchange.model.dto.result;

import io.github.road.gateio.tookit.ToJSON;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 市价吃单
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class EatSpotOrderMarketSellResultDTO implements ToJSON {

    private String orderId;

    //实际成交金额,减去手续费
    private BigDecimal realAmount;
    //成交价格
    private BigDecimal price;
}
