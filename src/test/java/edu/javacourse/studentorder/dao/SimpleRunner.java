package edu.javacourse.studentorder.dao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {
  public static void main(String[] args) {
    SimpleRunner sr = new SimpleRunner();

    sr.runTest();
  }

  private void runTest() {
    try {
      Class<?> clazz = Class.forName("edu.javacourse.studentorder.dao.StudentOrderDaoImplTest");
      Constructor<?> clazzConstructor = clazz.getConstructor();
      Object object = clazzConstructor.newInstance();

      for (Method method : clazz.getMethods()) {
        Test annotation = method.getAnnotation(Test.class);
        if (annotation != null) {
          method.invoke(object);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
