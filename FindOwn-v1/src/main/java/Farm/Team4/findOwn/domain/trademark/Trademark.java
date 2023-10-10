package Farm.Team4.findOwn.domain.trademark;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.trademark.ConvertTrademark;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Getter
@NoArgsConstructor
public class Trademark {
    @Id
    @Column(name = "trademark_id")
    private Long id;
    private String image;
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;// 상표 구분
    @Nullable
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member ownMember;

    public Trademark(Long id, String image, String applicant, Long registerNum, String state, String classification) {
        this.id = id;
        this.image = image;
        this.applicant = applicant;
        this.registerNum = registerNum;
        this.state = state;
        this.classification = classification;
    }
    public Trademark updateTrademark(ConvertTrademark request){
        this.image = request.getImage();
        this.applicant = request.getApplicant();
        this.registerNum = request.getRegisterNum();
        this.state = request.getState();
        this.classification = request.getClassification();
        return this;
    }
}
