package Farm.Team4.FindOwnv2.domain.community.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String source;
    private String reporter;
    private int viewCnt;
    private int scrapCnt;
    private LocalDate createdAt;
    public void increaseView(){
        this.viewCnt++;
    }
    public void increaseScrap(){
        this.scrapCnt++;
    }
    public void decreaseScrap(){this.scrapCnt--;}

    public Issue(String category, String title, String content, String source, String reporter) {
        this.category = Category.valueOf(category);
        this.title = title;
        this.content = content;
        this.source = source;
        this.reporter = reporter;
        this.viewCnt = 0;
        this.scrapCnt = 0;
        this.createdAt = LocalDate.now();
    }
}
