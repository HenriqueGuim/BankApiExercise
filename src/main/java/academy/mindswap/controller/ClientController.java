package academy.mindswap.controller;

import academy.mindswap.command.account.AccountDto;
import academy.mindswap.command.account.CreateAccountDto;
import academy.mindswap.command.client.ClientDto;
import academy.mindswap.command.client.ClientDtoConverter;
import academy.mindswap.command.client.CreateClientDto;
import academy.mindswap.model.Client;
import academy.mindswap.service.ClientService;
import academy.mindswap.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

   private final ClientService clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(clients);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        ClientDto client = clientService.getClientById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<?> getAccountsByClientId(@PathVariable Long id) {
        List<AccountDto> accounts = clientService.getAccountsByClientId(id);

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody CreateClientDto clientDto, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(getErrorMap(result), HttpStatus.BAD_REQUEST);
        }

        Client client = clientService.createClient(clientDto);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}/add-account")
    public ResponseEntity<?> addAccount(@PathVariable String id, @Valid @RequestBody CreateAccountDto createAccountDto, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(getErrorMap(result), HttpStatus.BAD_REQUEST);
        }

        Client[] client = clientService.addAccount(id, createAccountDto);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(Arrays.stream(client).map(ClientDtoConverter::toDto).toList());
    }

    private Map<String,String> getErrorMap(BindingResult result){
        Map<String,String> errorMap = new HashMap<>();
        result.getAllErrors().forEach(error -> errorMap.put(((FieldError)error).getField()  ,error.getDefaultMessage()));

        return errorMap;
    }




}
