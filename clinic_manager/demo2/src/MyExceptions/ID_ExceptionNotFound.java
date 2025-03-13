package MyExceptions;

public class ID_ExceptionNotFound extends RuntimeException{
    public ID_ExceptionNotFound() {
        super("\n[ERROR] ID not found ... Try again \n");
    }
}
