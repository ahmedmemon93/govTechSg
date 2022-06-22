package oppenheimer.pojos.response;

import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaxReliefResponse {
    @JsonProperty("natid")
    private String natid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("relief")
    private String relief;
}
