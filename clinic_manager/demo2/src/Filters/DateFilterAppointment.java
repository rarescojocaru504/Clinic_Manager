package Filters;

import Domain.Appointment;
import java.time.LocalDate;
public class DateFilterAppointment implements AbstractFilter<Appointment> {
    LocalDate date;

    public DateFilterAppointment() {
        date = null;
    }

    public DateFilterAppointment(LocalDate date){
        this.date = date;
    }

    @Override
    public boolean accept(Appointment entity) {
        LocalDate entityDate = entity.getDate();
        return entity.getDate().isAfter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
