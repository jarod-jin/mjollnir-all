package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.entity.FileInfo;
import com.dahua.tech.easywork.platform.service.IFileService;
import com.dahua.tech.easywork.platform.utils.FileUtils;
import com.google.common.primitives.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.dahua.tech.easywork.api.dto.platform.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

@Service
@ComponentScan
public class FileServiceImpl implements IFileService  {


   public ResultDTO upload(MultipartFile file) {

       ResultDTO resultDTO = new ResultDTO();
       FileInfo fileInfo = new FileInfo();
       FileUtils fileClients = new FileUtils();

       try {
          // fileClients.uploadFile(file);
           String entireUrl = fileClients.uploadFile(file);
           fileInfo.setOriginFileName(file.getName());
           fileInfo.setEntireUrl(entireUrl);
           int index = entireUrl.lastIndexOf("\\");
           String docUrl = entireUrl.substring(0,index);
           fileInfo.setDocUrl(docUrl);
           fileInfo.setModifyDate(new Date());
           resultDTO.setResultMessage("上传成功");
           resultDTO.setResultCode("200");
           resultDTO.setData(fileInfo);

       } catch (IOException e) {
           e.printStackTrace();
           resultDTO.setResultMessage("上传失败");
           resultDTO.setResultCode("500");
           resultDTO.setData(null);
       }
       return resultDTO;
    }

    public ResultDTO download(MultipartFile file){

       return new ResultDTO();
    }

    public ResultDTO delete(MultipartFile file){

       return new ResultDTO();
    }

}
