package com.dahua.tech.easywork.cucumber.page;

import com.dahua.tech.easywork.cucumber.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class ProjectListPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void showList() {
        driver.waitForTextPresent(labelTexts.appTitle);
        driver.clickByText(labelTexts.accountsLink);
        driver.waitForTextPresent(labelTexts.accountsTitle);
    }
}
