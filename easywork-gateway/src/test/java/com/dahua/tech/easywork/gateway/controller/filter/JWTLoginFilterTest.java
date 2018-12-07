package com.dahua.tech.easywork.gateway.controller.filter;

import com.dahua.tech.easywork.api.enums.AuthReturnCode;
import com.dahua.tech.easywork.gateway.controller.AuthenticationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @auther jarod.jin 2018/12/4
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class JWTLoginFilterTest {
    private MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new AuthenticationController()).build();
    }

    @Test
    @DisplayName("测试")
    public void login_right_pwd() throws Exception {
        System.out.println(AuthReturnCode.AU200.name());

    }
}