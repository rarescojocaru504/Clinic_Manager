package Repository;

import Domain.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppointmentRepository extends MemoryRepository<Integer, Appointment> {

    public AppointmentRepository() {
        super();
    }

    public void deleteByPatientId(Integer patientId) {
        List<Integer> id_list = new ArrayList<>();
        for (Appointment appointment : this.getAll()) {
            if (appointment.getPatientId().equals(patientId)) {
                id_list.add(appointment.get_id());
            }
        }
        for(Integer id : id_list) {
            super.delete(id);
        }
    }
    public boolean isPatientId(Integer patientId) {
        for (Appointment appointment : this.getAll()) {
            if (appointment.getPatientId().equals(patientId)) {
                return true;
            }
        }
        return false;
    }

    public List<Appointment> report2(LocalDate inputDate){
            System.out.println("Report 2 _____ Appointments from input date sorted by title:");
            List<Appointment> report = this.getAll().stream().filter(a -> ((Appointment) a).getDate().isEqual(inputDate))
                    .sorted(Comparator.comparing(a -> ((Appointment) a).getTitle()))
                    .toList();
            return report;
    }


}
