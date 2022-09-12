package academy.mindswap.command.client;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CreateClientDto{
    @Null(message = "Id must be null")
    private final Long id;
    @NotBlank
    private final String name;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$")
    private final String email;
    @NotBlank
    @Pattern(regexp ="^[0-9]{4}$")
    private final String pin;
}