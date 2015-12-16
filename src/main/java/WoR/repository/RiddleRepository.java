package WoR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import WoR.domain.Riddle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RiddleRepository extends JpaRepository<Riddle, Long> {

    Page<Riddle> findAll(Pageable pageable);
}
