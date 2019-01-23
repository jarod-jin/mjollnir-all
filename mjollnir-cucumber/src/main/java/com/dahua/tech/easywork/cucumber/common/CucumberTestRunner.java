package com.dahua.tech.easywork.cucumber.common;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @auther jarod.jin 2019/1/22
 */
@RunWith(Cucumber.class)
@CucumberOptions(features= {"src/test/resources/features"},
        glue = {"com.dahua.tech.easywork.cucumber.step"},
        tags = {"@smokeTest"},monochrome= true,
        plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber-report/cucumber.json"} )
public class CucumberTestRunner {
}
