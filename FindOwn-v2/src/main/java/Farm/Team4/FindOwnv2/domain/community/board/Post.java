package Farm.Team4.FindOwnv2.domain.community.board;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.community.post.request.UpdatePostDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    private int viewCnt;
    private int scrapCnt;
    @Transactional
    public void increaseView(){
        this.viewCnt+=1;
    }
    @Transactional
    public void increaseScrap(){
        this.scrapCnt++;
    }

    public Post(Member writer, String title, Tag tag, String content) {
        this.writer = writer;
        this.title = title;
        this.tag = tag;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.viewCnt = 0;
        this.scrapCnt = 0;
    }
    public void update(UpdatePostDTO updatePostDTO){
        this.title = updatePostDTO.getTitle();
        this.tag = Tag.valueOf(updatePostDTO.getTagName());
        this.content = updatePostDTO.getContent();
        this.createdAt = LocalDateTime.now();
    }
}
