package academy.mindswap.service;

import academy.mindswap.command.account.AccountConverter;
import academy.mindswap.command.account.AccountDto;
import academy.mindswap.model.Account;
import academy.mindswap.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDto createAccount(Account account) {
        return AccountConverter.toDto(accountRepository.save(account));
    }
}
