package balaji.hibernate.transactionmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bankaccount")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer() {
        service.transfer(500);
    }

}
