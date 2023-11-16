package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
    boolean existsByUsername(String username);
}
