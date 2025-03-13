package MyExceptions;

public class ID_Patient_ExceptionNotFound extends RuntimeException {
    public ID_Patient_ExceptionNotFound() {
        super("Patient not found");
    }
}
