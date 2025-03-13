package Filters;
import Domain.Patient;

public class HeightFilterPatient implements AbstractFilter<Patient> {
    int height;

    public HeightFilterPatient() {
        height = 0;
    }

    public HeightFilterPatient(int height) {
        this.height = height;
    }

    public boolean accept(Patient patient) {
        return patient.getHeight() == height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
