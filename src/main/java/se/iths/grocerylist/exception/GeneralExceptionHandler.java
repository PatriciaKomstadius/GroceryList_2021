package se.iths.grocerylist.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException exception) {
        logger.info(exception.getClass().getName());
        String errorMessage = "Entity not found";

        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage, exception));
    }

    @ExceptionHandler({MethodNotAllowedException.class})
    public ResponseEntity<Object> methodNotAllowedException(MethodNotAllowedException exception) {
        logger.info(exception.getClass().getName());
        String errorMessage = "Method is not allowed";

        return buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED, errorMessage, exception));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> badRequestException(BadRequestException exception) {
        logger.info(exception.getClass().getName());
        logger.error("Error: ", exception);
        String errorMessage = "Status code 400, invalid request to server.";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, exception));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(exception.getClass().getName());
        String errorMessage = "Malformed JSON request.";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, exception));
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        logger.info(exception.getClass().getName());
        logger.error("Error: ", exception);
        String errorMessage = "An unexpected error occurred.";

        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, exception));
    }


    //Unauthorized 401
    //Andreas

    //Method Not Allowed
    //Sofie

    //Bad request
    //Patricia


}
