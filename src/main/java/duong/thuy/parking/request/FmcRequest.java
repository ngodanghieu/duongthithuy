package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FmcRequest {
    @JsonProperty("deviceToken")
    private String deviceToken;
}
