package academy.mindswap.service;

import academy.mindswap.command.account.AccountDto;
import academy.mindswap.model.Account;

public interface AccountService {
    AccountDto createAccount(Account account);
}
