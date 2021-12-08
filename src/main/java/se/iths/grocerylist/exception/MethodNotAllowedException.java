package se.iths.grocerylist.exception;

public class MethodNotAllowedException extends RuntimeException {

    MethodNotAllowedException(String message){
        super(message);
    }

}
