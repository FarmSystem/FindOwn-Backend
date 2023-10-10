package Farm.Team4.findOwn.dto.design.parsing.body;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String agentName;
    private String appReferenceNumber;
    private String applicantName;
    private String applicationDate;
    private Long applicationNumber;
    private String applicationStatus;
    private String articleName;
    private String designMainClassification;
    private String designNumber;
    private String dsShpClssCd;
    private char fullText;
    private String imagePath;
    private String imagePathLarge;
    private String internationalRegisterDate;
    private String internationalRegisterNumber;
    private String inventorName;
    private int number;
    private String openDate;
    private String openNumber;
    private String priorityDate;
    private String priorityNumber;
    private String publicationDate;
    private String publicationNumber;
    private String regReferenceNumber;
    private String registrationDate;
    private Long registrationNumber;
}
