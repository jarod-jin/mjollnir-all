package com.dahua.tech.easywork.cucumber.driver;


import com.dahua.tech.easywork.cucumber.common.view.Params;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    void navigateToWithParams(String url, Params params);

    void waitForTextPresent(String text);

    void inputTextByXpath(String text, String xpath);

    void inputTextByName(String text, String name);

    void clickByText(String text);

    void clickByXpath(String xpath);

    void selectOptionByTextAndElementName(String text, String elementName);

    String getAllTextInPage();
}
