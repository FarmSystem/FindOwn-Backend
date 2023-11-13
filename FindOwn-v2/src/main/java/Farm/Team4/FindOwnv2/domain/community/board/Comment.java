package Farm.Team4.FindOwnv2.domain.community.board;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private String content;
    private LocalDateTime createdAt;
}
