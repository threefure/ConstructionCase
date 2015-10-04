package bananaplan.service;

import bananaplan.domain.Account;
import bananaplan.domain.EncryptedPassword;
import bananaplan.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by paulou on 9/30/15.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordService passwordService;

    public void createAccount(Account account) {
        EncryptedPassword encryptedPassword = passwordService.encryptedPassword(account.getPassword());
        account.setPassword(encryptedPassword.getEncryptedPassword());
        account.setSalt(encryptedPassword.getSalt());
        accountRepository.save(account);
    }

    public Account getAccount(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public void deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        accountRepository.delete(account);
    }
}
