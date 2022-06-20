package oppenheimer.pojos.response;

import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkingHero {
    @JsonProperty("birthday")
    String birthday;
    @JsonProperty("gender")
    String gender;
    @JsonProperty("name")
    String name;
    @JsonProperty("natid")
    String natid;
    @JsonProperty("salary")
    String salary;
    @JsonProperty("tax")
    String tax;
}
