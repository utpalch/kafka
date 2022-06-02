
package com.kafka.topic.stream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "itemCode",
    "itemDescription",
    "itemPrice",
    "quantity",
    "totalValues"
})
public class LineItem {

    @JsonProperty("itemCode")
    private String itemCode;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("itemPrice")
    private Double itemPrice;
    @JsonProperty("quantity")
    private Integer itemQty;
    @JsonProperty("totalValues")
    private Double totalValue;

    @JsonProperty("itemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("itemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("itemPrice")
    public Double getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("itemPrice")
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @JsonProperty("quantity")
    public Integer getItemQty() {
        return itemQty;
    }

    @JsonProperty("quantity")
    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    @JsonProperty("totalValues")
    public Double getTotalValue() {
        return totalValue;
    }

    @JsonProperty("totalValues")
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("itemCode", itemCode).append("itemDescription", itemDescription).append("itemPrice", itemPrice).append("quantity", itemQty).append("totalValue", totalValue).toString();
    }

}
