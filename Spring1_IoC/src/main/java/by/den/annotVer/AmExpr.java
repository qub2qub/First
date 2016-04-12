package by.den.annotVer;

import org.springframework.stereotype.Service;

@Service
public class AmExpr implements Card {
    public String getOwner() {
        return "AmExpr";
    }

    public int getNumber() {
        return 1;
    }

    @Override
    public String toString() {
        return "AmExpr{"+getNumber()+", "+getOwner()+"}";
    }
}
