package by.den.javaCodeVer;

public class BankAccount implements Account {
    public int getId() {
        return 1001;
    }

    public void init() {
        System.out.println("_____________Bank Account INIT");
    }
    public void destroy() {
        System.out.println("_____________Bank Account DESTROY");
    }
}
