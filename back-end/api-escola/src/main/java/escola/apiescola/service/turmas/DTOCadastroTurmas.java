package escola.apiescola.service.turmas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public record DTOCadastroTurmas(
        @NotBlank String nome,
        @NotNull LocalTime horario_inicio,
          boolean ativo) {
}
