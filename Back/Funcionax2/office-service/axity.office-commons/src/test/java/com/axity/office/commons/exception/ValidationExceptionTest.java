package com.axity.office.commons.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axity.office.commons.enums.ErrorCode;

/**
 * Validation Exception Test class
 * 
 * @author username@axity.com
 */
@ExtendWith(MockitoExtension.class)
class ValidationExceptionTest
{

    @BeforeEach
    public void setUp()
    {

    }

    @Test
    void testValidationException() {

        ValidationException validationException = new ValidationException();

        assertEquals(validationException.getCode(), 0);
    }

    @Test
    void testValidationExceptionOne() {

        String message = "Error";
        Throwable ex = new Throwable();
        ValidationException validationException = new ValidationException(message, ex);

        assertEquals(validationException.getCode(), 0);
    }

    @Test
    void testValidationExceptionTwo() {

        String message = "Error";
        ValidationException validationException = new ValidationException(message);

        assertEquals(validationException.getCode(), 0);
    }

    @Test
    void testValidationExceptionThree() {

        Throwable ex = new Throwable();
        ValidationException validationException = new ValidationException(ex);

        assertEquals(validationException.getCode(), 0);
    }

    @Test
    void testValidationExceptionFour() {

        int code = 3;
        ValidationException validationException = new ValidationException(code);

        assertEquals(validationException.getCode(), code);
    }

    @Test
    void testValidationExceptionFive() {

        int code = 3;
        String message = "Error";
        ValidationException validationException = new ValidationException(code, message);

        assertEquals(validationException.getCode(), code);
    }

    @Test
    void testValidationExceptionSix() {

        int code = 3;
        Throwable ex = new Throwable();

        ValidationException validationException = new ValidationException(code, ex);

        assertEquals(validationException.getCode(), code);
    }

    @Test
    void testValidationExceptionSeven() {

        int code = 3;
        Throwable ex = new Throwable();
        String message = "Error";

        ValidationException validationException = new ValidationException(code, message, ex);

        assertEquals(validationException.getCode(), code);
    }

    @Test
    void testValidationExceptionEight() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;

        ValidationException validationException = new ValidationException(error);

        assertEquals(validationException.getCode(), error.getCode());
    }

    @Test
    void testValidationExceptionNine() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        String message = "Error";

        ValidationException validationException = new ValidationException(error, message);

        assertEquals(validationException.getCode(), error.getCode());
    }

    @Test
    void testValidationExceptionTen() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        Throwable ex = new Throwable();

        ValidationException validationException = new ValidationException(error, ex);

        assertEquals(validationException.getCode(), error.getCode());
    }

    @Test
    void testValidationExceptionEleven() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        Throwable ex = new Throwable();
        String message = "Error";

        ValidationException validationException = new ValidationException(error, message, ex);

        assertEquals(validationException.getCode(), error.getCode());
    }

}