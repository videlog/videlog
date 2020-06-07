package com.makotojava.intro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Employee extends Person implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2791635132932135483L;
  
  private String tin;
  private String employeeNumber;
  private BigDecimal salary;
  
  public Employee() {
    super();
  }

  public Employee(String name, int age, int height, int weight, String eyeColor, Gender gender) {
    super(name, age, height, weight, eyeColor, gender);
  }
  
  public Employee(String name, int age, int height, int weight, String eyeColor, Gender gender, String tin, String employeeNumber, BigDecimal salary) {
    this(name, age, height, weight, eyeColor, gender);
    this.tin = tin;
    this.employeeNumber = employeeNumber;
    this.salary = salary;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getEmployeeNumber() {
    return employeeNumber;
  }

  public void setEmployeeNumber(String employeeNumber) {
    this.employeeNumber = employeeNumber;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  @Override
  public void printAudit(StringBuilder buffer) {
    // Call the superclass version of this method first to get its attribute values
    super.printAudit(buffer);
    // Now format this instance's values
    buffer.append("Tin=");
      buffer.append(getTin());
    buffer.append(","); buffer.append("EmployeeNumber=");
      buffer.append(getEmployeeNumber());
    buffer.append(","); buffer.append("Salary=");
      buffer.append(getSalary().setScale(2).toPlainString());
  }

  @Override
  public String toString() {
    return super.toString() + 
        "Employee [tin=" + tin + ", employeeNumber=" + employeeNumber
        + ", salary=" + salary + "]";
  }
  
  public String getState() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.getState());sb.append(STATE_DELIMITER);
    sb.append(tin);sb.append(STATE_DELIMITER);
    sb.append(employeeNumber);sb.append(STATE_DELIMITER);
    sb.append(salary);
    return sb.toString();
  }
  
  public void setState(StringTokenizer strtok) {
    super.setState(strtok);
    String tin = strtok.nextToken();
    setTin(tin);
    String employeeNumber = strtok.nextToken();
    setEmployeeNumber(employeeNumber);
    Double salary = Double.parseDouble(strtok.nextToken());
    setSalary(BigDecimal.valueOf(salary));
  }

}
