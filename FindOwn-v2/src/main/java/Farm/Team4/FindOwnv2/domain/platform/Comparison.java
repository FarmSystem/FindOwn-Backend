package Farm.Team4.FindOwnv2.domain.platform;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Comparison {
    @Id
    @Column(name = "comparison_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originImage;
    private boolean open;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "comparison", cascade = CascadeType.ALL)
    private List<Trademark> trademarks = new ArrayList<>();
    @Builder
    public Comparison(String originImage, boolean open, Member member) {
        this.originImage = originImage;
        this.open = open;
        this.createdAt = LocalDate.now();
        this.member = member;
    }
}
