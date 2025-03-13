package Repository;

import Domain.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentDBRepository extends AppointmentRepository {
    private final String url;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AppointmentDBRepository(String url) {
        this.url = url;
        read();
    }

    private void read() {
        String query = "SELECT * FROM Appointments";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String dateText = resultSet.getString("date"); // Read the date as TEXT
                LocalDate date = LocalDate.parse(dateText, DATE_FORMATTER); // Convert TEXT to LocalDate
                int patientId = resultSet.getInt("patient_id");

                Appointment appointment = new Appointment(id, title, date, patientId);
                super.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading appointments from the database.", e);
        }
    }

    private void write() {
        String deleteQuery = "DELETE FROM Appointments";
        String insertQuery = "INSERT INTO Appointments (id, title, date, patient_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             Statement deleteStatement = connection.createStatement();
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            connection.setAutoCommit(false);

            deleteStatement.executeUpdate(deleteQuery);

            for (Appointment appointment : super.getAll()) {
                insertStatement.setInt(1, appointment.get_id());
                insertStatement.setString(2, appointment.getTitle());
                insertStatement.setString(3, appointment.getDate().format(DATE_FORMATTER));
                insertStatement.setInt(4, appointment.getPatientId());
                insertStatement.addBatch();
            }

            insertStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error writing appointments to the database.", e);
        }
    }

    @Override
    public void add(Appointment appointment) {
        super.add(appointment);
        String insertQuery = "INSERT INTO Appointments (id, title, date, patient_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, appointment.get_id());
            statement.setString(2, appointment.getTitle());
            statement.setString(3, appointment.getDate().format(DATE_FORMATTER)); // Format LocalDate to TEXT
            statement.setInt(4, appointment.getPatientId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding appointment to the database.", e);
        }
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
        String deleteQuery = "DELETE FROM Appointments WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No appointment found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting appointment from the database.", e);
        }
    }

    @Override
    public void modify(Integer id, Appointment appointment) {
        super.modify(id, appointment);
        String updateQuery = "UPDATE Appointments SET title = ?, date = ?, patient_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDate().format(DATE_FORMATTER)); // Format LocalDate to TEXT
            statement.setInt(3, appointment.getPatientId());
            statement.setInt(4, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No appointment found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointment in the database.", e);
        }
    }
}