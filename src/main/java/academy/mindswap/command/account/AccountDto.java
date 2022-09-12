package academy.mindswap.command.account;

import academy.mindswap.command.client.ClientDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class AccountDto implements Serializable {
    private final Long id;
    private final String currency;
    private final Double balance;
    private final List<ClientDto> clients;

}