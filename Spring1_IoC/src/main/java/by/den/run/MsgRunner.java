package by.den.run;

import by.den.beans.Message;
import by.den.beans.MsgConstructor;
import by.den.beans.MsgDefault;
import by.den.beans.MsgSetter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MsgRunner {

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("/application-context.xml");
//        ApplicationContext appContext = new XmlWebApplicationContext("/application-context.xml");

//        appContext.getParentBeanFactory().

        Message message = (Message) appContext.getBean("message");
        MsgConstructor msgConstructor = (MsgConstructor) appContext.getBean("msgConstructor");
        MsgDefault msgDefault = (MsgDefault) appContext.getBean("msgDefault");
        MsgSetter msgSetter = (MsgSetter) appContext.getBean("msgSetter");

        System.out.println("message = " + message.getMessage());
        System.out.println("msgConstructor = " + msgConstructor.getMessage());
        System.out.println("msgDefault = " + msgDefault.getMessage());
        System.out.println("msgSetter = " + msgSetter.getMessage());
    }
}
