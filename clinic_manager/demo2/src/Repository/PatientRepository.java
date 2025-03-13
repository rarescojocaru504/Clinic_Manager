package Repository;
import Domain.Appointment;
import Domain.Patient;
import MyExceptions.EmptyRepoException;
import MyExceptions.ID_ExceptionNotFound;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PatientRepository extends MemoryRepository<Integer, Patient> {

    public List<Patient> report1(){
        System.out.println("Report 1 _____ Patients with height > 186 sorted by age in descending order");
        List<Patient> report = getAll().stream()
                .filter(p -> p.getHeight() > 186)
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .toList();
        return report;
    }

    public List<Patient> report3(){
        System.out.println("Report 3 _____ Patients with age > 18 sorted by age in ascending order");
        List<Patient> report = getAll().stream()
                .filter(p -> p.getAge() > 18)
                .sorted(Comparator.comparingInt(Patient::getAge))
                .toList();
        return report;
    }
    public List<String> report4(){
        System.out.println("Report 4 _____ Patients' names with age above 18 sorted by height in ascending order");
        List<String> report = getAll().stream()
                .filter(p -> p.getAge() > 18)
                .sorted(Comparator.comparingInt(Patient::getHeight))
                .map(Patient::getName)
                .toList();
        return report;
    }
    public List<String> report5(){
        System.out.println("Report 5 _____ Patients' name with height > 200cm sorted by name");
        List<String> report = getAll().stream()
                .filter(p -> p.getHeight() > 200)
                .sorted((a,b)->{
                    String n1 = a.getName();
                    String n2 = b.getName();
                    return n1.compareTo(n2);
                })
                .map(Patient::getName)
                .toList();
        return report;

    }
}
