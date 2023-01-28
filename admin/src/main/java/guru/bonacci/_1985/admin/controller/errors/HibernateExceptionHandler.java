package guru.bonacci._1985.admin.controller.errors;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HibernateExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(SQLIntegrityConstraintViolationException ex) {
    var errors = new ArrayList<String>();
    errors.add(ex.getLocalizedMessage());
    var apiError = new ApiError(HttpStatus.BAD_REQUEST, "Oops..", errors);
    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}