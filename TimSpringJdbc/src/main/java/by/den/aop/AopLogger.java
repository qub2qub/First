package by.den.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AopLogger {

    public void initLog() {
        System.out.println("method started");
    }
    public void closeLog() {
        System.out.println("method finished");
    }
    public void exceptLog(Throwable exc) {
        System.out.println("exception logged: "+exc.getClass().getName());
    }
    public void objLog(Object obj) {
        System.out.println("Log obj="+obj);
    }

    public Object aroundTimeLog(ProceedingJoinPoint call) {
        System.out.println("Time for method: "+call.getSignature().toShortString());
        StopWatch stopWatch = new StopWatch();
        Object output = null;
        try {
            stopWatch.start();
            output = call.proceed();
//            System.out.println("Skip call");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            stopWatch.stop();
            System.out.println("time="+stopWatch.shortSummary());
        }
        return output;
    }

}
