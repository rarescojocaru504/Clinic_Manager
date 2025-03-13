package Filters;
import Domain.Patient;

public class WeightFilterPatient implements AbstractFilter<Patient> {
    int weight;

    public WeightFilterPatient() {
        weight = 0;
    }
    public WeightFilterPatient(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean accept(Patient patient) {
        return patient.getWeight() == weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
