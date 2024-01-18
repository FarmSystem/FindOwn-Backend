package Farm.Team4.FindOwnv2.dto.comparison.response.client;

import Farm.Team4.FindOwnv2.dto.comparison.response.django.JudgementAIDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

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
        @JsonProperty("application_status")
        private String applicationStatus;
        public ComparisonTrademarkDto(JudgementAIDto judgementAIDto) {
                this.result = judgementAIDto.getResult();
                this.similarity = judgementAIDto.getSimilarity().toString();
                this.title = judgementAIDto.getTitle();
                this.imagePath = judgementAIDto.getImagePath();
                this.applicationNumber = judgementAIDto.getApplicationNumber();
                this.applicationName = judgementAIDto.getApplicantName();
                this.applicationStatus = judgementAIDto.getApplicationStatus();
        }

        public ComparisonTrademarkDto(String result, String similarity, String title, String imagePath, String applicationNumber, String applicationName, String applicationStatus) {
                this.result = result;
                this.similarity = similarity;
                this.title = title;
                this.imagePath = imagePath;
                this.applicationNumber = applicationNumber;
                this.applicationName = applicationName;
                this.applicationStatus = applicationStatus;
        }
}
