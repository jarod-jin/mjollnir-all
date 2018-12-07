package com.dahua.tech.easywork.platform.service;


import com.dahua.tech.easywork.core.dto.ResultDTO;
import org.springframework.web.multipart.MultipartFile;
/**
 * @auther jiang_jielin 2018/12/4
 */

public interface IFileService {
    ResultDTO upload(MultipartFile file);

    ResultDTO download(MultipartFile  file);

    ResultDTO delete(MultipartFile  file);
}
