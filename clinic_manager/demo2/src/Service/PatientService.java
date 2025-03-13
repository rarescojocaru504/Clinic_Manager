package Service;

import Domain.Patient;
import Repository.IRepository;
import Repository.MemoryRepository;
import Repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

public class PatientService implements IService<Integer, Patient> {
    IRepository<Integer,Patient> patientRepository;

    public PatientService(IRepository<Integer,Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public IRepository<Integer, Patient> getPatientRepository() {
        return patientRepository;
    }


    @Override
    public void add(Patient patient) {
        patientRepository.add(patient);
    }

    @Override
    public void modify(Integer id, Patient patient) {
        patientRepository.modify(id, patient);
    }

    @Override
    public void delete(Integer id) {
        patientRepository.delete(id);
    }

    @Override
    public Patient find_by_id(Integer id) {
        return ((PatientRepository)patientRepository).find_by_id(id);
    }

    @Override
    public ArrayList<Patient> getAll() {
        return (ArrayList<Patient>) patientRepository.getAll();
    }

    @Override
    public void display(){
        patientRepository.display();
    }

    @Override
    public boolean isEmpty() {
        return patientRepository.isEmpty();
    }
    public boolean isID(int id){
        return patientRepository.isId(id);
    }

    public List<Patient> report1(){
        List<Patient>report = ((PatientRepository)patientRepository).report1();
        return report;
    }

    public List<Patient> report3(){
        List<Patient>report = ((PatientRepository)patientRepository).report3();
        return report;
    }
    public List<String> report4(){
        List<String> report = ((PatientRepository)patientRepository).report4();
        return report;
    }
    public List<String> report5(){
        List<String> report = ((PatientRepository)patientRepository).report5();
        return report;
    }
}
