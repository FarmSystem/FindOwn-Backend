package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.judgment.DesignJudgment;
import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import Farm.Team4.findOwn.domain.news.Scrap;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @NotEmpty(message = "아이디 입력은 필수입니다.")
    @NotNull
    private String id;
    @NotNull
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*])[A-Za-z\\d~!@#$%^&*]{8,}$", message = "비밀번호 형식에 맞지 않습니다.")
    //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
    //(특수문자는 정의된 특수문자만 사용 가능)
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String nickname;
    @NotNull
    private String phoneNumber;
    @NotNull
    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotNull
    private Date membershipDate;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> myPosts = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<DesignJudgment> designJudgments = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TrademarkJudgment> trademarkJudgments = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MemberOwnDesign> ownDesigns = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MemberOwnTrademark> ownTrademarks = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Scrap> scraps = new ArrayList<>();
    public Member(String id, String password, String name, String nickname, String phoneNumber, String email, Date now){
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipDate = now;
    }
    public Member changePassword(String newPassword){
        this.password = newPassword;
        return this;
    }
    public Member changeEmail(String newEmail){
        this.email = newEmail;
        return this;
    }

}
