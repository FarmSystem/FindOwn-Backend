package Farm.Team4.findOwn.domain.judgment;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class TrademarkJudgment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private Date savedDate; // 저장 날짜
    private int result; // 결과
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private Trademark trademark;
    public TrademarkJudgment(int result, Member member, Trademark trademark){
        this.savedDate = new Date();
        this.result = result;
        this.member = member;
        this.trademark = trademark;
    }
}
