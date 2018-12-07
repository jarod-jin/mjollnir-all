package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.api.dto.platform.UserGroupDTO;
import com.dahua.tech.easywork.api.dto.platform.UserRoleDTO;
import com.dahua.tech.easywork.api.enums.PlatformReturnCode;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.entity.UserGroup;
import com.dahua.tech.easywork.platform.entity.UserOutside;
import com.dahua.tech.easywork.platform.entity.UserRole;
import com.dahua.tech.easywork.platform.repository.UserOutsideRepository;
import com.dahua.tech.easywork.platform.service.IUserGroupService;
import com.dahua.tech.easywork.platform.service.IUserOutsideService;
import com.dahua.tech.easywork.platform.service.IUserRoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class UserOutsideService implements IUserOutsideService {

    @Autowired
    private UserOutsideRepository userOutsideRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserGroupService userGroupService;


    /**
     * 用户注册
     * @param userRegister
     * @return
     */
    @Override
    public ResultDTO register(UserDTO userRegister) {
        //需要调用手机发送短信的服务，生成验证码以后跟用户输入的验证码进行比对。

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultCode("200");
        if(userOutsideRepository.existsByUserTel(userRegister.getTel())){
            resultDTO.setResultMessage("该用户已存在");
            return resultDTO;
        }
        UserOutside userOutside = new UserOutside();
        userOutside.setUserTel(userRegister.getTel());
        userOutside.setPassword(userRegister.getPassword());
        userOutside.setPasswordLatest(userRegister.getPassword());
        userOutside.setCreateDate(new Date());
        userOutside.setUserName("wjt_123");
        userOutsideRepository.save(userOutside);
        resultDTO.setResultMessage("注册成功");
        resultDTO.setData("abc123");
        return resultDTO;
    }

    /**
     * 用户查询返回一个用户 权限集合对象
     * @param userDTO
     * @return
     */
    @Override
    public ResultDTO queryUserOutside (UserDTO userDTO){
        if (StringUtils.isEmpty(userDTO.getUsername()) && StringUtils.isEmpty(userDTO.getTel()) ){
            return new ResultDTO(PlatformReturnCode.PLU401.name(),PlatformReturnCode.PLU401.getMsg(),"");
        }
        UserOutside  userOutside =  new UserOutside();
        userOutside.setUserName(userDTO.getUsername());
        userOutside.setUserTel(userDTO.getTel());
        userOutside.setIsEffect(1);
        List<UserOutside> userList = userOutsideRepository.findAll(Example.of(userOutside));
        if (userList.isEmpty()){
            return new ResultDTO(PlatformReturnCode.PLU404.name(),PlatformReturnCode.PLU404.getMsg(),"");
        }
        List<UserRole> roleList = userRoleService.findAllRoleByUserName(userList.get(0).getUserName());
        List<UserGroup> gruopList = userGroupService.findAllGroupByUserName(userList.get(0).getUserName());
        return new ResultDTO(PlatformReturnCode.PLU200.name(),
                PlatformReturnCode.PLU200.getMsg(), userToDto(userList.get(0),roleList,gruopList));
    }

    /**
     * 转化UserDTO
     * @param userOutside
     * @param roleList
     * @param gruopList
     * @return
     */
    private UserDTO userToDto(UserOutside userOutside, List<UserRole> roleList, List<UserGroup> gruopList) {
        List<UserRoleDTO> rDtolist = Lists.newArrayList();
        for(UserRole ur : roleList ){
            UserRoleDTO rDTO = new UserRoleDTO(ur.getId(),ur.getRoleName(),ur.getRoleClassify());
            rDtolist.add(rDTO);
        }
        List<UserGroupDTO> gDtolist = Lists.newArrayList();
        for(UserGroup ug : gruopList ){
            UserGroupDTO gDTO = new UserGroupDTO(ug.getId(),ug.getGroupName(),ug.getGroupType());
            gDtolist.add(gDTO);
        }
        return new UserDTO(userOutside.getUserName(),userOutside.getPassword(),
                userOutside.getNickName(),userOutside.getUserTel(),gDtolist,rDtolist);
    }



    @Override
    public ResultDTO findByRole(Long roleId) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultCode("200");
        List list = entityManager.createNativeQuery("select user_name from user_role where role_id=?").
                setParameter(1, roleId).getResultList();
        resultDTO.setResultMessage("查询成功");
        resultDTO.setData(list);
        return resultDTO;
    }

}
