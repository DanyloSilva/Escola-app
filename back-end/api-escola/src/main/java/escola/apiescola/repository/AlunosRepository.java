package escola.apiescola.repository;

import escola.apiescola.model.Alunos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunosRepository  extends JpaRepository <Alunos,Long> {
    Page<Alunos> findAllByAtivoTrue(Pageable paginacao);
    List<Alunos> findByTurmaId(Long turmaId);
}



