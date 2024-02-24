package escola.apiescola.service.alunos;

import escola.apiescola.model.Alunos;
import escola.apiescola.model.Turmas;

import java.time.LocalDate;

public record DTOListagemAlunos(Long id, String nome, String matricula, LocalDate dataNascimento, Long turmaId, String nomeTurma) {

    public DTOListagemAlunos(Alunos alunos) {
        this(alunos.getId(), alunos.getNome(), alunos.getMatricula(), alunos.getData_nascimento(),
                alunos.getTurmaId(),
                alunos.getTurmas() != null ? alunos.getTurmas().getNome() : null);
    }

}









