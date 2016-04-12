package by.den.javaCodeVer;

import by.den.annotVer.AmExpr;
import by.den.annotVer.Card;
import by.den.annotVer.CreditCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "by.den.annotVer")
public class JavaConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

    @Bean
    public MyService finderService() {
        return new FinderService();
    }

    @Bean
    public Account bankAccount() {
        return new BankAccount();
    }

    @Bean
    public MyService findAccountService() {
        return new FindAccountService(bankAccount());
    }

    @Bean
    public Card creditCard(){
        return new CreditCard();
    }
    @Bean
    public Card amExprCard(){
        return new AmExpr();
    }

}
