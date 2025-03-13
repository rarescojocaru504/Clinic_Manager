package MyExceptions;

public class InvalidException extends RuntimeException {
    public InvalidException(){
        super("\n[ERROR] Invalid choice ... Try again\n");
    }
}
