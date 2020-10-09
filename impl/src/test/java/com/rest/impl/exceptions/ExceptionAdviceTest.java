package com.rest.impl.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionAdviceTest {

    private ExceptionAdvice exceptionAdvice;

    @Before
    public void setUp() {
        exceptionAdvice = new ExceptionAdvice();
    }

    @Test
    public void whenGivenUserNotFoundException_thenReturnExceptionMessage() {
        UserNotFoundException expected = new UserNotFoundException(404);

        String result = exceptionAdvice.userNotFoundHandler(expected);

        assertThat(result).isEqualTo(expected.getMessage());
    }

    @Test
    public void whenGivenAddressNotFoundException_thenReturnExceptionMessage() {
        AddressNotFoundException expected = new AddressNotFoundException(404);

        String result = exceptionAdvice.addressNotFoundHandler(expected);

        assertThat(result).isEqualTo(expected.getMessage());
    }

    @Test
    public void whenGivenGroupNotFoundException_thenReturnExceptionMessage() {
        GroupNotFoundException expected = new GroupNotFoundException(404);

        String result = exceptionAdvice.groupNotFoundHandler(expected);

        assertThat(result).isEqualTo(expected.getMessage());
    }

}