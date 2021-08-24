package ink.kangaroo.common.core.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/23 9:15
 */
@Slf4j
@Aspect
@Component
public class CodeCastAop {

    @Pointcut("@annotation(ink.kangaroo.common.core.annotation.CodeCast)")
    public void countTime(){

    }
    @Around("countTime()")
    public Object doAround(ProceedingJoinPoint joinPoint){

        Object obj = null;
        try {
            long beginTime = System.currentTimeMillis();

            obj = joinPoint.proceed();
            //获取方法名称
            String methodName = joinPoint.getSignature().getName();
            //获取类名称
            String className = joinPoint.getSignature().getDeclaringTypeName();
            log.info("类:[{}]，方法:[{}]耗时时间为:[{}]", className, methodName, System.currentTimeMillis() - beginTime + "毫秒");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;

    }
}
