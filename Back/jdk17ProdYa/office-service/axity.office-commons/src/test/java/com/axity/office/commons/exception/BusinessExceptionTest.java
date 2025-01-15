package com.axity.office.commons.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axity.office.commons.enums.ErrorCode;

/**
 * Business Exception Test class
 * 
 * @author username@axity.com
 */
@ExtendWith(MockitoExtension.class)
class BusinessExceptionTest
{

    @BeforeEach
    public void setUp()
    {

    }

    @Test
    void testBusinessException() {

        BusinessException businessException = new BusinessException();

        assertEquals(businessException.getCode(), 0);
    }

    @Test
    void testBusinessExceptionOne() {

        String message = "Error";
        Throwable ex = new Throwable();
        BusinessException businessException = new BusinessException(message, ex);

        assertEquals(businessException.getCode(), 0);
    }

    @Test
    void testBusinessExceptionTwo() {

        String message = "Error";
        BusinessException businessException = new BusinessException(message);

        assertEquals(businessException.getCode(), 0);
    }

    @Test
    void testBusinessExceptionThree() {

        Throwable ex = new Throwable();
        BusinessException businessException = new BusinessException(ex);

        assertEquals(businessException.getCode(), 0);
    }

    @Test
    void testBusinessExceptionFour() {

        int code = 3;
        BusinessException businessException = new BusinessException(code);

        assertEquals(businessException.getCode(), code);
    }

    @Test
    void testBusinessExceptionFive() {

        int code = 3;
        String message = "Error";
        BusinessException businessException = new BusinessException(code, message);

        assertEquals(businessException.getCode(), code);
    }

    @Test
    void testBusinessExceptionSix() {

        int code = 3;
        Throwable ex = new Throwable();

        BusinessException businessException = new BusinessException(code, ex);

        assertEquals(businessException.getCode(), code);
    }

    @Test
    void testBusinessExceptionSeven() {

        int code = 3;
        Throwable ex = new Throwable();
        String message = "Error";

        BusinessException businessException = new BusinessException(code, message, ex);

        assertEquals(businessException.getCode(), code);
    }

    @Test
    void testBusinessExceptionEight() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;

        BusinessException businessException = new BusinessException(error);

        assertEquals(businessException.getCode(), error.getCode());
    }

    @Test
    void testBusinessExceptionNine() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        String message = "Error";

        BusinessException businessException = new BusinessException(error, message);

        assertEquals(businessException.getCode(), error.getCode());
    }

    @Test
    void testBusinessExceptionTen() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        Throwable ex = new Throwable();

        BusinessException businessException = new BusinessException(error, ex);

        assertEquals(businessException.getCode(), error.getCode());
    }

    @Test
    void testBusinessExceptionEleven() {

        ErrorCode error = ErrorCode.OFFICE_ALREADY_EXISTS;
        Throwable ex = new Throwable();
        String message = "Error";

        BusinessException businessException = new BusinessException(error, message, ex);

        assertEquals(businessException.getCode(), error.getCode());
    }

}
