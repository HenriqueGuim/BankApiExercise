package academy.mindswap.command.account;

import academy.mindswap.model.Client;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Builder
@Data
public class CreateAccountDto implements Serializable {
    @Null(message = "Id must be null")
    private final Long id;
    @NotNull(message = "Currency is mandatory")
    private final String currency;
    @NotNull(message = "Balance is mandatory")
    private final Double balance;

}