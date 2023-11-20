package Farm.Team4.FindOwnv2.domain.community.issue;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public Scrap(Member member, Issue issue) {
        this.member = member;
        this.issue = issue;
    }
}
