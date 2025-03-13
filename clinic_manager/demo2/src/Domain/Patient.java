package Domain;

import java.io.Serializable;

public class Patient implements Comparable<Patient>, Identifiable<Integer>, Serializable {
    private Integer id;
    private String name;
    private int age;
    private int height;
    private int weight;

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

    public Patient() {
        id = -1;
        name = null;
        age = 0;
        height = 0;
        weight = 0;
    }

    public Patient(Integer id, String name, int age, int height, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(Patient patient) {
        return this.name.compareTo(patient.name);
    }

    @Override
    public Integer get_id() {
        return id;
    }

    @Override
    public void set_id(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Patient) obj).id);
    }

    @Override
    public String toString() {
        String string = "||| ID: " + this.id + " | Name: " + this.name + " | Age: " + this.age + " | Height: " + this.height + " cm " + " | Weight: " + this.weight + " kg ";
        return string;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

