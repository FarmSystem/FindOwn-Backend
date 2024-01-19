package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.platform.Comparison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComparisonRepository extends JpaRepository<Comparison, Long> {
    Optional<Comparison> findById(Long id);
    List<Comparison> findAllByOpenOrderByCreatedAtDescIdDesc(boolean open);
}
