package Farm.Team4.FindOwnv2.domain.platform;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.community.issue.Scrap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String nickname;
    private String phoneNumber;
    private LocalDateTime register;
    private String role;
    @OneToMany(mappedBy = "ownMember", cascade = CascadeType.ALL)
    private List<Trademark> myTrademarks = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comparison> myJudgements = new ArrayList<>();
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> myPosts = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Scrap> myScraps = new ArrayList<>();
}
