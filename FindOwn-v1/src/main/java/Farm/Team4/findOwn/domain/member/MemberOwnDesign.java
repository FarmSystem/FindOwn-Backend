package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.design.Design;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberOwnDesign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @ManyToOne
    @JoinColumn(name = "design_id")
    private Design design;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public MemberOwnDesign(Design design, Member member) {
        this.design = design;
        this.member = member;
    }
    public MemberOwnDesign updateDesign(Design design){
        this.design = design;
        return this;
    }

}
