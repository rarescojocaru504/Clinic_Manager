package Filters;
import Domain.Patient;

public class AgeFilterPatient implements AbstractFilter<Patient>{
    int age;

    public AgeFilterPatient() {
        age = 0;
    }

    public AgeFilterPatient(int age){
        this.age = age;
    }

    @Override
    public boolean accept(Patient patient) {
        return patient.getAge() > age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
