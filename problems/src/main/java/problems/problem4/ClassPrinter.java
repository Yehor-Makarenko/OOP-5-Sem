package problems.problem4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class ClassPrinter {
  public static void printClassDescription(String className, String classFilePath) {
    CustomClassLoader classLoader = new CustomClassLoader();
    Class<?> loadedClass = null;
    try {
      loadedClass = classLoader.loadClassFromFile(className, classFilePath);
    } catch (ClassNotFoundException e) {      
      e.printStackTrace();
    }
    
    System.out.println("Class Name: " + loadedClass.getSimpleName());
    
    System.out.println("Fields:");
    for (Field field : loadedClass.getDeclaredFields()) {
      System.out.println("  " + field.getType().getSimpleName() + " " + field.getName());
    }

    System.out.println("Constructors:");
    for (Constructor<?> constructor : loadedClass.getDeclaredConstructors()) {
      System.out.print("  " + loadedClass.getSimpleName() + "(");  
      Parameter[] parameters = constructor.getParameters();          
      for (int i = 0; i < constructor.getParameterCount(); i++) {
        if (i == constructor.getParameterCount() - 1) {
          System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());  
        } else {
          System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName() + ", ");
        }        
      }
      System.out.println(")");
    }

    System.out.println("Methods:");
    for (Method method : loadedClass.getDeclaredMethods()) {
      System.out.print("  " + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
      Parameter[] parameters = method.getParameters();          
      for (int i = 0; i < method.getParameterCount(); i++) {
        if (i == method.getParameterCount() - 1) {
          System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());  
        } else {
          System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName() + ", ");
        }        
      }
      System.out.println(")");
    }    
  }
}
