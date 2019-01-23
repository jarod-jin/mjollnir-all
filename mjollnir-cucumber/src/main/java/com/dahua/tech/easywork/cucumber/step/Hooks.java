package com.dahua.tech.easywork.cucumber.step;

import com.dahua.tech.easywork.cucumber.EasyworkCucumberApplication;
import com.dahua.tech.easywork.cucumber.driver.UiDriver;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

/**
 * @auther jarod.jin 2019/1/18
 */
@SpringBootTest(webEnvironment= DEFINED_PORT)
@ContextConfiguration(classes= {EasyworkCucumberApplication.class})
public class Hooks {

    @Autowired
    protected UiDriver uiDriver;

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }
}
