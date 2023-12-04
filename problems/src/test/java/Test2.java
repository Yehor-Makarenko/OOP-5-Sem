import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import problems.problem4.CustomClassLoader;

public class Test2 {
  @Test
  public void testClassLoader() {
    try {
      CustomClassLoader classLoader = new CustomClassLoader();
      Class<?> personClass = classLoader.loadClassFromFile("problems.problem1.person.Person", "target\\classes\\problems\\problem1\\person\\Person.class");
      assertNotEquals(null, personClass);
    } catch(ClassNotFoundException e) {
      assertEquals(0, 1);
    }
  }

  @Test
  public void testClassData() {    
    try {
      CustomClassLoader classLoader = new CustomClassLoader();
      Class<?> personClass = classLoader.loadClassFromFile("problems.problem1.person.Person", "target\\classes\\problems\\problem1\\person\\Person.class");
      assertNotEquals(null, personClass.getDeclaredField("name"));
      assertNotEquals(null, personClass.getDeclaredField("age"));
    } catch (Exception e) {      
      assertEquals(0, 1);
    }

  }
}
