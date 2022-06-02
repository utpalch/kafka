
package com.kafka.topic.stream.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "createdTime",
    "storeID",
    "posID",
    "cashierID",
    "customerType",
    "customerIDNo",
    "totalAmount",
    "numberOfItems",
    "deliveryType",
    "deliveryAddress",
    "invoiceLineItems"
})
public class PosInvoice {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("createdTime")
    private Long createdTime;
    @JsonProperty("storeID")
    private String storeID;
    @JsonProperty("posID")
    private String posID;
    @JsonProperty("cashierID")
    private String cashierID;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("customerIDNo")
    private String customerCardNo;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("numberOfItems")
    private Integer numberOfItems;
    @JsonProperty("PaymentMethod")
    private String paymentMethod;
    @JsonProperty("TaxableAmount")
    private Double taxableAmount;
    @JsonProperty("CGST")
    private Double cGST;
    @JsonProperty("SGST")
    private Double sGST;
    @JsonProperty("CESS")
    private Double cESS;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("deliveryAddress")
    private DeliveryAddress deliveryAddress;
    @JsonProperty("invoiceLineItems")
    private List<LineItem> invoiceLineItems = new ArrayList<LineItem>();

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty("createdTime")
    public Long getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("createdTime")
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public PosInvoice withCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("storeID")
    public String getStoreID() {
        return storeID;
    }

    @JsonProperty("storeID")
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    @JsonProperty("posID")
    public String getPosID() {
        return posID;
    }

    @JsonProperty("posID")
    public void setPosID(String posID) {
        this.posID = posID;
    }

    @JsonProperty("cashierID")
    public String getCashierID() {
        return cashierID;
    }

    @JsonProperty("cashierID")
    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }

    @JsonProperty("customerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @JsonProperty("customerIDNo")
    public String getCustomerCardNo() {
        return customerCardNo;
    }

    @JsonProperty("customerIDNo")
    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public PosInvoice withCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
        return this;
    }

    @JsonProperty("totalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonProperty("numberOfItems")
    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    @JsonProperty("numberOfItems")
    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
    
    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("deliveryAddress")
    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    @JsonProperty("deliveryAddress")
    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @JsonProperty("invoiceLineItems")
    public List<LineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    @JsonProperty("invoiceLineItems")
    public void setInvoiceLineItems(List<LineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("invoiceNumber", invoiceNumber).append("createdTime", createdTime).append("storeID", storeID).append("posID", posID).append("cashierID", cashierID).append("customerType", customerType).append("customerIDNo", customerCardNo).append("totalAmount", totalAmount).append("numberOfItems", numberOfItems).append("paymentMethod", paymentMethod).append("taxableAmount", taxableAmount).append("cGST", cGST).append("sGST", sGST).append("cESS", cESS).append("deliveryType", deliveryType).append("deliveryAddress", deliveryAddress).append("invoiceLineItems", invoiceLineItems).toString();
    }

}
