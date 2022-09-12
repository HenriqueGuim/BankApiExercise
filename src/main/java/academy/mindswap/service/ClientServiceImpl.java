package academy.mindswap.service;

import academy.mindswap.aop.LogExecutionTime;
import academy.mindswap.command.account.AccountConverter;
import academy.mindswap.command.account.AccountDto;
import academy.mindswap.command.account.CreateAccountDto;
import academy.mindswap.command.client.ClientDto;
import academy.mindswap.command.client.ClientDtoConverter;
import academy.mindswap.command.client.CreateClientDto;
import academy.mindswap.exceptions.ClientNotFoundException;
import academy.mindswap.model.Account;
import academy.mindswap.model.Client;
import academy.mindswap.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AccountServiceImpl accountServiceImpl;

    public ClientServiceImpl(ClientRepository clientRepository, AccountServiceImpl accountServiceImpl) {
        this.clientRepository = clientRepository;
        this.accountServiceImpl = accountServiceImpl;
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(ClientDtoConverter::toDto).toList();
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return ClientDtoConverter.toDto(client);
    }

    @Override
    public List<AccountDto> getAccountsByClientId(Long id) {

        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            return null;
        }

        return client.getAccounts().stream().map(AccountConverter::toDto).toList();
    }

    @Override
    @LogExecutionTime
    public Client createClient(CreateClientDto clientDto) {

        Client client = ClientDtoConverter.fromCreateDto(clientDto);

        return clientRepository.save(client);

    }

    @Override
    public Client[] addAccount(String id, CreateAccountDto createAccountDto) {
        Long[] ids = Arrays.stream(id.split("-")).map(Long::parseLong).toArray(Long[]::new);

        Client[] client = new Client[ids.length];
        for (int i = 0; i < client.length; i++) {
            client[i] = clientRepository.findById(ids[i]).orElse(null);
        }
        for (int i = 0; i < client.length; i++) {
            if (client[i] == null) {
                return null;
            }
        }

        Account account = AccountConverter.toAccount(createAccountDto);
        account.setClients(List.of(client));

        for (Client client1 : client) {
            client1.getAccounts().add(account);
        }

        accountServiceImpl.createAccount(account);
        for (Client client1 : client) {
            clientRepository.save(client1);
        }
        return client;
    }
}
