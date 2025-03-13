package MyExceptions;

public class ID_ExceptionAlready extends RuntimeException{
    public ID_ExceptionAlready(){
        super("\n[ERROR] ID Already Exists ... Try Again\n");
    }
}
