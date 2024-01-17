package Farm.Team4.FindOwnv2.dto.comparison.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgementAIDto {
    @JsonProperty("result")
    private String result;
    @JsonProperty("accuracy")
    private Float similarity;
    @JsonProperty("ApplicationNumber")
    private String applicationNumber;
    @JsonProperty("ApplicantName")
    private String applicantName;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("ImagePath")
    private String imagePath;
    public void updateImagePath(String s3Path){
        this.imagePath = s3Path;
    }
}
