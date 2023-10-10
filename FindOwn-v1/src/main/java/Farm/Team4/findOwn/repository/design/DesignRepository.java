package Farm.Team4.findOwn.repository.design;

import Farm.Team4.findOwn.domain.design.Design;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, Long> {
}
