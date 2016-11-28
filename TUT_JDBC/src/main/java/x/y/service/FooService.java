// the service interface that we want to make transactional

package x.y.service;

import x.y.model.Foo;

public interface FooService {

    Foo getFoo(String fooName);

    Foo getFoo(String fooName, String barName);

    void insertFoo(Foo foo);

    void updateFoo(Foo foo);

}