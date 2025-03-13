package Domain;
import java.io.Serializable;
import java.time.LocalDate;
public class Appointment implements Comparable<Appointment>, Identifiable<Integer>, Serializable {
    private Integer id = -1;
    private String title;
    private LocalDate date;
    private Integer patientId;

    public Appointment(Integer id, String title, LocalDate date, Integer patientId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Appointment) obj).id);
    }

    @Override
    public Integer get_id() {
        return id;
    }

    @Override
    public void set_id(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String m_string = "||| ID: " + id + " | Title: " + title + " | Date: " + date + " | Patient: ID# " + patientId ;
        return m_string;
    }

    @Override
    public int compareTo(Appointment appointment) {
        return this.date.compareTo(appointment.date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

}
