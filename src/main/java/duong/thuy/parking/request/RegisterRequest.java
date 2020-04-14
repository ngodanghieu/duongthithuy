package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {

    @JsonProperty(value = "username")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String userName;

    @NotNull(message = "")
    @NotEmpty(message = "")
    @JsonProperty(value = "password")
    private String password;

    @NotNull(message = "")
    @NotEmpty(message = "")
    @JsonProperty(value = "email")
    private String email;
}
