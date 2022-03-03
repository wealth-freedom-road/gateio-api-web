package io.github.road.gateio.exchange.model.dto.result;

import io.github.road.gateio.tookit.ToJSON;
import java.math.BigDecimal;
import lombok.Data;

/**
 * TICKER信息
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class TickResultDTO implements ToJSON {

    private BigDecimal last;
    private BigDecimal baseVolume;
    private BigDecimal quoteVolume;
    private BigDecimal high24h;
    private BigDecimal low24h;
    private BigDecimal highestBid;
    private BigDecimal lowestAsk;
    private String currencyPair;
    private String changePercentage;
    private BigDecimal todayChangePercentage;

}
