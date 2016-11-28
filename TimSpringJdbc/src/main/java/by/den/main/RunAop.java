package by.den.main;

import by.den.model.AopLocation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunAop {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-context.xml");
        AopLocation loc = (AopLocation) ctx.getBean("aopLocation");
        loc.findLocation(1);
        loc.getDescription();
//        loc.findLocation(5); // throws exception

    }
}
