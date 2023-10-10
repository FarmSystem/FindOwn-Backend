package Farm.Team4.findOwn.repository.member;

import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberOwnDesignRepository extends JpaRepository<MemberOwnDesign, Long> {
    Optional<MemberOwnDesign> findById(Long id);
}
