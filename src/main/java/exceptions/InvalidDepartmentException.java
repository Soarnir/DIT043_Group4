package exceptions;

public class InvalidDepartmentException extends RuntimeException{

    public InvalidDepartmentException() {
        //default message
    }

    public InvalidDepartmentException(String message) {
        super(message);
    }
}
