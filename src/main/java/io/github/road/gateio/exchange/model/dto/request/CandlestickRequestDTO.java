package io.github.road.gateio.exchange.model.dto.request;

import io.github.road.gateio.exchange.model.enums.CandlesticksIntervalEnum;
import io.github.road.gateio.tookit.ToJSON;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 蜡烛图
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class CandlestickRequestDTO implements ToJSON {

    private String market;
    private CandlesticksIntervalEnum intervalEnum;
    private Integer limit;
    private LocalDateTime from;
    private LocalDateTime to;

}
