package Farm.Team4.findOwn.repository.member;

import Farm.Team4.findOwn.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    boolean existsById(String id);
    boolean existsByEmail(String email);
}
