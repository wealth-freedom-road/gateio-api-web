package io.github.road.gateio.api;

import io.github.road.gateio.exchange.extension.GateApiExtension;
import io.github.road.gateio.exchange.model.dto.request.CandlestickRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.EatSpotOrderMarketBuyRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.EatSpotOrderMarketSellRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.PrevCandlestickRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.SpotPriceTriggeredOrderRequestDTO;
import io.github.road.gateio.exchange.model.dto.result.CancelSpotPriceTriggeredOrderResultDTO;
import io.github.road.gateio.exchange.model.dto.result.CandlestickResultDTO;
import io.github.road.gateio.exchange.model.dto.result.CurrencyPairResultDTO;
import io.github.road.gateio.exchange.model.dto.result.EatSpotOrderMarketBuyResultDTO;
import io.github.road.gateio.exchange.model.dto.result.EatSpotOrderMarketSellResultDTO;
import io.github.road.gateio.exchange.model.dto.result.SpotAccountResultDTO;
import io.github.road.gateio.exchange.model.dto.result.SpotPriceTriggeredOrderResultDTO;
import io.github.road.gateio.exchange.model.dto.result.TickResultDTO;
import io.github.road.gateio.exchange.model.enums.CandlesticksIntervalEnum;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
@Slf4j
@RestController
public class OpenApiController extends BaseController {


    /**
     * 查询现货账户
     */
    @PostMapping("spotAccountMust")
    public SpotAccountResultDTO spotAccountMust(@RequestHeader("uid") String uid, String currency) throws IOException {
        return GateApiExtension.auth(get(uid)).spotAccountMust(currency);
    }

    /**
     * 创建现货触发订单
     */
    @PostMapping("createSpotPriceTriggeredOrderMust")
    public SpotPriceTriggeredOrderResultDTO createSpotPriceTriggeredOrderMust(@RequestHeader("uid") String uid,
            SpotPriceTriggeredOrderRequestDTO request) throws IOException {
        return GateApiExtension.auth(get(uid)).createSpotPriceTriggeredOrderMust(request);
    }

    /**
     * 创建现货触发订单
     */
    @PostMapping("cancelSpotPriceTriggeredOrderMust")
    public CancelSpotPriceTriggeredOrderResultDTO cancelSpotPriceTriggeredOrderMust(@RequestHeader("uid") String uid, String orderId)
            throws IOException {
        return GateApiExtension.auth(get(uid)).cancelSpotPriceTriggeredOrderMust(orderId);
    }

    /**
     * 市价吃单（全部成交）
     */
    @PostMapping("eatSpotOrderMarketMustOrNull")
    public EatSpotOrderMarketBuyResultDTO eatSpotOrderMarketMustOrNull(@RequestHeader("uid") String uid,
            EatSpotOrderMarketBuyRequestDTO request) throws IOException {
        return GateApiExtension.auth(get(uid)).eatSpotOrderMarketMustOrNull(request);
    }


    /**
     * 市价卖出（全部成交）
     */
    @PostMapping("marketSellMustOrNull")
    public EatSpotOrderMarketSellResultDTO marketSellMustOrNull(@RequestHeader("uid") String uid,
            EatSpotOrderMarketSellRequestDTO request) throws IOException {
        return GateApiExtension.auth(get(uid)).marketSellMustOrNull(request);
    }


    //
    // 下面的不需要鉴权
    //

    /**
     * 查询单个币种信息
     */
    @PostMapping("currencyPairsMust")
    public CurrencyPairResultDTO currencyPairsMust(String pair) {
        return GateApiExtension.create().currencyPairsMust(pair);
    }

    /**
     * 查询所有币种信息
     */
    @PostMapping("listCurrencyPairsMust")
    public List<CurrencyPairResultDTO> listCurrencyPairsMust() {
        return GateApiExtension.create().listCurrencyPairsMust();
    }


    /**
     * 获取单个Ticker信息
     */
    @PostMapping("getTickerMust")
    public TickResultDTO getTickerMust(String market) {
        return GateApiExtension.create().getTickerMust(market);
    }

    /**
     * 获取所有Ticker信息
     */
    @PostMapping("getTickersMust")
    public List<TickResultDTO> getTickersMust() {
        return GateApiExtension.create().getTickersMust();
    }


    /**
     * 获取蜡烛图
     */
    @PostMapping("listCandlestickMust")
    public List<CandlestickResultDTO> listCandlestickMust(CandlestickRequestDTO request) {
        return GateApiExtension.create().listCandlestickMust(request);
    }


    /**
     * 获取最新的蜡烛图，一根K线
     */
    @PostMapping("candlestickRecentMust")
    public CandlestickResultDTO candlestickRecentMust(String market, String intervalValue) {
        return GateApiExtension.create().candlestickRecentMust(market, CandlesticksIntervalEnum.toEnum(intervalValue));
    }

    /**
     * 获取上一个周期的蜡烛图
     */
    @PostMapping("prevCandlestickMust")
    public CandlestickResultDTO prevCandlestickMust(PrevCandlestickRequestDTO request) {
        return GateApiExtension.create().prevCandlestickMust(request);
    }


    /**
     * 查询交易对，从0点开始到当前时间的涨幅数据
     */
    @PostMapping("fetchCurrencyTodayChangePercentage")
    public BigDecimal fetchCurrencyTodayChangePercentage(String market) {
        return GateApiExtension.create().fetchCurrencyTodayChangePercentage(market);
    }


    /**
     * 今日0点的第一个k线，获取今日开盘价
     */
    @PostMapping("fetchTodayFirstCandlestick")
    public CandlestickResultDTO fetchTodayFirstCandlestick(String market) {
        return GateApiExtension.create().fetchTodayFirstCandlestick(market);
    }

    /**
     * 获取单个监控周期内,振幅百分比
     */
    @PostMapping("getSingleMonitoringCycleChangePercentage")
    public BigDecimal getSingleMonitoringCycleChangePercentage(CandlestickRequestDTO request) {
        return GateApiExtension.create().getSingleMonitoringCycleChangePercentage(request);
    }

}
