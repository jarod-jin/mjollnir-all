package com.dahua.tech.easywork.cucumber.page;

import com.dahua.tech.easywork.cucumber.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.dahua.tech.easywork.cucumber.common.Urls.ISDP_LOGIN;


@Component
@Scope("cucumber-glue")
public class SignInPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void signIn(String userName, String password) {
        driver.navigateTo(ISDP_LOGIN);
        driver.waitForTextPresent(labelTexts.head);
        driver.inputTextByXpath(userName, "//input[@class='el-input__inner' and @type='text']");
        driver.inputTextByXpath(password, "//input[@class='el-input__inner' and @type='password']");
        driver.clickByXpath("//button[@class='el-button el-button--primary el-button--small' and @type='button']");
    }

}
