package io.github.road.gateio.exchange.model.dto.result;

import io.github.road.gateio.exchange.model.enums.OrderStatusEnum;
import io.github.road.gateio.tookit.ToJSON;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 挂单结果
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class CreateSpotOrderResultDTO implements ToJSON {


    //状态
    private OrderStatusEnum orderStatusEnum;
    //订单id
    private String orderId;
    //手续费
    private BigDecimal fee;
    //已成交金额
    private BigDecimal fillTotal;
    //成交价格
    private BigDecimal price;
    //成交
    private BigDecimal amount;
    //订单自定义信息
    private String text;


}
