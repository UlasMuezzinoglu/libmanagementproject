package ulas.libmanagementproject.aspects;

import jdk.jshell.spi.ExecutionControl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ulas.libmanagementproject.entity.Genre;

@Aspect
@Component
public class GenreServiceAspect {



    @Before("execution(* ulas.libmanagementproject.business.concretes.GenreManager.add(..))")
    public void testAddMethodBefore(JoinPoint joinPoint) {
        var genre = (Genre)joinPoint.getArgs()[0];
        System.out.println("Add Metotundan önce çalıştı !" +" " + genre.getName());

        // return "Metotu Çalıştırma !"; malesef henüz bu işe yaramadı :(
    }
    @After("execution(* ulas.libmanagementproject.business.concretes.GenreManager.add(..))")
    public void testAddMethodAfter(JoinPoint joinPoint){
        var genre = (Genre)joinPoint.getArgs()[0];
        System.out.println("Add Metotundan Sonra çalıştı !" +" " + genre.getName());
    }
}
