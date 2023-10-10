package Farm.Team4.findOwn.domain.board.post;

import Farm.Team4.findOwn.domain.board.Comment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.news.News;
import Farm.Team4.findOwn.dto.board.post.request.UpdatePostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostWithTag> postWithTags = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, Type type, String content, Date createdAt, Member member) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.createdAt = createdAt;
        this.member = member;
    }
    public Post updatePost(UpdatePostRequest request){
        this.title = request.getTitle();
        this.type = Enum.valueOf(Type.class, request.getType());
        this.content = request.getContent();
        this.createdAt = new Date();
        return this;
    }
}
