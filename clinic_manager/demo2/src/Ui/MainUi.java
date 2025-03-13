package Ui;
import Domain.Patient;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import Filters.AgeFilterPatient;
import Repository.FilterRepository;
import Repository.PatientDBRepository;
import Repository.PatientRepository;
import Service.FilterService;
import Filters.WeightFilterPatient;
import Filters.HeightFilterPatient;
import MyExceptions.EmptyRepoException;
import MyExceptions.InvalidException;
import Service.PatientService;
import MyExceptions.ID_ExceptionAlready;
import MyExceptions.ID_ExceptionNotFound;
import Domain.Appointment;
import Service.AppointmentService;
import java.time.DateTimeException;
import java.time.LocalDate;
import MyExceptions.ID_Patient_ExceptionNotFound;
import Filters.DateFilterAppointment;
import Repository.AppointmentRepository;

public class MainUi implements IUi{
    private final Scanner scanner = new Scanner(System.in);
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public MainUi(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void run () {
        boolean run = true;
        while (run) {
            try {
                displayMenu();
                System.out.println("Enter choice: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "0":
                        run = false;
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    case "1":
                        runPMenu();
                        break;
                    case "2":
                        runAMenu();
                        break;
                    case "3":
                        runRMenu();
                        break;
                    default:
                        throw new InvalidException();


                }
            } catch (InvalidException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void displayMenu () {
        System.out.println("\n______MENU______");
        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Appointments");
        System.out.println("3. Get Reports");
        System.out.println("0. Exit\n");
    }
    public void displayMenuR(){
        System.out.println("\n______Reports MENU______");
        System.out.println("1. Get patients with height above 186 sorted by age in descending order");
        System.out.println("2. Get appointments from input date sorted by name");
        System.out.println("3. Get patients with age above 18 sorted by height in ascending order ");
        System.out.println("4. Get patients' name with age above 18 sorted by height in ascending order ");
        System.out.println("5. Get patients' name with height > 200cm sorted by name");
        System.out.println("0. Go back\n");
    }
    public void displayMenuP() {
        System.out.println("\n______Patient_MENU______");
        System.out.println("1. Add Patient");
        System.out.println("2. Delete Patient");
        System.out.println("3. Edit Patient");
        System.out.println("4. Find Patient (BY ID)");
        System.out.println("5. Display All Patients");
        System.out.println("6. Display Patient's Appointments (BY PATIENT ID)");
        System.out.println("7. Filter by age (greater than input age)");
        System.out.println("8. Filter by height(equals input height)");
        System.out.println("9. Filter by weight(equals input weight)");
        System.out.println("0. Go Back");
    }

    public void runPMenu() {
        boolean run = true;
        while (run) {
            displayMenuP();
            System.out.println("Enter choice: ");
            String choice = scanner.nextLine();
            try{
                switch (choice) {
                    case "1":
                        addP();
                        break;
                    case "2":
                        deleteP();
                        break;
                    case "3":
                        editP();
                        break;
                    case "4":
                        findP();
                        break;
                    case "5":
                        displayP();
                        break;
                    case "6":
                        displayAppointmentsP();
                        break;
                    case "7":
                        filterAgeP();
                        break;
                    case "8":
                        filterHeightP();
                        break;
                    case "9":
                        filterWeightP();
                        break;
                    case "0":
                        run = false;
                        break;
                    default:
                        throw new InvalidException();
                }
            }catch(InvalidException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void runAMenu() {
        boolean run = true;
        while(run){
            displayMenuA();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        addA();
                        break;
                    case "2":
                        deleteA();
                        break;
                    case "3":
                        editA();
                        break;
                    case "4":
                        findA();
                        break;
                    case "5":
                        displayA();
                        break;
                    case "6":
                        filterByDateA();
                        break;
                    case "0":
                        run = false;
                        break;
                    default:
                        throw new InvalidException();
                }
            }catch(InvalidException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void runRMenu() {
        boolean run = true;
        while(run){
            displayMenuR();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            try{
                switch (choice) {
                    case "1":
                        op1();
                        break;
                    case "2":
                        op2();
                        break;
                    case "3":
                        op3();
                        break;
                    case "4":
                        op4();
                        break;
                    case "5":
                        op5();
                        break;
                    case "0":
                        run = false;
                        break;
                    default:
                        throw new InvalidException();
                }
            }catch(InvalidException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void displayMenuA() {
        System.out.println("\n______Appointment_MENU______");
        System.out.println("1. Create Appointment");
        System.out.println("2. Delete Appointment");
        System.out.println("3. Edit Appointment");
        System.out.println("4. Find Appointment (BY ID)");
        System.out.println("5. Display All Appointments");
        System.out.println("6. Filter by date (after input date)");
        System.out.println("0. Go Back");
    }

    public void addP() {
        try {
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Name: ");
            String name = scanner.nextLine();

            System.out.println("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Height: ");
            int height = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Weight: ");
            int weight = scanner.nextInt();
            scanner.nextLine();

            Patient patient = new Patient(id, name, age, height, weight);
            patientService.add(patient);

        } catch (ID_ExceptionAlready e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Wrong type ... \n");
            scanner.nextLine();
        }
    }

    public void deleteP(){
        try {
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if(patientService.getPatientRepository() instanceof PatientDBRepository)patientService.delete(id);
            else{
                appointmentService.deleteByPatientId(id);
                patientService.delete(id);
            }


        }catch (ID_ExceptionNotFound e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Type an integer for the id!\n");
            scanner.nextLine();
        }
    }

    public void editP(){
        try {
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter New Name: ");
            String newName = scanner.nextLine();

            System.out.println("Enter New Age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter New Height: ");
            int newHeight = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter New Weight: ");
            int newWeight = scanner.nextInt();
            scanner.nextLine();

            Patient patient = new Patient(id, newName, newAge, newHeight, newWeight);
            patientService.modify(id, patient);

        }catch (ID_ExceptionNotFound e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }
    }

    public void findP(){
        try {
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            patientService.find_by_id(id);

        }catch (ID_ExceptionNotFound e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Type an integer for the id!\n");
            scanner.nextLine();
        }
    }

    public void displayP(){
        try {
            patientService.display();
        }catch(EmptyRepoException e){
            System.out.println("No patients in the list ... ");
        }
    }

    public void filterAgeP(){
        try {
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            AgeFilterPatient patientAgeFilter = new AgeFilterPatient(age);
            Iterable<Patient> patients = patientService.getAll();
            FilterRepository<Integer, Patient> filterRepository = new FilterRepository<>(patientAgeFilter);

            for (Patient patient : patients) {
                filterRepository.add(patient);
            }

            FilterService<Integer, Patient> filterService = new FilterService<>(filterRepository);
            filterService.displayFiltered();

        } catch (EmptyRepoException e) {
            System.out.println("No patients available to filter.");
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Type an integer for the age!\n");
            scanner.nextLine();
        }
    }

    public void filterHeightP(){
        try {
            System.out.println("Enter height: ");
            int height = scanner.nextInt();
            scanner.nextLine();

            HeightFilterPatient patientHeightFilter = new HeightFilterPatient(height);
            Iterable<Patient> patients = patientService.getAll();
            FilterRepository<Integer, Patient> filterRepository = new FilterRepository<>(patientHeightFilter);

            for (Patient patient : patients) {
                filterRepository.add(patient);
            }

            FilterService<Integer, Patient> filterService = new FilterService<>(filterRepository);
            filterService.displayFiltered();

        }catch(EmptyRepoException e){
            System.out.println("No patients available to filter.");
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Type an integer for the height!\n");
            scanner.nextLine();
        }
    }

    public void filterWeightP(){
        try{
            System.out.println("Enter weight: ");
            int weight = scanner.nextInt();
            scanner.nextLine();
            WeightFilterPatient patientWeightFilter = new WeightFilterPatient(weight);
            Iterable<Patient> patients = patientService.getAll();
            FilterRepository<Integer, Patient> filterRepository = new FilterRepository<>(patientWeightFilter);

            for (Patient patient : patients) {
                filterRepository.add(patient);
            }

            FilterService<Integer, Patient> filterService = new FilterService<>(filterRepository);
            filterService.displayFiltered();
        }catch(EmptyRepoException e){
            System.out.println("No patients available to filter.");
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Type an integer for the weight!\n");
            scanner.nextLine();
        }
    }

    public void addA() {
        try {
            System.out.print("Enter appointment ID: ");
            int appointmentID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter date... \n");
            System.out.println("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter month: ");
            int month = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter day: ");
            int day = scanner.nextInt();
            scanner.nextLine();
            LocalDate localDate = LocalDate.of(year, month, day);
            System.out.print("Enter patient ID: ");
            int patientID = scanner.nextInt();
            scanner.nextLine();
            if (!patientService.isID(patientID)) throw new ID_Patient_ExceptionNotFound();
            Appointment appointment = new Appointment(appointmentID, title, localDate, patientID);
            appointmentService.add(appointment);
        } catch (ID_ExceptionAlready e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Wrong type ... \n");
            scanner.nextLine();
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
        } catch (ID_ExceptionNotFound e) {
            System.out.println(e.getMessage());
        } catch (ID_Patient_ExceptionNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteA() {
        try {
            System.out.print("Enter appointment ID: ");
            int appointmentID = scanner.nextInt();
            scanner.nextLine();
            appointmentService.delete(appointmentID);
        }catch(ID_ExceptionAlready e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }catch(DateTimeException e){
            System.out.println("Invalid date");
        }catch(ID_ExceptionNotFound e){
            System.out.println(e.getMessage());
        }
    }

    public void editA() {
        try {
            System.out.print("Enter appointment new ID: ");
            int appointmentNewID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter appointment ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter date... \n");
            System.out.println("Enter year: ");
            int newYear = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter month: ");
            int newMonth = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter day: ");
            int newDay = scanner.nextInt();
            scanner.nextLine();
            LocalDate localDate = LocalDate.of(newYear, newMonth, newDay);
            System.out.print("Enter patient ID: ");
            int newPatientID = scanner.nextInt();
            scanner.nextLine();
            Appointment appointment = new Appointment(appointmentNewID, newTitle, localDate, newPatientID);
            appointmentService.modify(id, appointment);
        }catch(ID_ExceptionAlready e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }catch(DateTimeException e){
            System.out.println("Invalid date");
        }catch(ID_ExceptionNotFound e){
            System.out.println("Invalid id for patient or appointment");
        }
    }

    public void findA() {
        try {
            System.out.print("Enter appointment ID: ");
            int appointmentID = scanner.nextInt();
            scanner.nextLine();
            System.out.println(appointmentService.find_by_id(appointmentID));
        }catch(ID_ExceptionAlready e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }catch(DateTimeException e){
            System.out.println("Invalid date");
        }catch(ID_ExceptionNotFound e){
            System.out.println(e.getMessage());
        }
    }

    public void filterByDateA() {
        try {
            System.out.print("Enter date... \n");

            System.out.println("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter month: ");
            int month = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter day: ");
            int day = scanner.nextInt();
            scanner.nextLine();

            LocalDate localDate = LocalDate.of(year, month, day);

            DateFilterAppointment filter = new DateFilterAppointment(localDate);
            Iterable<Appointment> appointments = appointmentService.getAll();
            FilterRepository<Integer, Appointment> filterRepository = new FilterRepository<>(filter);

            for (Appointment appointment : appointments) {
                filterRepository.add(appointment);
            }

            FilterService<Integer, Appointment> filterService = new FilterService<>(filterRepository);
            filterService.displayFiltered();

        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }catch(DateTimeException e){
            System.out.println("Invalid date");
        }catch(EmptyRepoException e){
            System.out.println(e.getMessage());
        }

    }

    public void displayA() {
        try {
            appointmentService.display();
        }
        catch (EmptyRepoException e) {
            System.out.println("No appointments in the list .... ");
        }
    }

    public void displayAppointmentsP(){
        try {
            System.out.print("Enter patient ID: ");
            int patientID = scanner.nextInt();
            scanner.nextLine();
            Iterable<Appointment> appointments = appointmentService.getAll();
            AppointmentRepository newAppointmentRepository = new AppointmentRepository();
            AppointmentService newAppointmentService = new AppointmentService(newAppointmentRepository);
            for (Appointment appointment : appointments) {
                if(appointment.getPatientId() == patientID){
                    newAppointmentService.add(appointment);
                }
            }
            Iterable<Appointment> newAppointments= newAppointmentService.getAll();
            if (newAppointments == null) System.out.println("No appointments found");
            else {
                System.out.println("Appointments found for Patient : ID# " + patientID);
                for (Appointment appointment : newAppointments) {
                    System.out.println(appointment);
                }
            }
        }catch (EmptyRepoException e) {
            System.out.println("There are no appointments found");
        }catch(InputMismatchException e){
            System.out.println("\n[ERROR] Wrong type ...\n");
            scanner.nextLine();
        }
    }

    public void op1(){
        List<Patient> report = patientService.report1();
        report.stream().forEach(System.out::println);
    }
    public void op2(){
        System.out.println("Enter date");
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        LocalDate localDate = LocalDate.of(year, month, day);
        List<Appointment> report = appointmentService.report2(localDate);
        report.stream().forEach(System.out::println);
    }
    public void op3(){
        List<Patient>report = patientService.report3();
        report.stream().forEach(System.out::println);
    }
    public void op4(){
        List<String>report = patientService.report4();
        report.stream().forEach(System.out::println);
    }
    public void op5(){
        List<String>report = patientService.report5();
        report.stream().forEach(System.out::println);
    }

}
