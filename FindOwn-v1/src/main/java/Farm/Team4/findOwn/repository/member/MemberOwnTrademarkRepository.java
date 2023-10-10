package Farm.Team4.findOwn.repository.member;

import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberOwnTrademarkRepository extends JpaRepository<MemberOwnTrademark, Long> {
    Optional<MemberOwnTrademark> findById(Long id);
}
