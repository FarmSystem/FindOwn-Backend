package Farm.Team4.FindOwnv2.domain.community.board;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;
    private String title;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private LocalDateTime creatAt;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    private int viewCnt;
    private int scrapCnt;
    public void increaseView(){
        this.viewCnt++;
    }
    public void increaseScrap(){
        this.scrapCnt++;
    }
}
