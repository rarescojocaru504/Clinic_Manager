package Repository;

import Domain.Appointment;
import Domain.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentBinaryRepository extends FileRepository<Integer, Appointment>{
    public AppointmentBinaryRepository(String path) {
        super(path);
        //file_out();
    }


    @Override
    public void file_in() {
        File file = new File(this.path);
        if (file.length() == 0) {
            this.entities = new HashMap<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path))) {
            this.entities = (HashMap<Integer, Appointment>) ois.readObject();
        } catch (EOFException e) {
            this.entities = new HashMap<>();
            System.out.println("File is empty; initializing with an empty repository.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path, e);
        } catch (IOException e) {
            throw new RuntimeException("I/O error while reading the file: " + path, e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found during deserialization: " + e.getMessage(), e);
        }
    }

    @Override
    public void file_out() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path))) {
            oos.writeObject(this.entities);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void file_out_t(Appointment appointment) {
        file_out();
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
