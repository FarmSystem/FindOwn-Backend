package Farm.Team4.findOwn.dto.trademark;

import lombok.Data;

@Data
public class ConvertTrademark {
    private Long trademarkId;
    private String image;
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;

    public ConvertTrademark(Long trademarkId, String image, String applicant, Long registerNum, String state, String classification) {
        this.trademarkId =trademarkId;
        this.image = image;
        this.applicant = applicant;
        this.registerNum = registerNum;
        this.state = state;
        this.classification = classification;
    }
}
