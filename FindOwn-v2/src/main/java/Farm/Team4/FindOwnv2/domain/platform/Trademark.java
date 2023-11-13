package Farm.Team4.FindOwnv2.domain.platform;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Getter
@NoArgsConstructor
public class Trademark {
    @Id
    @Column(name = "trademark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member ownMember;
    private String image; // -> 이거는 추후에 이미지 객체로 변환할 예정
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;// 상표 구분
}
