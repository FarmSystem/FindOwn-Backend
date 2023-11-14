package Farm.Team4.FindOwnv2.domain.platform;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.community.issue.Scrap;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String korName;
    private String email;
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

    public Member(String username, String password, String korName, String email) {
        this.username = username;
        this.password = password;
        this.korName = korName;
        this.email = email;
        this.register = LocalDateTime.now();
        this.role = "USER";
    }
}
