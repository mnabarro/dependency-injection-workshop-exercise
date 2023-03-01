package app.dependencies;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class RegistrationsReader {

  private final Properties properties = new Properties();

  public RegistrationsReader() {
    try {
      properties.loadFromXML(new FileInputStream("src/main/resources/properties.xml"));
    } catch (Exception e) {
      System.out.println("Configuration file could not be found");
    }
  }

  public List<Registration> getRegistrations() {
    return properties.entrySet().stream().map(this::mapEntryToRegistration).toList();
  }

  private Registration mapEntryToRegistration(Map.Entry<Object, Object> entry) {
    String type = entry.getKey().toString();
    String implementation = entry.getValue().toString();

    return new Registration(findClass(type), findClass(implementation));
  }

  private Class<?> findClass(String name) {
    String packageName = "app";
    Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));

    return reflections.getSubTypesOf(Object.class)
      .stream()
      .filter(clazz -> clazz.getName().equals(name))
      .findAny().get();
  }
}
