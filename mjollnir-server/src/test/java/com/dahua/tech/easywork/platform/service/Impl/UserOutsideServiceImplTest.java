package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther jarod.jin 2018/12/5
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserOutsideServiceImplTest {

    @Autowired
    UserOutsideService userOutsideService;

    @Test
    void queryUserOutside() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("45216");
        ResultDTO resultDTO =  userOutsideService.queryUserOutside(userDTO);
        UserDTO user = (UserDTO)resultDTO.getData();
        assertAll("Check ResultDTO",
                () -> assertTrue(resultDTO.getResultCode().equals("PLU200")),
                () -> assertAll("Check UserDTO",
                        ()-> assertEquals("jarod", user.getNickname()),
                        ()-> assertEquals("656717", user.getTel()),
                        ()-> assertFalse(user.getGroupDTOList().isEmpty()),
                        ()-> assertFalse(user.getRoleDTOList().isEmpty())
                )
        );

    }
}