package com.axity.office.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

/**
 * Validation Exception Handler Test class
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ValidationExceptionHandlerTest
{

    @Autowired
    private ValidationExceptionHandler  validationExceptionHandler ;

    @BeforeEach
    void setUp() {

    }

    void genericMethod() {

    }

    @Test
    void testHandleMethodArgumentNotValid() throws NoSuchMethodException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.ACCEPTED;
        WebRequest request = null;
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "objectName");
        bindingResult.addError(new FieldError("objectName", "field1", "message"));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Object> response = this.validationExceptionHandler.handleMethodArgumentNotValid(
                ex, headers, status, request);

        assertEquals(response.getStatusCode(), status);
    }
}