package Farm.Team4.findOwn.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;
    @Column(unique = true)
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
