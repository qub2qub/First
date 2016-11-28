package x.y;

public class Foo {

    Bar bar;
    Baz baz;
    String email;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Foo() {
    }

    public Foo(Bar bar, Baz baz, String email) {
        this.bar = bar;
        this.baz = baz;
        this.email = email;
    }
}
