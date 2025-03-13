package gui;

import Domain.Appointment;
import Domain.Patient;
import Repository.*;
import Service.AppointmentService;
import Service.PatientService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        IRepository<Integer, Patient>  patientRepository = null;
        IRepository<Integer, Appointment> appointmentRepository = null;
        Properties prop = new Properties();
            try {

                prop.load(new FileReader("src/main/settings.properties"));
                String repoType = prop.getProperty("repository");
                String patientPath = prop.getProperty("patientPath");
                String appointmentPath = prop.getProperty("appointmentPath");
                String patientBinPath = prop.getProperty("patientBinPath");
                String appointmentBinPath = prop.getProperty("appointmentBinPath");
                String dbUrl = prop.getProperty("dbUrl");

                System.out.println("Repository type from properties: " + repoType);

                switch (repoType) {
                    case "txt":
                        patientRepository = new PatientTextRepository(patientPath);
                        appointmentRepository = new AppointmentTextRepository(appointmentPath);
                        break;
                    case "bin":
                        patientRepository = new PatientBinaryRepository(patientBinPath);
                        appointmentRepository = new AppointmentBinaryRepository(appointmentBinPath);
                        break;
                    case "mem":
                        patientRepository = new PatientRepository();
                        appointmentRepository = new AppointmentRepository();
                        break;
                    case "db":
                        if (dbUrl == null || dbUrl.trim().isEmpty()) {
                            throw new IllegalArgumentException("Database URL (dbUrl) must be specified in settings.properties.");
                        }
                        patientRepository = new PatientDBRepository(dbUrl);
                        appointmentRepository = new AppointmentDBRepository(dbUrl);

                        break;
                    default:
                        throw new IllegalArgumentException("Invalid repository type in settings.properties");
                }

                PatientService patientService = new PatientService(patientRepository);
                AppointmentService appointmentService = new AppointmentService((MemoryRepository<Integer, Appointment>) appointmentRepository);
                Controller controller = new Controller(patientService, appointmentService);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUI.fxml"));
                loader.setController(controller);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Dentist Manager");
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void main(String[] args) {

        launch(args);

    }
}
