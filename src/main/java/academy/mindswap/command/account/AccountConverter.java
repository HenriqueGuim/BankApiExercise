package academy.mindswap.command.account;

import academy.mindswap.command.client.ClientDtoConverter;
import academy.mindswap.model.Account;

public class AccountConverter {

        public static Account toAccount(CreateAccountDto createAccountDto) {
            return Account.builder()
                    .id(createAccountDto.getId())
                    .currency(createAccountDto.getCurrency())
                    .balance(createAccountDto.getBalance())
                    .build();
        }

        public static AccountDto toDto(Account account) {
            return AccountDto.builder()
                    .id(account.getId())
                    .currency(account.getCurrency())
                    .balance(account.getBalance())
                    .clients(account.getClients().stream().map(ClientDtoConverter::toDto).toList())
                    .build();
        }


}
