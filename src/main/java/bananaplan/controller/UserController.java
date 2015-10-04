package bananaplan.controller;

import bananaplan.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bananaplan.service.AccountService;

import javax.websocket.server.PathParam;

@RestController
public class UserController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void createAccount(@RequestBody Account account){
        accountService.createAccount(account);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String accountLogin(@PathParam("username") String username, @PathParam("password") String password){
        return accountService.getAccount(username, password) != null ? "find account!" : "account not exists or username/password not match";
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
    }
}
