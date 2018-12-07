package com.dahua.tech.easywork.platform.controller;

import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.api.dto.platform.FileInfoDTO;
import com.dahua.tech.easywork.platform.entity.FileInfo;
import com.dahua.tech.easywork.platform.service.Impl.FileServiceImpl;
import com.dahua.tech.easywork.platform.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@ComponentScan
public class FileServiceController {
//    @Autowired
//    FileServiceImpl fileService;
    @Resource
    FileUtils fdfsClient;

//    @RequestMapping(value = "/datafile/upload",method = RequestMethod.POST)
//    public ResultDTO upload(@RequestParam  MultipartFile file) throws Exception {
//        return  fileService.upload(file);
//    }


    @RequestMapping(value = "/datafile/upload",method = RequestMethod.POST)
    public ResultDTO upload(MultipartHttpServletRequest file, HttpServletRequest request) throws Exception {
        ResultDTO resultDTO =new ResultDTO();
        FileInfo fileInfo=new FileInfo();
        String param = request.getParameter("param");
        if(StringUtils.isEmpty(param)){
            resultDTO.setResultCode("500");
            resultDTO.setResultMessage("请添加有效文件");
        }
        InputStream is = null;
        String fileName = file.getFile(param).getName();
        try{
            long size = file.getFile(param).getSize();
            is = file.getFile(param).getInputStream();
            String path = fdfsClient.uploadFileStream(is,size,fileName);

            fileInfo.setEntireUrl(path);
            fileInfo.setOriginFileName(fileName);
            //fileInfo.setDocUrl(file.getContextPath());
            fileInfo.setDocUrl(path);
            resultDTO.setData(fileInfo);
            resultDTO.setResultCode("200");
            resultDTO.setResultMessage("succ");
        }catch (IOException e){
            resultDTO.setResultCode("500");
        }finally {
            is.close();
        }
        return resultDTO;

    }

    @RequestMapping(value = "/datafile/delete",method = RequestMethod.DELETE)
    public ResultDTO delete(@PathParam(value="url") String url){
         fdfsClient.deleteFile(url);
        ResultDTO resultDTO =new ResultDTO();
        resultDTO.setData(null);
        resultDTO.setResultCode("200");
        resultDTO.setResultMessage("delete succ");
        return resultDTO;
    }

}
