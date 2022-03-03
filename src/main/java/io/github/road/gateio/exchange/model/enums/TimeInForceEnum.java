package io.github.road.gateio.exchange.model.enums;

import lombok.Getter;

public enum TimeInForceEnum {

    GTC("gtc"),
    IOC("ioc");

    @Getter
    private String value;

    private TimeInForceEnum(String value) {
        this.value = value;
    }

}