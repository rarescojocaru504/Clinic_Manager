package Repository;

import Domain.Patient;

import java.io.*;

public class PatientTextRepository extends FileRepository<Integer, Patient> {
    public PatientTextRepository(String path) {
        super(path);
    }
    @Override
    public void file_in(){
        try(BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens.length != 5){
                    continue;
                }
                Integer id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                Integer age = Integer.parseInt(tokens[2]);
                Integer height = Integer.parseInt(tokens[3]);
                Integer weight = Integer.parseInt(tokens[4]);
                Patient patient = new Patient(id, name, age, height, weight);
                super.add(patient);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void file_out(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))){
            Iterable<Patient> patients = this.getAll();
            for(Patient patient : patients){
                bw.write(patient.get_id() + "," + patient.getName() + "," + patient.getAge() + "," + patient.getHeight() + "," + patient.getWeight());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void file_out_t(Patient patient0) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))) {
            bw.write("\nLast Search results : " + patient0+"\n\n");
            Iterable<Patient> patients = this.getAll();
            for(Patient patient : patients){
                bw.write(patient.get_id() + "," + patient.getName() + "," + patient.getAge() + "," + patient.getHeight() + "," + patient.getWeight());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
