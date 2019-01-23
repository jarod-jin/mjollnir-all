package com.dahua.tech.easywork.cucumber.step;

import com.dahua.tech.easywork.cucumber.page.CommonPage;
import com.dahua.tech.easywork.cucumber.page.HomePage;
import com.dahua.tech.easywork.cucumber.page.LabelTexts;
import com.dahua.tech.easywork.cucumber.page.SignInPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @auther jarod.jin 2019/1/22
 */
public class LoginSteps{

    private String user;

    private String password;

    @Autowired
    SignInPage signInPage;

    @Autowired
    CommonPage commonPage;

    @Autowired
    HomePage homePage;

    @Autowired
    LabelTexts labelTexts;


    @Given("open sign in page, input user {string}")
    public void openSignInPageInputUser(String arg0)  {
        this.user= arg0;
    }

    @And("input password {string}")
    public void inputPassword(String arg0) {
        this.password= arg0;
    }

    @When("click sign in")
    public void clickSignIn()  {
        signInPage.signIn(user,password);
    }

    @Then("login successful")
    public void loginSuccessful()  {
        commonPage.waitForTextPresent(labelTexts.signout);
        assertThat(commonPage.getAllText()).contains(labelTexts.signout);
    }


    @And("see user name of {string} in the page")
    public void seeUserNameOfInThePage(String arg0) {
        assertThat(commonPage.getAllText()).contains(arg0);
    }

    @Then("login failed")
    public void loginFailed() {
        assertThat(commonPage.getAllText()).contains(labelTexts.signin);
    }

    @And("see the word {string} in the page")
    public void seeTheWordInThePage(String arg0) {
        commonPage.waitForTextPresent(arg0);
        assertThat(commonPage.getAllText()).contains(arg0);
    }
}
