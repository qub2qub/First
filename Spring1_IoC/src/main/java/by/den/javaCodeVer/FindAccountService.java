package by.den.javaCodeVer;

public class FindAccountService implements MyService{

    Account account;

    public FindAccountService(Account account) {
        this.account = account;
    }

    public void doJob() {
        System.out.println("Find Account with ID="+account.getId());
    }
}
