package com.makotojava.intro;

import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Person {
  
  public static final String STATE_DELIMITER = "~";
  
  public Person() {
    // Default constructor
  }
  
  public enum Gender {
    MALE,
    FEMALE,
    UNKNOWN
  }
  
  public Person(String name, int age, int height, int weight, String eyeColor, Gender gender) {
    this.name = name;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.eyeColor = eyeColor;
    this.gender = gender;
  }

  private String name;
  private int age;
  private int height;
  private int weight;
  private String eyeColor;
  private Gender gender;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getEyeColor() {
    return eyeColor;
  }

  public void setEyeColor(String eyeColor) {
    this.eyeColor = eyeColor;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void printAudit(StringBuilder buffer) {
    buffer.append("Name="); buffer.append(getName());
    buffer.append(","); buffer.append("Age="); buffer.append(getAge());
    buffer.append(","); buffer.append("Height="); buffer.append(getHeight());
    buffer.append(","); buffer.append("Weight="); buffer.append(getWeight());
    buffer.append(","); buffer.append("EyeColor="); buffer.append(getEyeColor());
    buffer.append(","); buffer.append("Gender="); buffer.append(getGender());
  }
  
  public void printAudit(Logger l) {
    StringBuilder sb = new StringBuilder();
    printAudit(sb);
    l.info(sb.toString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + age;
    result = prime * result + ((eyeColor == null) ? 0 : eyeColor.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + height;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + weight;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (age != other.age)
      return false;
    if (eyeColor == null) {
      if (other.eyeColor != null)
        return false;
    } else if (!eyeColor.equals(other.eyeColor))
      return false;
    if (gender == null) {
      if (other.gender != null)
        return false;
    } else if (!gender.equals(other.gender))
      return false;
    if (height != other.height)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (weight != other.weight)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight + ", eyeColor=" + eyeColor
        + ", gender=" + gender + "]";
  }
  
  public String getState() {
    StringBuilder sb = new StringBuilder();
    sb.append(name);sb.append(STATE_DELIMITER);
    sb.append(age);sb.append(STATE_DELIMITER);
    sb.append(height);sb.append(STATE_DELIMITER);
    sb.append(weight);sb.append(STATE_DELIMITER);
    sb.append(eyeColor);
    return sb.toString();
  }
  
  public void setState(StringTokenizer strtok) {
    String name = strtok.nextToken();
    setName(name);
    Integer age = Integer.parseInt(strtok.nextToken());
    setAge(age);
    Integer height = Integer.parseInt(strtok.nextToken());
    setHeight(height);
    Integer weight = Integer.parseInt(strtok.nextToken());
    setWeight(weight);
    String eyeColor = strtok.nextToken();
    setEyeColor(eyeColor);
  }
  
}
