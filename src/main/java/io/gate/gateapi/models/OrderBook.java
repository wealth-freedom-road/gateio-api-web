/*
 * Gate API v4
 * Welcome to Gate.io API  APIv4 provides spot, margin and futures trading operations. There are public APIs to retrieve the real-time market statistics, and private APIs which needs authentication to trade on user's behalf.
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.gate.gateapi.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderBook
 */
public class OrderBook {
    public static final String SERIALIZED_NAME_ID = "id";
    @SerializedName(SERIALIZED_NAME_ID)
    private Long id;

    public static final String SERIALIZED_NAME_CURRENT = "current";
    @SerializedName(SERIALIZED_NAME_CURRENT)
    private Long current;

    public static final String SERIALIZED_NAME_UPDATE = "update";
    @SerializedName(SERIALIZED_NAME_UPDATE)
    private Long update;

    public static final String SERIALIZED_NAME_ASKS = "asks";
    @SerializedName(SERIALIZED_NAME_ASKS)
    private List<List<String>> asks = new ArrayList<>();

    public static final String SERIALIZED_NAME_BIDS = "bids";
    @SerializedName(SERIALIZED_NAME_BIDS)
    private List<List<String>> bids = new ArrayList<>();


    public OrderBook id(Long id) {
        
        this.id = id;
        return this;
    }

     /**
     * Order book ID, which is updated whenever the order book is changed. Valid only when &#x60;with_id&#x60; is set to &#x60;true&#x60;
     * @return id
    **/
    @javax.annotation.Nullable
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public OrderBook current(Long current) {
        
        this.current = current;
        return this;
    }

     /**
     * The timestamp of the response data being generated (in milliseconds)
     * @return current
    **/
    @javax.annotation.Nullable
    public Long getCurrent() {
        return current;
    }


    public void setCurrent(Long current) {
        this.current = current;
    }

    public OrderBook update(Long update) {
        
        this.update = update;
        return this;
    }

     /**
     * The timestamp of when the orderbook last changed (in milliseconds)
     * @return update
    **/
    @javax.annotation.Nullable
    public Long getUpdate() {
        return update;
    }


    public void setUpdate(Long update) {
        this.update = update;
    }

    public OrderBook asks(List<List<String>> asks) {
        
        this.asks = asks;
        return this;
    }

    public OrderBook addAsksItem(List<String> asksItem) {
        this.asks.add(asksItem);
        return this;
    }

     /**
     * Asks order depth
     * @return asks
    **/
    public List<List<String>> getAsks() {
        return asks;
    }


    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public OrderBook bids(List<List<String>> bids) {
        
        this.bids = bids;
        return this;
    }

    public OrderBook addBidsItem(List<String> bidsItem) {
        this.bids.add(bidsItem);
        return this;
    }

     /**
     * Bids order depth
     * @return bids
    **/
    public List<List<String>> getBids() {
        return bids;
    }


    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(this.id, orderBook.id) &&
                Objects.equals(this.current, orderBook.current) &&
                Objects.equals(this.update, orderBook.update) &&
                Objects.equals(this.asks, orderBook.asks) &&
                Objects.equals(this.bids, orderBook.bids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, current, update, asks, bids);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderBook {\n");
        sb.append("      id: ").append(toIndentedString(id)).append("\n");
        sb.append("      current: ").append(toIndentedString(current)).append("\n");
        sb.append("      update: ").append(toIndentedString(update)).append("\n");
        sb.append("      asks: ").append(toIndentedString(asks)).append("\n");
        sb.append("      bids: ").append(toIndentedString(bids)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n        ");
    }

}
