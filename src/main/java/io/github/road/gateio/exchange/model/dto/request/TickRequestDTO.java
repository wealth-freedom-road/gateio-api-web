package io.github.road.gateio.exchange.model.dto.request;

import io.github.road.gateio.tookit.ToJSON;
import lombok.Data;

/**
 * 获取Ticker信息
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class TickRequestDTO implements ToJSON {

    private String market; //交易对

}
