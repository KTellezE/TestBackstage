package com.axity.office.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axity.office.commons.exception.ValidationException;

/**
 * Validation Util Test class
 * 
 * @author username@axity.com
 */
@ExtendWith(MockitoExtension.class)
class ValidationUtilTest
{


    @BeforeEach
    public void setUp()
    {

    }

    @Test
    void testCheckRequired() {

        String value = "";
        String fieldName = "id";

        Assertions.assertThrows(ValidationException.class,
                () -> ValidationUtil.checkRequired(value, fieldName));

    }

    @Test
    void testCheckRequiredBranch() {

        String value = "2";
        String fieldName = "id";

        Assertions.assertDoesNotThrow(() -> ValidationUtil.checkRequired(value, fieldName));

    }

    @Test
    void testCheckMaxLength() {

        int maxLength = 3;
        String fieldName = "name";
        String value = "Chester";

        Assertions.assertThrows(ValidationException.class,
                () -> ValidationUtil.checkMaxLength(value, fieldName, maxLength));

    }

    @Test
    void testCheckMaxLengthBranch() {

        int maxLength = 3;
        String fieldName = "name";
        String value = "";

        Assertions.assertDoesNotThrow(() ->
                ValidationUtil.checkMaxLength(value, fieldName, maxLength));

    }

    @Test
    void testCheckMaxLengthTwo() {

        int maxLength = 3;
        String fieldName = "name";
        String value = "Ch";

        Assertions.assertDoesNotThrow(() ->
                ValidationUtil.checkMaxLength(value, fieldName, maxLength));

    }

    @Test
    void testCheckRequiredMaxLength() {

        int maxLength = 10;
        String fieldName = "name";
        String value = "Chester";

        Assertions.assertDoesNotThrow(() ->
                ValidationUtil.checkMaxLength(value, fieldName, maxLength));

    }
}