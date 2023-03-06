package com.sungwon.testeturing.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginControllerUnitTest {

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginShouldReturnLoginPage() throws Exception {
        String result = loginController.login();
        Assert.assertEquals("login/index", result);
    }
}
