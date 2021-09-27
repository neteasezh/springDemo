package com.wust.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspect {
    /*
    切入点表达式：修饰符 返回值 目标方法
    public int com.wust.aop.Calculator.*(..) 某个类的任意方法任意参数
    public int com.wust.aop.Calculator.div(int,int) 精确到某个类的某个方法
    */
    //抽取公共的切入点表达式
    @Pointcut("execution(public int com.wust.aop.Calculator.*(..))")
    public void pointCut(){}

    /**
     * @param joinPoint:切入点（其实就是将目标方法封装成了切入点）
     */
    @Before("pointCut()")
    public void logBefore(JoinPoint joinPoint){
        //获得目标方法的参数值
        Object[] args = joinPoint.getArgs();
        //获得目标方法的方法签名
        Signature signature = joinPoint.getSignature();
        //获得目标方法名
        String name = signature.getName();

        System.out.println(name+"方法运行...参数列表是："+ Arrays.asList(args));
    }

    @After("pointCut()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"结束...");
    }
    //returning = "res"绑定参数，将返回值赋值给对应的方法参数res
    //如果在方法中有多个参数，必须将参数joinPoint放在第一的位置上，否则会报错
    @AfterReturning(value = "pointCut()",returning = "res")
    public void logReturn(JoinPoint joinPoint,Object res){
        System.out.println(joinPoint.getSignature().getName()+"正常返回...运行结果为："+ res);
    }

    //throwing = "e"绑定参数,将异常信息返回给方法参数e
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void logException(JoinPoint joinPoint,Exception e){
        System.out.println(joinPoint.getSignature().getName()+"异常...异常信息为："+e);
    }
}
