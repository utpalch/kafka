
package com.kafka.topic.stream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "addressLine",
    "city",
    "state",
    "zip",
    "contactNumber"
})
public class DeliveryAddress {

    @JsonProperty("addressLine")
    private String addressLine;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("addressLine")
    public String getAddressLine() {
        return addressLine;
    }

    @JsonProperty("addressLine")
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("contactNumber")
    public String getContactNumber() {
        return contactNumber;
    }

    @JsonProperty("contactNumber")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("addressLine", addressLine).append("city", city).append("state", state).append("zip", zip).append("contactNumber", contactNumber).toString();
    }

}
