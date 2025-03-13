package Repository;

import Domain.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDBRepository extends PatientRepository {
    private final String url;

    public PatientDBRepository(String url) {
        this.url = url;
        read();
    }

    private void read() {
        String query = "SELECT * FROM Patients";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int height = resultSet.getInt("height");
                int weight = resultSet.getInt("weight");

                Patient patient = new Patient(id, name, age, height, weight);
                super.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading patients from the database.", e);
        }
    }

    private void write() {
        String deleteQuery = "DELETE FROM Patients";
        String insertQuery = "INSERT INTO Patients (id, name, age, height, weight) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             Statement deleteStatement = connection.createStatement();
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            connection.setAutoCommit(false);

            deleteStatement.executeUpdate(deleteQuery);

            for (Patient patient : super.getAll()) {
                insertStatement.setInt(1, patient.get_id());
                insertStatement.setString(2, patient.getName());
                insertStatement.setInt(3, patient.getAge());
                insertStatement.setInt(4, patient.getHeight());
                insertStatement.setInt(5, patient.getWeight());
                insertStatement.addBatch();
            }

            insertStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error writing patients to the database.", e);
        }
    }

    @Override
    public void add(Patient patient) {
        super.add(patient);
        String insertQuery = "INSERT INTO Patients (id, name, age, height, weight) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, patient.get_id());
            statement.setString(2, patient.getName());
            statement.setInt(3, patient.getAge());
            statement.setInt(4, patient.getHeight());
            statement.setInt(5, patient.getWeight());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding patient to the database.", e);
        }
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
        String deleteQuery = "DELETE FROM Patients WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No patient found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting patient from the database.", e);
        }
    }

    @Override
    public void modify(Integer id, Patient patient) {
        super.modify(id, patient);
        String updateQuery = "UPDATE Patients SET name = ?, age = ?, height = ?, weight = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setInt(3, patient.getHeight());
            statement.setInt(4, patient.getWeight());
            statement.setInt(5, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No patient found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating patient in the database.", e);
        }
    }
}