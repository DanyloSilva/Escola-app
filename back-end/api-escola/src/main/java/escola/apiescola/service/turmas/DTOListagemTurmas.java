package escola.apiescola.service.turmas;

import escola.apiescola.model.Alunos;
import escola.apiescola.model.Turmas;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public record DTOListagemTurmas(
        @NotBlank Long id, @NotBlank String nome,
                                @NotBlank LocalTime horario_inicio,
                                @NotBlank  boolean ativo) {
    public  DTOListagemTurmas (Turmas turmas){
    this(turmas.getId() , turmas.getNome(), turmas.getHorario_inicio(), turmas.isAtivo());
}
}
