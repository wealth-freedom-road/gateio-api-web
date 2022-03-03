package io.github.road.gateio.exchange.model.dto.request;

import lombok.Data;

/**
 * 盘口深度
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Data
public class ListOrderBookRequestDTO {

    private String market;
    private String interval = "0"; // 合并深度指定的价格精度，0 为不合并，不指定则默认为 0
    private Integer limit = 10;  // 深度档位数量
    private boolean withId = false; //是否返回订单簿ID

}
