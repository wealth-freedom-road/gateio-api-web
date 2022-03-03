package io.github.road.gateio.exchange.model.dto.request;

import io.github.road.gateio.exchange.model.enums.SideEnum;
import io.github.road.gateio.exchange.model.enums.TimeInForceEnum;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 挂现货单
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class CreateSpotOrderRequestDTO {

    private String market; //btc_usdt
    private BigDecimal price; //委托价格
    private BigDecimal tokenAmt;//委托token数量
    private SideEnum sideEnum; //委托方向
    private TimeInForceEnum timeInForceEnum; //委托策略
    private String text;

}
