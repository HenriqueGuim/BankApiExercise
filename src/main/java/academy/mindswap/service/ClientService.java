package academy.mindswap.service;

import academy.mindswap.command.account.AccountDto;
import academy.mindswap.command.account.CreateAccountDto;
import academy.mindswap.command.client.ClientDto;
import academy.mindswap.command.client.CreateClientDto;
import academy.mindswap.model.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    ClientDto getClientById(Long id);

    List<AccountDto> getAccountsByClientId(Long id);

    Client createClient(CreateClientDto clientDto);

    Client[] addAccount(String id, CreateAccountDto createAccountDto);
}
