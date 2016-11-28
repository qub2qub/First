package by.den.annotVer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCard implements Card {

    @Autowired(required = false)
    public MissingClass missingClass;

    public String name = "default card";

    public String getOwner() {
        return "CreditOwner";
    }

    public int getNumber() {
        return 1;
    }



    @Override
    public String toString() {
        return name+ "CreditCard{"+getNumber()+", "+getOwner()+", missing="+missingClass+"}";
    }
}
