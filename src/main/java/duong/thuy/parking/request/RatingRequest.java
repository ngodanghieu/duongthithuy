package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatingRequest {

    @JsonProperty("transactionId")
    private int transactionId;

    @JsonProperty("stars")
    private int stats;
}
