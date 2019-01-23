package com.dahua.tech.easywork.cucumber.driver;


import com.dahua.tech.easywork.cucumber.common.view.Params;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
public class SeleniumWebDriver implements UiDriver {

    private static final int DEFAULT_TIME_OUT_IN_SECONDS= 10;
    private static final String url= "http://10.1.248.137:4444/wd/hub";
    private final WebDriver webDriver= getLocalDriver();

    private WebDriver getDefaultRemoteDriver(String remoteUrl){
        System.setProperty("webdriver.chrome.driver","src/lib/chromedriver.exe");
        DesiredCapabilities capabilities= DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);
        try {
            WebDriver driver= new RemoteWebDriver(new URL(remoteUrl),capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            return getLocalDriver();
        }
    }


    private WebDriver getLocalDriver(){
        System.setProperty("webdriver.chrome.driver","src/lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        webDriver.get(url);
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        webDriver.get(url + params.getQuery());
    }

    @Override
    public void waitForTextPresent(String text) {
        new WebDriverWait(webDriver, DEFAULT_TIME_OUT_IN_SECONDS).until(
                (ExpectedCondition<Boolean>) webDriver -> getAllTextInPage().contains(text));
    }

    @Override
    public void inputTextByXpath(String text, String xpath) {
        elementByXpath(xpath).sendKeys(text);
    }

    @Override
    public void inputTextByName(String text, String xpath) {
        elementByName(xpath).sendKeys(text);
    }

    private WebElement elementByName(String name) {
        return webDriver.findElement(By.name(name));
    }

    private WebElement elementByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    @Override
    public void clickByText(String text) {
        firstElementByText(text).click();
    }

    @Override
    public void clickByXpath(String xpath) {
        elementByXpath(xpath).click();
    }

    private WebElement firstElementByText(String text) {
        return elementsByText(text)
                .findFirst().<NoSuchElementException>orElseThrow(() -> {
                    throw new NoSuchElementException(String.format("no element can be found by text= %s", text));
                });
    }

    private Stream<WebElement> elementsByText(String text) {
        return Stream.of(
                elementsByXPath(String.format("//input[@value='%s']", text)),
                elementsByXPath(String.format("//button[text()='%s']", text)),
                elementsByLinkText(text))
                .flatMap(Collection::stream);
    }

    private List<WebElement> elementsByLinkText(String text) {
        return webDriver.findElements(By.linkText(text));
    }

    private List<WebElement> elementsByXPath(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    @Override
    public void selectOptionByTextAndElementName(String text, String elementName) {
        new Select(elementByName(elementName)).selectByVisibleText(text);
    }

    @Override
    public String getAllTextInPage() {
        return elementByTag().getText();
    }

    private WebElement elementByTag() {
        return webDriver.findElement(By.tagName("body"));
    }

}
