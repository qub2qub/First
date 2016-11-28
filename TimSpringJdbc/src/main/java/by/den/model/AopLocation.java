package by.den.model;

public class AopLocation {

    public Object findLocation(int postCode) {
        System.out.println("findLocation postCode="+postCode);
        if (postCode == 5) {
            throw new UnsupportedOperationException();
        }
        return new Integer(postCode);
    }

    public void getDescription() {
        System.out.println("getDescription");
    }

}
