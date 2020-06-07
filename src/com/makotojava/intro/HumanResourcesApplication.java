package com.makotojava.intro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.makotojava.intro.Person.Gender;

public class HumanResourcesApplication {
  private static final Logger log = Logger.getLogger(HumanResourcesApplication.class.getName());
  private static final String SOURCE_CLASS = HumanResourcesApplication.class.getName();

  private List<Employee> employeeDatabase;

  public List<Employee> getEmployees() {
    if (employeeDatabase == null) {
      employeeDatabase = createEmployees();
    }
    return employeeDatabase;
  }

  public static void main(String[] args) {
    // Create List of Employees
    List<Employee> employees = createEmployees();
    HumanResourcesApplication application = new HumanResourcesApplication();
    application.serializeToDisk("employees.ser", employees);
    // Save (serialize) to disk
    application.saveToDisk("employees.txt", employees);
    // Read from disk
    employees = application.readFromDisk("employees.txt");
    employees = application.deserializeFromDisk("employees.ser");
    // Use a Scanner class here to implement a crude interface
  }

  public void handleStockOptions(final Person person, StockOptionProcessingCallback callback) {
    if (person instanceof StockOptionEligible) {
      // Eligible Person, invoke the callback straight up
      callback.process((StockOptionEligible) person);
    } else if (person instanceof Employee) {
      // Not eligible, but still an Employee. Let's cobble up a
      /// anonymous inner class implementation for this
      callback.process(new StockOptionEligible() {
        @Override
        public void awardStockOptions(int number, BigDecimal price) {
          // This employee is not eligible
          log.warning("It would be nice to award " + number + " of shares at $" +
              price.setScale(2, RoundingMode.HALF_UP).toPlainString() +
              ", but unfortunately, Employee " + person.getName() +
              " is not eligible for Stock Options!");
        }
      });
    } else {
      callback.process(new StockOptionEligible() {
        @Override
        public void awardStockOptions(int number, BigDecimal price) {
          log.severe("Cannot consider awarding " + number + " of shares at $" +
              price.setScale(2, RoundingMode.HALF_UP).toPlainString() +
              ", because " + person.getName() +
              " does not even work here!");
        }
      });
    }
  }

  public static List<Person> createPeople() {
    List<Person> ret = new ArrayList<>();
    // First create and add some people
    Person p = new Person("Jane Doe", 24, 150, 50, "BLUE", Gender.FEMALE);
    ret.add(p);
    //
    p = new Person("Peter Parker", 34, 175, 76, "GREEN", Gender.MALE);
    ret.add(p);
    // Now create some Employees
    ret.addAll(createEmployees());
    // Now create some Managers
    ret.addAll(createManagers());
    return ret;
  }

  /**
   * Canned method to create Employees that will be part of the
   * Human Resources "database". For purely teaching purposes only.
   * Once we create a List of Employees we can do lots of different
   * things with it: write out to disk (then read back in), use for
   * a search feature, etc.
   * 
   * @return List<Employee> - List of Employee objects
   */
  public static List<Employee> createEmployees() {
    List<Employee> ret = new ArrayList<>();
    Employee e = new Employee("Jon Smith", 45, 175, 75, "BLUE", Gender.MALE, "123-45-9999", "0001",
        BigDecimal.valueOf(100000.0));
    ret.add(e);
    //
    e = new Employee("Jon Jones", 40, 185, 85, "BROWN", Gender.MALE, "223-45-9999", "0002",
        BigDecimal.valueOf(110000.0));
    ret.add(e);
    //
    e = new Employee("Mary Smith", 35, 155, 55, "GREEN", Gender.FEMALE, "323-45-9999", "0003",
        BigDecimal.valueOf(120000.0));
    ret.add(e);
    //
    e = new Employee("Chris Johnson", 38, 165, 65, "HAZEL", Gender.UNKNOWN, "423-45-9999", "0004",
        BigDecimal.valueOf(90000.0));
    ret.add(e);
    //
    // e = new Employee("Christine Johnson", 33, 160, 60, "BROWN", Gender.FEMALE, "424-45-9999", "0005",
    // BigDecimal.valueOf(190000.0));
    // ret.add(e);
    // Return list of Employees
    return ret;
  }

  public static List<Manager> createManagers() {
    List<Manager> ret = new ArrayList<>();
    Manager m = new Manager("Clancy Snodgrass", 45, 180, 90, "BLUE", Gender.MALE, "456-45-9999", "0100",
        BigDecimal.valueOf(245000.0));
    ret.add(m);
    //
    m = new Manager("Mary Snodgrass", 44, 170, 55, "BLUE", Gender.FEMALE, "567-45-9999", "0101",
        BigDecimal.valueOf(250000.0));
    ret.add(m);
    return ret;
  }

  public boolean serializeToDisk(String filename, List<Employee> employees) {
    final String METHOD_NAME = "serializeToDisk(String filename, List<Employee> employees)";

    boolean ret = false;// default: failed
    File file = new File(filename);
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
      log.info("Writing " + employees.size() + " employees to disk (using Serializable)...");
      outputStream.writeObject(employees);
      ret = true;
      log.info("Done.");
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    }
    return ret;
  }

  public boolean saveToDisk(String filename, List<Employee> employees) {
    final String METHOD_NAME = "saveToDisk(String filename, List<Employee> employees)";

    boolean ret = false;
    File file = new File(filename);
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
      log.info("Writing " + employees.size() + " employees to disk (as String)...");
      for (Employee employee : employees) {
        writer.write(employee.getState() + "\n");
      }
      ret = true;
      log.info("Done.");
    } catch (FileNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(),
          e);
    }
    return ret;
  }

  public boolean saveToDiskBuffered(String filename, List<Employee> employees) {
    final String METHOD_NAME = "saveToDisk(String filename, List<Employee> employees)";

    boolean ret = false;
    File file = new File(filename);
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
      log.info("Writing " + employees.size() + " employees to disk (as String)...");
      for (Employee employee : employees) {
        writer.write(employee.getState() + "\n");
      }
      ret = true;
      log.info("Done.");
    } catch (FileNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(),
          e);
    }
    return ret;
  }

  public List<Employee> readFromDisk(String filename) {
    final String METHOD_NAME = "readFromDisk(String filename)";
    List<Employee> ret = new ArrayList<>();
    File file = new File(filename);
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
      StringBuilder sb = new StringBuilder();
      int numberOfEmployees = 0;
      int character = reader.read();
      while (character != -1) {
        sb.append((char) character);
        character = reader.read();
      }
      log.info("Read file: \n" + sb.toString());
      int index = 0;
      while (index < sb.length() - 1) {
        StringBuilder line = new StringBuilder();
        while (sb.charAt(index) != '\n') {
          line.append(sb.charAt(index++));
        }
        StringTokenizer strtok = new StringTokenizer(line.toString(), Person.STATE_DELIMITER);
        Employee employee = new Employee();
        employee.setState(strtok);
        log.info("Read Employee: " + employee.toString());
        ret.add(employee);
        numberOfEmployees++;
        index++;
      }
      log.info("Read " + numberOfEmployees + " employees from disk.");
    } catch (FileNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(),
          e);
    }
    return ret;
  }

  public List<Employee> readFromDiskBuffered(String filename) {
    final String METHOD_NAME = "readFromDisk(String filename)";
    List<Employee> ret = new ArrayList<>();
    File file = new File(filename);
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
      String line = reader.readLine();
      int numberOfEmployees = 0;
      while (line != null) {
        StringTokenizer strtok = new StringTokenizer(line, Person.STATE_DELIMITER);
        Employee employee = new Employee();
        employee.setState(strtok);
        log.info("Read Employee: " + employee.toString());
        ret.add(employee);
        numberOfEmployees++;
        // Read next line
        line = reader.readLine();
      }
      log.info("Read " + numberOfEmployees + " employees from disk.");
    } catch (FileNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(),
          e);
    }
    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Employee> deserializeFromDisk(String filename) {
    final String METHOD_NAME = "deserializeFromDisk(String filename)";

    List<Employee> ret = new ArrayList<>();
    File file = new File(filename);
    int numberOfEmployees = 0;
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
      List<Employee> employees = (List<Employee>) inputStream.readObject();
      log.info("Deserialized List says it contains " + employees.size() + " objects...");
      for (Employee employee : employees) {
        log.info("Read Employee: " + employee.toString());
        numberOfEmployees++;
      }
      ret = employees;
      log.info("Read " + numberOfEmployees + " employees from disk.");
    } catch (FileNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME,
          "Cannot find file " + file.getName() + ", message = " + e.getLocalizedMessage(), e);
    } catch (IOException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(),
          e);
    } catch (ClassNotFoundException e) {
      log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "ClassNotFoundException, message = " + e.getLocalizedMessage(),
          e);
    }
    return ret;
  }

}
