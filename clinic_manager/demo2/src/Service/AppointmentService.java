package Service;

import Domain.Appointment;
import Domain.Patient;
import MyExceptions.EmptyRepoException;
import Repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService implements IService<Integer, Appointment> {
    IRepository<Integer, Appointment> appointmentRepository;

    public AppointmentService(MemoryRepository<Integer, Appointment> appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void add(Appointment appointment) {
        appointmentRepository.add(appointment);
    }

    @Override
    public void modify(Integer id, Appointment appointment) {
        appointmentRepository.modify(id, appointment);
    }

    @Override
    public void delete(Integer id) {
        appointmentRepository.delete(id);
    }

    @Override
    public Appointment find_by_id(Integer id) {
        return appointmentRepository.find_by_id(id);
    }

    @Override
    public ArrayList<Appointment> getAll() {
        return (ArrayList<Appointment>) appointmentRepository.getAll();
    }

    @Override
    public void display() {
        appointmentRepository.display();
    }

    @Override
    public boolean isEmpty() {
        return appointmentRepository.isEmpty();
    }

    public List<Appointment> report2(LocalDate date) {
        List<Appointment> report = ( (AppointmentRepository) appointmentRepository ).report2(date);
        return report;
    }
    public void deleteByPatientId(Integer patientId) {
            try {
                if (appointmentRepository instanceof AppointmentTextRepository) {
                    ((AppointmentTextRepository) appointmentRepository).deleteByPatientId(patientId);
                }else if(appointmentRepository instanceof AppointmentBinaryRepository){
                    ((AppointmentBinaryRepository) appointmentRepository).deleteByPatientId(patientId);
                } else if (appointmentRepository instanceof AppointmentRepository) {
                    ((AppointmentRepository) appointmentRepository).deleteByPatientId(patientId);
                } else if (appointmentRepository instanceof AppointmentDBRepository) {
                    //do nothing
                }

                else {
                    throw new UnsupportedOperationException("The repository does not support deleting appointments by patient ID.");
                }
            }catch(EmptyRepoException e){
                System.out.println(e.getMessage());
            }

    }
    public ArrayList<Appointment> getAll3(){return ((AppointmentRepository)appointmentRepository).getAll();}
}

