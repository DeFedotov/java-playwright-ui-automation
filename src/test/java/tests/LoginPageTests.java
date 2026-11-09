package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends BaseTest{

    @Test
    @DisplayName("Login test")
    public void loginTest(){
        LoginPage loginPage = new LoginPage(page);


        loginPage
                .navigate()
                .login("user", "user");
    }
}
