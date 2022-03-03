package io.github.road.gateio.exchange.extension;

import com.alibaba.fastjson.JSON;
import io.github.road.gateio.exchange.client.GateApi;
import io.github.road.gateio.exchange.exception.ExchangeApiException;
import io.github.road.gateio.exchange.exception.ExchangeResultCodeEnum;
import io.github.road.gateio.exchange.model.dto.ExchangeUserSecretDTO;
import io.github.road.gateio.exchange.model.dto.Result;
import io.github.road.gateio.exchange.model.dto.request.CancelSpotPriceTriggeredOrderRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.CandlestickRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.CreateSpotOrderRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.CurrencyPairRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.EatSpotOrderMarketBuyRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.EatSpotOrderMarketSellRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.ListOrderBookRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.PrevCandlestickRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.SpotAccountRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.SpotOderQueryRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.SpotPriceTriggeredOrderRequestDTO;
import io.github.road.gateio.exchange.model.dto.request.TickRequestDTO;
import io.github.road.gateio.exchange.model.dto.result.CancelSpotPriceTriggeredOrderResultDTO;
import io.github.road.gateio.exchange.model.dto.result.CandlestickResultDTO;
import io.github.road.gateio.exchange.model.dto.result.CreateSpotOrderResultDTO;
import io.github.road.gateio.exchange.model.dto.result.CurrencyPairResultDTO;
import io.github.road.gateio.exchange.model.dto.result.EatSpotOrderMarketBuyResultDTO;
import io.github.road.gateio.exchange.model.dto.result.EatSpotOrderMarketSellResultDTO;
import io.github.road.gateio.exchange.model.dto.result.ListOrderBookResultDTO;
import io.github.road.gateio.exchange.model.dto.result.ListOrderBookResultDTO.AskDTO;
import io.github.road.gateio.exchange.model.dto.result.ListOrderBookResultDTO.BidDTO;
import io.github.road.gateio.exchange.model.dto.result.SpotAccountResultDTO;
import io.github.road.gateio.exchange.model.dto.result.SpotPriceTriggeredOrderResultDTO;
import io.github.road.gateio.exchange.model.dto.result.TickResultDTO;
import io.github.road.gateio.exchange.model.enums.CandlesticksIntervalEnum;
import io.github.road.gateio.exchange.model.enums.OrderStatusEnum;
import io.github.road.gateio.exchange.model.enums.SideEnum;
import io.github.road.gateio.exchange.model.enums.TimeInForceEnum;
import io.github.road.gateio.tookit.CommonUtils;
import io.github.road.gateio.tookit.CurrencyUtils;
import io.github.road.gateio.tookit.DateUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * GATE交易
 *
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
public class GateApiExtension extends GateApi {

    private GateApiExtension(ExchangeUserSecretDTO userSecretDTO) {
        super(userSecretDTO);
    }

    public static GateApiExtension auth(ExchangeUserSecretDTO userSecretDTO) {
        return new GateApiExtension(userSecretDTO);
    }

    public static GateApiExtension create() {
        return new GateApiExtension(new ExchangeUserSecretDTO(StringUtils.EMPTY, StringUtils.EMPTY));
    }


    /**
     * 查询所有币种信息
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     * https://www.gateio.tv/docs/developers/apiv4/zh_CN/#currencypair
     */
    public CurrencyPairResultDTO currencyPairsMust(String pair) {
        CurrencyPairRequestDTO requestDTO = new CurrencyPairRequestDTO();
        requestDTO.setPair(pair);
        while (true) {
            try {
                return super.currencyPairsCore(requestDTO);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }

    /**
     * 查询所有币种信息
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public List<CurrencyPairResultDTO> listCurrencyPairsMust() {
        while (true) {
            try {
                return super.listCurrencyPairsCore();
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 查询现货账户
     */
    public SpotAccountResultDTO spotAccountMust(String currency) {
        SpotAccountRequestDTO requestDTO = new SpotAccountRequestDTO();
        requestDTO.setCurrency(currency);
        while (true) {
            try {
                return super.spotAccountCore(requestDTO);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 创建现货触发订单
     */
    public Result<SpotPriceTriggeredOrderResultDTO> createSpotPriceTriggeredOrder(SpotPriceTriggeredOrderRequestDTO request) {
        try {
            return Result.success(super.createSpotPriceTriggeredOrderCore(request));
        } catch (ExchangeApiException e) {
            return Result.fail(e.getResultCode());
        }
    }


    /**
     * 创建现货触发订单
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public SpotPriceTriggeredOrderResultDTO createSpotPriceTriggeredOrderMust(SpotPriceTriggeredOrderRequestDTO request) {
        while (true) {
            try {
                return super.createSpotPriceTriggeredOrderCore(request);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 取消现货触发订单
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public CancelSpotPriceTriggeredOrderResultDTO cancelSpotPriceTriggeredOrderMust(String orderId) {
        CancelSpotPriceTriggeredOrderRequestDTO requestDTO = new CancelSpotPriceTriggeredOrderRequestDTO();
        requestDTO.setOrderId(orderId);
        while (true) {
            try {
                return super.cancelSpotPriceTriggeredOrderCore(requestDTO);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 市价吃单（全部成交）
     *
     * <b>到达最大重试次数前都会以市价重试<b>
     */
    public EatSpotOrderMarketBuyResultDTO eatSpotOrderMarketMustOrNull(EatSpotOrderMarketBuyRequestDTO request) {

        String market = request.getMarket();
        CurrencyPairResultDTO pairResultDTO = this.currencyPairsMust(market);
        Integer amountPrecision = pairResultDTO.getAmountPrecision(); //数量精度
        Integer precision = pairResultDTO.getPrecision();  //价格精度

        int cur = 1;
        while (cur <= request.getRetryMax()) {
            //获取最新成交价 用这个计算大概我们要买多少个
            BigDecimal lastPrice = this.getTickerMust(market).getLast();

            CreateSpotOrderRequestDTO orderRequestDTO = new CreateSpotOrderRequestDTO();
            //加一点钱，排到前面去

            orderRequestDTO.setPrice(lastPrice.multiply(BigDecimal.ONE.add(request.getSlipPoint())));
            orderRequestDTO.setTokenAmt(request.getUsdtAmt().divide(orderRequestDTO.getPrice(), amountPrecision, BigDecimal.ROUND_FLOOR));

            BigDecimal priceByTokenAmt = this.getPriceByTokenAmt(market, orderRequestDTO.getTokenAmt(), SideEnum.BUY);
            //重新设置价格
            orderRequestDTO.setPrice(priceByTokenAmt);

            orderRequestDTO.setSideEnum(SideEnum.BUY);
            orderRequestDTO.setTimeInForceEnum(TimeInForceEnum.IOC);
            orderRequestDTO.setMarket(market);
            orderRequestDTO.setText(request.getText());

            Result<CreateSpotOrderResultDTO> spotOrderResponse = this.createSpotOrder(orderRequestDTO);


            if (spotOrderResponse.isSuccess() &&
                    spotOrderResponse.getData().getOrderStatusEnum().equals(OrderStatusEnum.CLOSED)) {

                CreateSpotOrderResultDTO data = spotOrderResponse.getData();
                EatSpotOrderMarketBuyResultDTO resultDTO = new EatSpotOrderMarketBuyResultDTO();
                resultDTO.setTokenAmt(orderRequestDTO.getTokenAmt());
                resultDTO.setPrice(orderRequestDTO.getPrice());
                resultDTO.setMarket(market);
                resultDTO.setOrderId(data.getOrderId());
                //实际的
                resultDTO.setFillTotal(data.getFillTotal());
                //手续费
                resultDTO.setFee(data.getFee());
                resultDTO.setTokenNumber(orderRequestDTO.getTokenAmt().subtract(data.getFee()));
                //成本价
                resultDTO.setCost(data.getFillTotal().divide(orderRequestDTO.getTokenAmt(), precision, BigDecimal.ROUND_HALF_UP));
                //订单自定义信息
                resultDTO.setText(data.getText());
                log.info("最新成交价吃单结束，resultDTO={}", JSON.toJSONString(resultDTO));
                return resultDTO;
            } else {

                //TODO 超时查证 否则会循环下单 !!!
                if (spotOrderResponse.getCode().equals(ExchangeResultCodeEnum.TIMEOUT.getCode())) {
                    log.error("最新成交价吃单异常，请求交易所异常，message={}", spotOrderResponse.getMsg());
                    break;  //县种植
                }

            }

            //获取卖一价
            ListOrderBookRequestDTO requestDTO = new ListOrderBookRequestDTO();
            requestDTO.setMarket(market);
            ListOrderBookResultDTO bookResultDTO = listOrderBookMust(requestDTO);
            AskDTO askDTO = bookResultDTO.getAsks().get(0);
            orderRequestDTO.setPrice(askDTO.getPrice().multiply(BigDecimal.ONE.add(request.getSlipPoint())));
            orderRequestDTO.setTokenAmt(request.getUsdtAmt().divide(orderRequestDTO.getPrice(), amountPrecision, BigDecimal.ROUND_FLOOR));

            spotOrderResponse = this.createSpotOrder(orderRequestDTO);

            if (spotOrderResponse.isSuccess() &&
                    spotOrderResponse.getData().getOrderStatusEnum().equals(OrderStatusEnum.CLOSED)) {

                CreateSpotOrderResultDTO data = spotOrderResponse.getData();

                EatSpotOrderMarketBuyResultDTO resultDTO = new EatSpotOrderMarketBuyResultDTO();
                resultDTO.setTokenAmt(orderRequestDTO.getTokenAmt());
                resultDTO.setPrice(orderRequestDTO.getPrice());
                resultDTO.setMarket(market);
                resultDTO.setOrderId(data.getOrderId());

                //实际的数量
                resultDTO.setFillTotal(data.getFillTotal());
                //手续费
                resultDTO.setFee(data.getFee());
                resultDTO.setTokenNumber(orderRequestDTO.getTokenAmt().subtract(data.getFee()));
                //成本价
                resultDTO.setCost(data.getFillTotal().divide(orderRequestDTO.getTokenAmt(), precision, BigDecimal.ROUND_HALF_UP));
                log.info("卖一价吃单结束，resultDTO={}", JSON.toJSONString(resultDTO));
                return resultDTO;
            } else {
                //TODO 超时查证 否则会循环下单 !!!
                if (spotOrderResponse.getCode().equals(ExchangeResultCodeEnum.TIMEOUT.getCode())) {
                    log.error("卖一价格吃单异常，请求交易所异常，message={}", spotOrderResponse.getMsg());
                    break;  //县种植
                }
            }
            cur++;
        }
        return null;
    }


    /**
     * 根据盘口计算合理的买入卖出价格
     *
     * @param sideEnum 买卖的方向
     */
    private BigDecimal getPriceByTokenAmt(String market, BigDecimal tokenAmt, SideEnum sideEnum) {
        //查询15档行情
        ListOrderBookRequestDTO request = new ListOrderBookRequestDTO();
        request.setMarket(market);
        request.setLimit(15);
        final ListOrderBookResultDTO books = listOrderBookMust(request);
        BigDecimal tokenAmtTotal = BigDecimal.ZERO;
        if (sideEnum.equals(SideEnum.BUY)) {
            for (AskDTO ask : books.getAsks()) {
                tokenAmtTotal = tokenAmtTotal.add(ask.getTokenAmt());
                if (tokenAmtTotal.compareTo(tokenAmt) >= 0) {
                    return ask.getPrice();
                }
            }
            return books.getAsks().get(0).getPrice();
        } else {
            for (BidDTO bid : books.getBids()) {
                tokenAmtTotal = tokenAmtTotal.add(bid.getTokenAmt());
                if (tokenAmtTotal.compareTo(tokenAmt) >= 0) {
                    return bid.getPrice();
                }
            }
            return books.getBids().get(0).getPrice();
        }
    }


    /**
     * 市价卖出（全部成交）
     *
     * <b>到达最大重试次数前都会以市价重试<b>
     */
    public EatSpotOrderMarketSellResultDTO marketSellMustOrNull(EatSpotOrderMarketSellRequestDTO request) {
        int cur = 1;
        String market = request.getMarket();
        while (cur <= request.getRetryMax()) {


            CreateSpotOrderRequestDTO orderRequestDTO = new CreateSpotOrderRequestDTO();

            //根据盘口计算合理的买入卖出价格
            BigDecimal priceByTokenAmt = this.getPriceByTokenAmt(market, request.getTokenAmt(), SideEnum.SELL);

            //少一点钱，排到前面去
            orderRequestDTO.setPrice(priceByTokenAmt.multiply(BigDecimal.ONE.subtract(request.getSlipPoint())));

            orderRequestDTO.setTokenAmt(request.getTokenAmt());
            orderRequestDTO.setSideEnum(SideEnum.SELL);
            orderRequestDTO.setTimeInForceEnum(TimeInForceEnum.IOC);
            orderRequestDTO.setMarket(market);
            orderRequestDTO.setText(request.getText());

            Result<CreateSpotOrderResultDTO> spotOrderResponse = this.createSpotOrder(orderRequestDTO);

            if (spotOrderResponse.isSuccess() &&
                    spotOrderResponse.getData().getOrderStatusEnum().equals(OrderStatusEnum.CLOSED)) {

                CreateSpotOrderResultDTO data = spotOrderResponse.getData();
                EatSpotOrderMarketSellResultDTO resultDTO = new EatSpotOrderMarketSellResultDTO();
                resultDTO.setOrderId(data.getOrderId());
                //卖出价格
                resultDTO.setPrice(data.getPrice());
                //卖出后实际到账usdt数量 减去手续费
                resultDTO.setRealAmount(data.getFillTotal().subtract(data.getFee()));
                log.info("最新成交价市价卖出结束，resultDTO={}", JSON.toJSONString(resultDTO));
                return resultDTO;
            } else {
                //TODO 超时查证 否则会循环下单 !!!
                if (spotOrderResponse.getCode().equals(ExchangeResultCodeEnum.TIMEOUT.getCode())) {
                    log.error("最新成交价卖出-吃单，请求交易所异常，message={}", spotOrderResponse.getMsg());
                    break;  //县种植
                }
            }
            cur++;
        }
        return null;
    }


    /**
     * 现货下单
     */
    public Result<CreateSpotOrderResultDTO> createSpotOrder(CreateSpotOrderRequestDTO request) {
        try {
            return Result.success(super.createSpotOrderCore(request));
        } catch (ExchangeApiException e) {
            return Result.fail(e.getResultCode());
        }
    }

    /**
     * 查询单个订单详情，不存在时返回null
     */
    public CreateSpotOrderResultDTO spotOderQueryMustOrNull(SpotOderQueryRequestDTO sourceRequest) {
        while (true) {
            try {
                return super.spotOderQueryCore(sourceRequest);
            } catch (ExchangeApiException e) {
                if (e.getResultCode().equals(ExchangeResultCodeEnum.ORDER_NOT_FOUND)) {
                    return null;
                }
                CommonUtils.sleepSeconds(1);
            }
        }
    }

    /**
     * 查询市场深度信息
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public ListOrderBookResultDTO listOrderBookMust(ListOrderBookRequestDTO requestDTO) {
        while (true) {
            try {
                return super.listOrderBookCore(requestDTO);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }

    /**
     * 获取单个Ticker信息
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public TickResultDTO getTickerMust(String market) {
        TickRequestDTO requestDTO = new TickRequestDTO();
        requestDTO.setMarket(market);
        while (true) {
            try {
                return super.getTickerCore(requestDTO);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 获取所有Ticker信息
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public List<TickResultDTO> getTickerMust() {
        while (true) {
            try {
                return super.getTickersCore();
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }

    /**
     * 获取蜡烛图
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public List<CandlestickResultDTO> listCandlestickMust(CandlestickRequestDTO request) {
        while (true) {
            try {
                return super.listCandlesticksCore(request);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }

    /**
     * 获取最新的蜡烛图，一根K线
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public CandlestickResultDTO candlestickRecentMust(String market, CandlesticksIntervalEnum intervalEnum) {
        CandlestickRequestDTO requestDTO = new CandlestickRequestDTO();
        requestDTO.setMarket(market);
        requestDTO.setIntervalEnum(intervalEnum);
        requestDTO.setLimit(1);
        return listCandlestickMust(requestDTO).get(0);
    }


    /**
     * 获取上一个周期的蜡烛图
     * <b>该方法一定会返回结果，未返回前会一直阻塞，该方法不会抛出异常。<b>
     */
    public CandlestickResultDTO prevCandlestickMust(PrevCandlestickRequestDTO request) {
        LocalDateTime curDateTime = request.getCurDateTime();
        CandlesticksIntervalEnum intervalEnum = request.getIntervalEnum();
        String market = request.getMarket();
        CandlestickRequestDTO candlestickRequestDTO = new CandlestickRequestDTO();
        candlestickRequestDTO.setIntervalEnum(intervalEnum);
        candlestickRequestDTO.setMarket(market);
        String name = intervalEnum.name();
        String[] periods = name.split("_");
        String period = periods[0];
        String unit = periods[1];
        //当前周期
        if (StringUtils.equals(period, "M")) { //分钟
            LocalDateTime prevMin = curDateTime.minusMinutes(Long.parseLong(unit));
            candlestickRequestDTO.setFrom(prevMin);
            candlestickRequestDTO.setTo(prevMin);
        } else if (StringUtils.equals(period, "S")) {   //秒
            LocalDateTime prevSeconds = curDateTime.minusSeconds(Long.parseLong(unit));
            candlestickRequestDTO.setFrom(prevSeconds);
            candlestickRequestDTO.setTo(prevSeconds);
        } else if (StringUtils.equals(period, "H")) { //小时
            LocalDateTime prevHour = curDateTime.minusHours(Long.parseLong(unit));
            candlestickRequestDTO.setFrom(prevHour);
            candlestickRequestDTO.setTo(prevHour);
        } else if (StringUtils.equals(period, "D")) { //天
            LocalDateTime prevDay = curDateTime.minusDays(Long.parseLong(unit));
            candlestickRequestDTO.setFrom(prevDay);
            candlestickRequestDTO.setTo(prevDay);
        }
        while (true) {
            try {
                return super.listCandlesticksCore(candlestickRequestDTO).get(0);
            } catch (ExchangeApiException e) {
                CommonUtils.sleepSeconds(1);
            }
        }
    }


    /**
     * 查询交易对,从0点开始到当前时间的涨幅数据
     *
     * @param market
     * @return
     */
    public BigDecimal fetchCurrencyTodayChangePercentage(String market) {
        //今天0点的k数据,获取今天开盘价
        CandlestickResultDTO todayFirstCandlestickList = fetchTodayFirstCandlestick(market);
        if (todayFirstCandlestickList == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal todayOpenPrice = todayFirstCandlestickList.getOpen();
        //当前的k线数据,获取当前价格
        TickResultDTO tickerCore = getTickerMust(market);
        BigDecimal currentPrice = tickerCore.getLast();
        return CurrencyUtils.todayChangePercentage(todayOpenPrice, currentPrice);
    }

    /**
     * 今日0点的第一个k线,获取今日开盘价
     *
     * @param market
     * @return
     */
    public CandlestickResultDTO fetchTodayFirstCandlestick(String market) {
        CandlestickRequestDTO candlestickRequest = new CandlestickRequestDTO();
        candlestickRequest.setMarket(market);
        candlestickRequest.setFrom(DateUtils.getLocalDateTime());
        candlestickRequest.setTo(DateUtils.getLocalDateTime());
        candlestickRequest.setIntervalEnum(CandlesticksIntervalEnum.S_10);
        List<CandlestickResultDTO> candlestickList = listCandlestickMust(candlestickRequest);
        if (!CollectionUtils.isEmpty(candlestickList)) {
            return candlestickList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取单个监控周期内,振幅百分比
     *
     * @param request
     * @return
     */
    public BigDecimal getSingleMonitoringCycleChangePercentage(CandlestickRequestDTO request) {
        List<CandlestickResultDTO> candlestickResultList = listCandlestickMust(request);
        if (!CollectionUtils.isEmpty(candlestickResultList)) {
            //第一根线
            CandlestickResultDTO firstCandlestick = candlestickResultList.get(0);
            //最后一根线
            CandlestickResultDTO lastCandlestick = candlestickResultList.get(candlestickResultList.size() - 1);
            //第一根线的开盘价
            BigDecimal openPrice = firstCandlestick.getOpen();
            //最后一根线的收盘价
            BigDecimal closePrice = lastCandlestick.getClose();
            //收盘价-开盘价 = 差值
            BigDecimal differenceValue = closePrice.subtract(openPrice);
            //计算振幅比例, 差值/开盘价
            return differenceValue.divide(openPrice, 10, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }
        return null;
    }
}
