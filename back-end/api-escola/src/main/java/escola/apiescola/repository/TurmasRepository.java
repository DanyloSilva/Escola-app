package escola.apiescola.repository;

import escola.apiescola.model.Turmas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface TurmasRepository extends JpaRepository<Turmas, Long> {
    Page<Turmas> findAllByAtivoTrue(Pageable paginacao);


}
