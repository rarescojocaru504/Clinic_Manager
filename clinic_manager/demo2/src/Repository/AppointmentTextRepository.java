package Repository;

import Domain.Appointment;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentTextRepository extends FileRepository<Integer, Appointment> {
    public AppointmentTextRepository(String path) {
        super(path);
    }

    @Override
    public void file_in() {
        try(BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 4) {
                    continue;
                }
                Integer id = Integer.parseInt(tokens[0]);
                String title = tokens[1];
                LocalDate date = LocalDate.parse(tokens[2]);
                Integer patientId = Integer.parseInt(tokens[3]);
                Appointment appointment = new Appointment(id, title, date, patientId);
                super.add(appointment);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void file_out() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))) {
            Iterable<Appointment> appointments = super.getAll();
            for (Appointment appointment : appointments) {
                bw.write(appointment.get_id() + "," + appointment.getTitle() + "," + appointment.getDate() + "," + appointment.getPatientId());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void file_out_t(Appointment appointment) {
        file_out();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))) {
            bw.write("\nLast Search results : " + appointment+"\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByPatientId(Integer patientId) {
        List<Integer> idsToRemove = new ArrayList<>();
        for (Appointment appointment : this.getAll()) {
            if (appointment.getPatientId().equals(patientId)) {
                idsToRemove.add(appointment.get_id());
            }
        }

        for (Integer id : idsToRemove) {
            super.delete(id);
        }
        file_out();
    }
}
