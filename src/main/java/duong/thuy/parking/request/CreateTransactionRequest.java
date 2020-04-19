package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateTransactionRequest {
    @JsonProperty(value = "startTime")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String startTime;

    @JsonProperty(value = "endTime")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String endTime;

    @JsonProperty(value = "requestedPayment")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String requestedPayment;

    @JsonProperty(value = "phoneNumber")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String phoneNumber;

    @JsonProperty(value = "licence")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private String licence;

    @JsonProperty(value = "parkingId")
    @NotNull(message = "")
    @NotEmpty(message = "")
    private int parkingId;

}
