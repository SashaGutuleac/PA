package org.example.homework12;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Homework12Application {

    public static void main(String[] args) throws Exception {

        File inputFolder = new File(Homework12Application.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        System.out.println("📂 Scanam folderul: " + inputFolder.getAbsolutePath() + "\n");

        List<Class<?>> discoveredAnnotations = new ArrayList<>();
        List<Class<?>> discoveredPublicClasses = new ArrayList<>();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{inputFolder.toURI().toURL()});
        exploreFolder(inputFolder, inputFolder, classLoader, discoveredAnnotations, discoveredPublicClasses);

        System.out.println("--- ADNOTARI GASITE ---");
        for (Class<?> ann : discoveredAnnotations) {
            System.out.println("@" + ann.getName());
        }

        System.out.println("\n--- CLASE PUBLICE SI PROTOTIPURI ---");
        for (Class<?> clazz : discoveredPublicClasses) {
            System.out.println("public class " + clazz.getSimpleName() + " {");
            for (Method m : clazz.getDeclaredMethods()) {
                System.out.println("  " + Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName() + "(...)");
            }
            System.out.println("}");

            invokeTargetMethods(clazz, discoveredAnnotations);
            System.out.println();
        }
    }

    private static void exploreFolder(File root, File currentFile, URLClassLoader classLoader,
                                      List<Class<?>> annotations, List<Class<?>> publicClasses) {
        if (currentFile.isDirectory()) {
            for (File child : currentFile.listFiles()) {
                exploreFolder(root, child, classLoader, annotations, publicClasses);
            }
        } else if (currentFile.getName().endsWith(".class")) {
            try {
                String relativePath = currentFile.getAbsolutePath().substring(root.getAbsolutePath().length() + 1);
                String className = relativePath.replace("\\", ".").replace("/", ".").replace(".class", "");

                Class<?> loadedClass = classLoader.loadClass(className);

                if (loadedClass.isAnnotation()) {
                    annotations.add(loadedClass);
                } else if (Modifier.isPublic(loadedClass.getModifiers())) {
                    publicClasses.add(loadedClass);
                }
            } catch (Exception e) {
                // Ignoram clasele care nu pot fi incarcate
            }
        }
    }

    private static void invokeTargetMethods(Class<?> clazz, List<Class<?>> targetAnnotations) {
        try {
            Object instance = null;
            boolean isInstantiated = false;

            for (Method m : clazz.getDeclaredMethods()) {
                boolean hasTargetAnnotation = false;
                for (Annotation ann : m.getAnnotations()) {
                    if (targetAnnotations.contains(ann.annotationType())) {
                        hasTargetAnnotation = true;
                        break;
                    }
                }

                if (hasTargetAnnotation) {
                    if (!isInstantiated) {
                        instance = clazz.getDeclaredConstructor().newInstance();
                        isInstantiated = true;
                    }

                    if (m.getParameterCount() == 0) {
                        m.invoke(instance);
                    } else if (m.getParameterCount() == 1 && (m.getParameterTypes()[0] == int.class || m.getParameterTypes()[0] == Integer.class)) {
                        m.invoke(instance, 42);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Eroare la invocarea metodelor pentru " + clazz.getSimpleName() + ": " + e.getMessage());
        }
    }
}