package by.den.run;

import by.den.annotVer.AmExpr;
import by.den.annotVer.Card;
import by.den.annotVer.CreditCard;
import by.den.javaCodeVer.FindAccountService;
import by.den.javaCodeVer.FinderService;
import by.den.javaCodeVer.JavaConfig;
import by.den.javaCodeVer.MyService;
import fiona.apple.CommandManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigRunner {
    public static void main(String[] args) {

//        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext("by.den.javaCodeVer");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // add a shutdown hook for the above context...
        ctx.registerShutdownHook();
        ctx.register(JavaConfig.class);
        // после этого можно не регить конфиг (строка ниже)
        // т.к. при scan() конфиг зарегится как аннотированный бин
//        ctx.scan("by.den.javaCodeVer");
        ctx.refresh();

        CommandManager mng = ctx.getBean(CommandManager.class);
        MyService findAcc = ctx.getBean(FindAccountService.class);
        MyService my = (MyService) ctx.getBean("myService");
        MyService finder = (FinderService) ctx.getBean("finderService");
        findAcc.doJob();
        my.doJob();
//        MyService my2 = ctx.getBean(MyService.class);
//        my2.doJob();
        finder.doJob();

//        Card credit = (Card) ctx.getBean("creditCardJava");
        Card credit = ctx.getBean(CreditCard.class);
        Card amer = ctx.getBean(AmExpr.class);
        System.out.println(credit);
        System.out.println(amer);
//        ctx.destroy();
        ctx.close();
    }
}
