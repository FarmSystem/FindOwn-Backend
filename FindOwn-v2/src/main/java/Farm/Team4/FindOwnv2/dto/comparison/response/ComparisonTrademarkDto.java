package Farm.Team4.FindOwnv2.dto.comparison.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ComparisonTrademarkDto{
        @JsonProperty("result")
        private String result;
        @JsonProperty("similarity")
        private String similarity;
        @JsonProperty("title")
        private String title;
        @JsonProperty("image_path")
        private String imagePath;
        @JsonProperty("application_number")
        private String applicationNumber;
        @JsonProperty("applicant_name")
        private String applicationName;

        public void updatePath(String s3Path){
                this.imagePath = s3Path;
        }
}
