package academy.mindswap.aop;

import academy.mindswap.command.client.ClientDto;
import academy.mindswap.model.Client;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        Object proceed = joinPoint.proceed();

        Client toChange = (Client) proceed;
        toChange.setName("Changed");



        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
