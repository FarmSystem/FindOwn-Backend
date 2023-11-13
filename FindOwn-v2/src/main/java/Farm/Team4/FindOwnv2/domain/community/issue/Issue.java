package Farm.Team4.FindOwnv2.domain.community.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Issue {
    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(columnDefinition = "TEXT")
    private String url;
    private int viewCnt;
    private int scrapCnt;
    public void increaseView(){
        this.viewCnt++;
    }
    public void increaseScrap(){
        this.scrapCnt++;
    }
}
