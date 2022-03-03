package io.github.road.gateio.exchange.model.enums;

import lombok.Getter;

public enum SideEnum {

    BUY("buy"),
    SELL("sell");

    @Getter
    private String value;

    private SideEnum(String value) {
        this.value = value;
    }
}