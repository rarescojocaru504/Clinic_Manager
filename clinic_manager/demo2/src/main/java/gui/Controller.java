package gui;

import Domain.Appointment;

import Domain.Patient;
import Service.AppointmentService;
import Service.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    PatientService patientService;
    AppointmentService appointmentService;
    ObservableList<Patient> patients;
    ObservableList<Appointment> appointments;

    public Controller(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    void resetObservableList1() {
        ArrayList<Patient> new_patients = new ArrayList<>(this.patientService.getAll());
        patients = FXCollections.observableArrayList(new_patients);
        list1.setItems(patients);
    }

    void resetObservableList2(){
        ArrayList<Appointment> new_appointments = new ArrayList<>(this.appointmentService.getAll());
        appointments = FXCollections.observableArrayList(new_appointments);
        list2.setItems(appointments);
    }

    @FXML
    public void initialize() {
        resetObservableList1();
        resetObservableList2();
    }



    @FXML
    private Button b1;

    @FXML
    private void handleAddPatient() {
        try {
            int id = Integer.parseInt(t1.getText());
            String name = t2.getText();
            int age = Integer.parseInt(t3.getText());
            int weight = Integer.parseInt(t4.getText());
            int height = Integer.parseInt(t5.getText());
            Patient patient = new Patient(id, name, age, weight, height);
            patientService.add(patient);
            resetObservableList1();
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please check your entries.");
        }
    }

    @FXML
    private void handleAddAppointment() {
        try{
            int id = Integer.parseInt(t6.getText());
            String title = t7.getText();
            LocalDate date = LocalDate.parse(t8.getText());
            int patientId = Integer.parseInt(t9.getText());
            Appointment appointment = new Appointment(id, title, date, patientId);
            appointmentService.add(appointment);
            resetObservableList2();
            t6.clear();
            t7.clear();
            t8.clear();
            t9.clear();
        }catch(NumberFormatException e){
            System.out.println("Invalid input! Please check your entries.");
        }
    }
    @FXML
    private void handleDeletePatient() {
        try{
            int id = Integer.parseInt(t1.getText());
            patientService.delete(id);
            appointmentService.deleteByPatientId(id);
            resetObservableList1();
            resetObservableList2();
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
        }catch(NumberFormatException e){
            System.out.println("Invalid input! Please check your entries.");
        }
    }

    @FXML
    private void handleDeleteAppointment() {
        try{
            int id = Integer.parseInt(t6.getText());
            appointmentService.delete(id);
            resetObservableList2();
            t6.clear();
            t7.clear();
            t8.clear();
            t9.clear();
        }catch(NumberFormatException e){
            System.out.println("Invalid input! Please check your entries.");
        }
    }

    @FXML
    private void handleEditPatient() {
        try{
            int id = Integer.parseInt(t1.getText());
            String name = t2.getText();
            int age = Integer.parseInt(t3.getText());
            int weight = Integer.parseInt(t4.getText());
            int height = Integer.parseInt(t5.getText());
            Patient patient = new Patient(id, name, age, weight, height);
            patientService.modify(id, patient);
            resetObservableList1();
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
        }catch(NumberFormatException e){
            System.out.println("Invalid input! Please check your entries.");
        }
    }

    @FXML
    private void handleEditAppointment() {
        try{
            int id = Integer.parseInt(t6.getText());
            String title = t7.getText();
            LocalDate date = LocalDate.parse(t8.getText());
            int patientId = Integer.parseInt(t9.getText());
            Appointment appointment = new Appointment(id, title, date, patientId);
            appointmentService.modify(id, appointment);
            resetObservableList2();
            t6.clear();
            t7.clear();
            t8.clear();
            t9.clear();
        }catch(NumberFormatException e){
            System.out.println("Invalid input! Please check your entries.");
        }
    }

    @FXML
    void handleR1() {
        List<Patient> new_patients = this.patientService.report1();
        ObservableList<Patient> patients = FXCollections.observableArrayList(new_patients);
        list3.getItems().clear();
        list3.setItems(FXCollections.observableArrayList(patients));
    }

    @FXML
    void handleR2() {
        List<Appointment> new_app = this.appointmentService.report2(LocalDate.parse(r_tField.getText()));
        r_tField.clear();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList(new_app);
        list3.getItems().clear();
        list3.setItems(FXCollections.observableArrayList(appointments));
    }

    @FXML
    void handleR3() {
        List<Patient> new_patients = this.patientService.report3();
        ObservableList<Patient> patients = FXCollections.observableArrayList(new_patients);
        list3.getItems().clear();
        list3.setItems(FXCollections.observableArrayList(patients));
    }

    @FXML
    void handleR4() {
        List<String> new_patients = this.patientService.report4();
        ObservableList<String> patients = FXCollections.observableArrayList(new_patients);
        list3.getItems().clear();
        list3.setItems(FXCollections.observableArrayList(patients));
    }


    @FXML
    void handleR5() {
        List<String> new_patients = this.patientService.report5();
        ObservableList<String> patients = FXCollections.observableArrayList(new_patients);
        list3.getItems().clear();
        list3.setItems(FXCollections.observableArrayList(patients));
    }

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private ListView<Patient> list1;

    @FXML
    private ListView<Appointment> list2;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private TextField t5;

    @FXML
    private TextField t6;

    @FXML
    private TextField t7;

    @FXML
    private TextField t8;

    @FXML
    private TextField t9;

    @FXML
    private ListView<Object> list3;

    @FXML
    private TextField r_tField;

    @FXML
    private Button rb1;

    @FXML
    private Button rb2;

    @FXML
    private Button rb3;

    @FXML
    private Button rb4;

    @FXML
    private Button rb5;


}
