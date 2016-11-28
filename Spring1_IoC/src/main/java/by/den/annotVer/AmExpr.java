package by.den.annotVer;

import org.springframework.stereotype.Service;

@Service
public class AmExpr implements Card {
    public String getOwner() {
        return "AmExprOwner";
    }

    public int getNumber() {
        return 2;
    }

    @Override
    public String toString() {
        return "AmExpr{"+getNumber()+", "+getOwner()+"}";
    }
}
