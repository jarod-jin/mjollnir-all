package com.dahua.tech.easywork.cucumber.page;

import com.dahua.tech.easywork.cucumber.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CommonPage {

    @Autowired
    UiDriver driver;

    public String getAllText() {
        return driver.getAllTextInPage();
    }

    public void waitForTextPresent(String text){
        driver.waitForTextPresent(text);
    }

}
