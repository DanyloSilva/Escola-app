package escola.apiescola.service.alunos;

import escola.apiescola.model.Turmas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
//aqui fiz isso para o dato nem cadastrar se vir vazio

public record DTOCadastrarAlunos( @NotBlank String nome,
                                  @NotBlank String matricula,
                                  @NotNull LocalDate data_nascimento,
                                   Long turmaId ) {
}
