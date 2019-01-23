package com.dahua.tech.easywork.cucumber.common;

/**
 * @auther jarod.jin 2019/1/18
 */
public class Urls {

    public static final String WEB_PREFIX = "/easywork/web";

    public static final String ISDP =  WEB_PREFIX+ "/ISDP";

    public static final String OUTSOURCER = "/outsourcer";

    public static final String LOGIN = "/login";

    public static final String ISDP_LOGIN =  ISDP + LOGIN;

    public static final String OUTSOURCER_LOGIN = OUTSOURCER + LOGIN;

    public static final String PROJECT = ISDP + "/project";

    public static final String DETAIL= "/detail";

    public static final String SURVEY_FULL_PATH = PROJECT + DETAIL + "/survey";

    public static final String PLAN_FULL_PATH = PROJECT + DETAIL + "/plan";

}
