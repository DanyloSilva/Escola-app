package escola.apiescola.service.turmas;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public record DTOAtualizacaoTurmas(
        @NotBlank Long id,
        @NotBlank String nome,
                                     @NotBlank LocalTime horario_inicio,
                                     @NotBlank  boolean ativo) {
}
