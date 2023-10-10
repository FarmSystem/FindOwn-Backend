package Farm.Team4.findOwn.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class News {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String source; // 출처
    private String reporter; // 기자님 성함

    public News(String title, String category, String content, String source, String reporter) {
        this.title = title;
        this.category = Enum.valueOf(Category.class, category);
        this.content = content;
        this.source = source;
        this.reporter = reporter;
    }
}
