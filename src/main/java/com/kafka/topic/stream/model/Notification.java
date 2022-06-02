
package com.kafka.topic.stream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "CustomerIDNo",
    "TotalAmount",
    "earnedLoyaltyPoints"
})
public class Notification {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("customerIDNo")
    private String customerCardNo;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("earnedLoyaltyPoints")
    private Double earnedLoyaltyPoints;

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty("customerIDNo")
    public String getCustomerIDNo() {
        return customerCardNo;
    }

    @JsonProperty("customerIDNo")
    public void setCustomerIDNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    @JsonProperty("totalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonProperty("earnedLoyaltyPoints")
    public Double getEarnedLoyaltyPoints() {
        return earnedLoyaltyPoints;
    }

    @JsonProperty("earnedLoyaltyPoints")
    public void setEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyaltyPoints = earnedLoyaltyPoints;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("invoiceNumber", invoiceNumber).append("CustomerIDNo", customerCardNo).append("totalAmount", totalAmount).append("earnedLoyaltyPoints", earnedLoyaltyPoints).toString();
    }

}
