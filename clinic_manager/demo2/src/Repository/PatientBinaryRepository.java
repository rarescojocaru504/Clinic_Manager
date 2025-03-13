package Repository;

import Domain.Patient;

import java.io.*;
import java.util.HashMap;

public class PatientBinaryRepository extends FileRepository<Integer, Patient> {
    public PatientBinaryRepository(String path) {

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
            this.entities = (HashMap<Integer, Patient>) ois.readObject();
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
    public void file_out_t(Patient patient) {
    file_out();
    }
}
