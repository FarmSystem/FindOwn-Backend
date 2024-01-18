package Farm.Team4.FindOwnv2.domain.platform;

import Farm.Team4.FindOwnv2.dto.comparison.response.client.ComparisonTrademarkDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trademark_id")
    private Long id;
    private String result;
    private String similarity;
    private String title;
    private String applicationNumber;
    private String applicantName;
    private String applicationStatus;
    @ManyToOne
    @JoinColumn(name = "comparison_id")
    private Comparison comparison;

    public Trademark(ComparisonTrademarkDto comparisonTrademarkDto, Comparison comparison) {
        this.result = comparisonTrademarkDto.getResult();
        this.similarity = comparisonTrademarkDto.getSimilarity().toString();
        this.title = comparisonTrademarkDto.getTitle();
        this.applicationNumber = comparisonTrademarkDto.getApplicationNumber();
        this.applicantName = comparisonTrademarkDto.getApplicationName();
        this.applicationStatus = comparisonTrademarkDto.getApplicationStatus();
        this.comparison = comparison;
    }
}
