package academy.mindswap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ClientNotFoundException e, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Exception handled");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
