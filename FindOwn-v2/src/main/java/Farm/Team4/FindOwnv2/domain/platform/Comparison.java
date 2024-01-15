package Farm.Team4.FindOwnv2.domain.platform;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comparison {
    @Id
    @Column(name = "comparison_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testImageUrl; // -> image는 객체 만들어서 추후 변경 예정
    private String resultImageUrl;
    private String trademarkName;
    private String applicant;
    private String result;
    private int resultNum;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
