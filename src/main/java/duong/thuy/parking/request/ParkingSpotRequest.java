package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ParkingSpotRequest {

    @JsonProperty(value = "parkingName")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String parkingName;

    @JsonProperty(value = "address")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String address;

    @JsonProperty(value = "properties")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String properties;

    @JsonProperty(value = "payMethod")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String payMethod;

    @JsonProperty(value = "garaType")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String garaType;

    @JsonProperty(value = "longitude")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String longitude;

    @JsonProperty(value = "latitude")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String latitude;

    @JsonProperty(value = "capacity")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String capacity;

    @JsonProperty(value = "price")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String price;

    @JsonProperty(value = "describe")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String describe;

    @JsonProperty(value = "garaImage")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String garaImage;

    @JsonProperty(value = "certifitateLandImage")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String certifitateLandImage;

}
