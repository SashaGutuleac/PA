package org.example.compulsory12;

import java.lang.reflect.Method;

public class Compulsory12Application {

    public static void main(String[] args) {
        String className = "org.example.compulsory12.MyTestClass";

        try {
            Class<?> targetClass = Class.forName(className);

            Method runMethod = targetClass.getMethod("run");

            Object instance = targetClass.getDeclaredConstructor().newInstance();
            runMethod.invoke(instance);

        } catch (NoSuchMethodException e) {
            System.out.println("Clasa specificata NU contine o metoda numita 'run' fara argumente.");
        } catch (Exception e) {
            System.out.println(" A aparut o eroare neasteptata: " + e.getMessage());
        }
    }
}