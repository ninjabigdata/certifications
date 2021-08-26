package balaji.hibernate.transactionmanagement;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "account_id")
    private Integer accountId;
    private String name;
    private Integer balance;

}
