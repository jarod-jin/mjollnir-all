package com.dahua.tech.easywork.cucumber.common.view;

@FunctionalInterface
public interface View<T> {

    void display(T domainObject);

}
