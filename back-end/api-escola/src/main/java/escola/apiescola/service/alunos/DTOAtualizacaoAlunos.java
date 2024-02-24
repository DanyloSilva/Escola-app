package escola.apiescola.service.alunos;

import escola.apiescola.model.Turmas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record DTOAtualizacaoAlunos(@NotNull Long id, @NotBlank String nome, @NotBlank String matricula, @NotBlank LocalDate data_nascimento, Long turmaId) {
}
