package com.dahua.tech.easywork.platform.utils;

import com.dahua.tech.easywork.platform.entity.FileInfo;
import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resources;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @auther jiang_jielin 2018/12/4
 */

@Component
@ComponentScan
public class FileUtils  {

   @Autowired
   private FastFileStorageClient storageClient;
   @Autowired
   private FdfsWebServer fdfsWebServer;

    /**上传文件
     */
    public String uploadFile(MultipartFile file) throws IOException{


            StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(),
                    FilenameUtils.getExtension(file.getName()),null);
            return getResAccessUrl(storePath);
    }

    /**
    *将一段字符串生成文件进行上传
    * @param  content
     * @return file access url
     * @throws IOException
     */
    public String uploadFile(String content,String fileExtension){
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream,buff.length,fileExtension,null);
        return getResAccessUrl(storePath);
    }

    /*
       获取封装图片完整URL地址
     */
    private String getResAccessUrl(StorePath storePath){
        String fileUrl=fdfsWebServer.getWebServerUrl()+storePath.getFullPath();
       // String fileUrl = FileConst.storeServerHttpProto+"://"+FileConst.storeServerHost+":"+FileConst.storeServerPort+"/"
                //+storePath.getFullPath();
        return fileUrl;

    }

    /*
    delete file
     */
    public void deleteFile(String fileUrl){
        if(StringUtils.isEmpty(fileUrl)){
            return;
        }
        try{
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(),storePath.getPath());
        }catch (FdfsUnsupportStorePathException e){
            e.printStackTrace();
        }
    }
    /**
     * 上传图片并同时生成一个缩略图
     */
    public String uploadFileImage(MultipartFile file) throws IOException{

        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(),file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }

    /*
    上传文件
     */

    public String uploadFile(File file) throws  IOException{
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(inputStream,file.length(),FilenameUtils.getExtension(file.getName()),
                null);
        return getResAccessUrl(storePath);
    }
    /*
    文件流上传
     */
    public String uploadFileStream(InputStream inputStream,long size,String fileName) throws  IOException{

        StorePath storePath = storageClient.uploadFile(inputStream,size,FilenameUtils.getExtension(fileName),
                null);
        return getResAccessUrl(storePath);
    }
}