package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateOwnerRequest {

    @JsonProperty(value = "fullname")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String fullName;

    @JsonProperty(value = "address")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String address;

    @JsonProperty(value = "phoneNumber")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String phoneNumber;

    @JsonProperty(value = "image")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String cmndImage;

}
