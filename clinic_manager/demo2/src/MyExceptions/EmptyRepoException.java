package MyExceptions;

public class EmptyRepoException extends RuntimeException{
    public EmptyRepoException(){
        super("\n[ERROR] There are no entities ...\n");
    }
}
