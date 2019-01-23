package com.dahua.tech.easywork.cucumber.page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("cucumber-glue")
public class LabelTexts {

    @Value("${cucumber.domain}")
    private String domain;

    @Value("${label.add}")
    public String add;

    @Value("${home.label.signout}")
    public String signout;

    @Value("${accounts.index.label.title}")
    public String accountsTitle;

    @Value("${label.appTitle}")
    public String appTitle;

    @Value("${label.accounts}")
    public String accountsLink;

    @Value("${transactions.index.label.title}")
    public String transactionsTitle;

    @Value("${signin.label.head}")
    public String head;

    @Value("${signin.label.submit}")
    public String signin;

}
