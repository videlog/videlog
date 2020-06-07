package com.makotojava.intro;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class Manager extends Employee implements StockOptionEligible {
  /**
   * 
   */
  private static final long serialVersionUID = -9093910124290970326L;
  
  private static final Logger log = Logger.getLogger(Manager.class.getName());
  
  public Manager() {
    super();
  }

  public Manager(String name, int age, int height, int weight, String eyeColor, Gender gender, String tin, String employeeNumber,
      BigDecimal salary) {
    super(name, age, height, weight, eyeColor, gender, tin, employeeNumber, salary);
  }

  public Manager(String name, int age, int height, int weight, String eyeColor, Gender gender) {
    super(name, age, height, weight, eyeColor, gender);
  }

  @Override
  public void awardStockOptions(int number, BigDecimal price) {
    log.info("I can't believe I, " + getName() + ", got " + number + " options at $" + price.toPlainString() + "!");
  }

}
