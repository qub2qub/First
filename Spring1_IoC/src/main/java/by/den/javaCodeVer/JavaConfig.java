package by.den.javaCodeVer;

import by.den.annotVer.AmExpr;
import by.den.annotVer.Card;
import by.den.annotVer.CreditCard;
import fiona.apple.AsyncCommand;
import fiona.apple.Command;
import fiona.apple.CommandManager;
import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan(basePackages = "by.den.annotVer")
@Import(AppConfig.class)
public class JavaConfig {

    @Bean()
    public MyService myService() {
        return new MyServiceImpl();
    }

    @Bean
    public MyService finderService() {
        return new FinderService();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @Bean
    public Account bankAccount() {
        return new BankAccount();
    }

    @Bean
    public MyService findAccountService(Account account) {
        return new FindAccountService(account);
    }
//    @Bean
//    public MyService findAccountService() {
//        return new FindAccountService(bankAccount());
//    }
//
    @Bean
    public Card creditCardJava(){
        return new CreditCard();
    }
    @Bean
    @Primary
    public Card creditCard(){
        CreditCard creditCard = new CreditCard();
        creditCard.name = "java code";
        return creditCard;
    }

    @Bean
    public Card amExprCard(){
        return new AmExpr();
    }

    // an HTTP Session-scoped bean exposed as a proxy
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserPreferences userPreferences() {
        return new UserPreferences();
    }

    @Bean
    public UserService userService() {
        UserService service = new SimpleUserService();
        // a reference to the proxied userPreferences bean
        service.setUserPreferences(userPreferences());
        return service;
    }

    @Bean
    @Scope("prototype")
    public AsyncCommand asyncCommand() {
        AsyncCommand command = new AsyncCommand();
        // inject dependencies here as required
        return command;
    }

    @Bean
    public CommandManager commandManager() {
        // return new anonymous implementation of CommandManager with
        // command() overridden to return a new prototype Command object
        return new CommandManager() {
            protected Command createCommand() {
                return asyncCommand();
            }
        };
    }
}
