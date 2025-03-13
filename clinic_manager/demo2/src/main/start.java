package main;

import Domain.Appointment;
import Domain.Patient;
import Repository.*;
import Service.AppointmentService;
import Service.PatientService;
import Ui.MainUi;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class start {

    public static void main(String[] args) {
        Properties prop = new Properties();
        IRepository<Integer, Patient> patientRepository = null;
        IRepository<Integer, Appointment> appointmentRepository = null;

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
                    patientRepository = new MemoryRepository<>();
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

            MainUi mainUi = new MainUi(patientService, appointmentService);

            if (repoType.equals("mem") || repoType.equals("db")) {
                //addSampleData(patientService, appointmentService);
            }

            mainUi.run();

        } catch (IOException e) {
            System.out.println("Error loading settings.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }
//  private static void addSampleData(IService<Integer, Patient> patientService, IService<Integer, Appointment> appointmentService) {
//        Patient patient1 = new Patient(1, "Cojocaru Rares", 20, 220, 90);
//        Patient patient2 = new Patient(2, "Biro Andrei", 22, 175, 75);
//        Patient patient3 = new Patient(3, "Timbus Teodora", 19, 165, 55);
//        Patient patient4 = new Patient(4, "Michael Brown", 30, 180, 80);
//        Patient patient5 = new Patient(5, "Laura Johnson", 27, 168, 62);
//        Patient patient6 = new Patient(6, "Ethan Smith", 35, 185, 85);
//        Patient patient7 = new Patient(7, "Sophia Williams", 24, 160, 50);
//        Patient patient8 = new Patient(8, "Emma Taylor", 32, 170, 68);
//        Patient patient9 = new Patient(9, "Liam Martinez", 28, 172, 70);
//        Patient patient10 = new Patient(10, "Olivia Davis", 21, 164, 54);
//        Patient patient11 = new Patient(11, "Noah Wilson", 45, 206, 90);
//        Patient patient12 = new Patient(12, "Isabella Moore", 38, 166, 65);
//        Patient patient13 = new Patient(13, "Mason Lee", 50, 205, 95);
//        Patient patient14 = new Patient(14, "Ava Brown", 20, 160, 48);
//        Patient patient15 = new Patient(15, "James White", 29, 174, 72);
//        Patient patient16 = new Patient(16, "Charlotte Harris", 34, 162, 55);
//        Patient patient17 = new Patient(17, "Elijah Clark", 40, 201, 100);
//        Patient patient18 = new Patient(18, "Amelia Walker", 25, 169, 60);
//        Patient patient19 = new Patient(19, "Lucas Young", 31, 176, 78);
//        Patient patient20 = new Patient(20, "Mia Hall", 26, 165, 58);
//
//        patientService.add(patient1);
//        patientService.add(patient2);
//        patientService.add(patient3);
//        patientService.add(patient4);
//        patientService.add(patient5);
//        patientService.add(patient6);
//        patientService.add(patient7);
//        patientService.add(patient8);
//        patientService.add(patient9);
//        patientService.add(patient10);
//        patientService.add(patient11);
//        patientService.add(patient12);
//        patientService.add(patient13);
//        patientService.add(patient14);
//        patientService.add(patient15);
//        patientService.add(patient16);
//        patientService.add(patient17);
//        patientService.add(patient18);
//        patientService.add(patient19);
//        patientService.add(patient20);
//
//        LocalDate date1 = LocalDate.of(2024, 11, 6);
//        LocalDate date2 = LocalDate.of(2024, 11, 7);
//        LocalDate date3 = LocalDate.of(2024, 11, 8);
//        LocalDate date4 = LocalDate.of(2024, 11, 9);
//        LocalDate date5 = LocalDate.of(2024, 11, 10);
//
//        Appointment appointment1 = new Appointment(1, "Check-up", date1, 1);
//        Appointment appointment2 = new Appointment(2, "Consultation", date2, 2);
//        Appointment appointment3 = new Appointment(3, "Dental Cleaning", date3, 3);
//        Appointment appointment4 = new Appointment(4, "Orthodontic Consultation", date4, 4);
//        Appointment appointment5 = new Appointment(5, "Eye Check-up", date5, 5);
//        Appointment appointment6 = new Appointment(6, "General Check-up", date1, 6);
//        Appointment appointment7 = new Appointment(7, "Follow-up", date2, 7);
//        Appointment appointment8 = new Appointment(8, "Vaccination", date3, 8);
//        Appointment appointment9 = new Appointment(9, "Annual Physical", date4, 9);
//        Appointment appointment10 = new Appointment(10, "Nutritional Counseling", date5, 10);
//        Appointment appointment11 = new Appointment(11, "Cardiology Consultation", date1, 11);
//        Appointment appointment12 = new Appointment(12, "Physical Therapy", date2, 12);
//        Appointment appointment13 = new Appointment(13, "Pediatric Check-up", date3, 13);
//        Appointment appointment14 = new Appointment(14, "Allergy Testing", date4, 14);
//        Appointment appointment15 = new Appointment(15, "Flu Shot", date5, 15);
//        Appointment appointment16 = new Appointment(16, "Chiropractic Adjustment", date1, 16);
//        Appointment appointment17 = new Appointment(17, "Lab Testing", date2, 17);
//        Appointment appointment18 = new Appointment(18, "Skin Consultation", date3, 18);
//        Appointment appointment19 = new Appointment(19, "ENT Consultation", date4, 19);
//        Appointment appointment20 = new Appointment(20, "Mental Health Check", date5, 20);
//
//        appointmentService.add(appointment1);
//        appointmentService.add(appointment2);
//        appointmentService.add(appointment3);
//        appointmentService.add(appointment4);
//        appointmentService.add(appointment5);
//        appointmentService.add(appointment6);
//        appointmentService.add(appointment7);
//        appointmentService.add(appointment8);
//        appointmentService.add(appointment9);
//        appointmentService.add(appointment10);
//        appointmentService.add(appointment11);
//        appointmentService.add(appointment12);
//        appointmentService.add(appointment13);
//        appointmentService.add(appointment14);
//        appointmentService.add(appointment15);
//        appointmentService.add(appointment16);
//        appointmentService.add(appointment17);
//        appointmentService.add(appointment18);
//        appointmentService.add(appointment19);
//        appointmentService.add(appointment20);
//
//        System.out.println("Sample data added.");
//    }
}