package duong.thuy.parking.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParkingResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("blockAmount")
    private Long blockAmount;

    @JsonProperty("ownerId")
    private Long ownerId;

    @JsonProperty("parkingName")
    private String parkingName;

    @JsonProperty("properties")
    private String properties;

    @JsonProperty("address")
    private String address;

    @JsonProperty("kindOf")
    private String kindOf;

    @JsonProperty("parkingImages")
    private String parkingImages;

    @JsonProperty("payment")
    private String payment;

    @JsonProperty("capacity")
    private String capacity;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("status")
    private String status;

    @JsonProperty("certificateOfland")
    private String certificateOfland;

    @JsonProperty("modified_at")
    private String modified_at;

    @JsonProperty("describe")
    private String describe;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("latitude")
    private Double latitude;
}
