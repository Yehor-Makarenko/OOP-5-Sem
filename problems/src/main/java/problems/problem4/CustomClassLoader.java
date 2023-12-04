package problems.problem4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CustomClassLoader extends ClassLoader {
  public Class<?> loadClassFromFile(String className, String path) throws ClassNotFoundException {
    try {
      File file = new File(path);
      byte[] classData = Files.readAllBytes(file.toPath());
      return defineClass(className, classData, 0, classData.length);
    } catch (IOException e) {
      throw new ClassNotFoundException("Failed to load class " + className, e);
    }
  }
}
