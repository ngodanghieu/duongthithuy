package duong.thuy.parking.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("points")
    private String points;

    @JsonProperty("email")
    private String email;
}
