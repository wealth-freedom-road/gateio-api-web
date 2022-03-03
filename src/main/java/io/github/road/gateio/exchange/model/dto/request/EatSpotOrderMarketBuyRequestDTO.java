package io.github.road.gateio.exchange.model.dto.request;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 市价吃单
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class EatSpotOrderMarketBuyRequestDTO {

    private String market; //btc_usdt
    private BigDecimal usdtAmt; //总金额
    private String text;
    private Integer retryMax = 10; //重试次数
    /**
     * 滑点
     */
    private BigDecimal slipPoint = BigDecimal.ONE.divide(new BigDecimal(1000));



}
