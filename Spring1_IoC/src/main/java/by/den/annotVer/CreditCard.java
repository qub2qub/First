package by.den.annotVer;

import org.springframework.stereotype.Component;

@Component
public class CreditCard implements Card {

    public String getOwner() {
        return "credit";
    }

    public int getNumber() {
        return 1;
    }

    @Override
    public String toString() {
        return "CreditCard{"+getNumber()+", "+getOwner()+"}";
    }
}
