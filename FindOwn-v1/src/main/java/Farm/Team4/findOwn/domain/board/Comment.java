package Farm.Team4.findOwn.domain.board;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "member")
    private Member writer;
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;
    public Comment(String content, Member writer, Post post) {
        this.content = content;
        this.writer = writer;
        this.post = post;
        this.createdAt = new Date();
    }
    public Comment updateComment(String content){
        this.content = content;
        this.createdAt = new Date();
        return this;
    }
}
