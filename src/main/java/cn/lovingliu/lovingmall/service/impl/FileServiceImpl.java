package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.service.FileService;
import cn.lovingliu.lovingmall.util.FTPUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-04
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file, String path) {
        // 图片原始名称
        String fileName = file.getOriginalFilename();
        // 图片扩展名
        String fileExtensionName  = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("上传文件的的文件名是: {},上传的路径: {},新文件名: {}",fileName,path,uploadFileName);

        // 根据路径创建创建文件夹
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);// 赋予可写权限
            fileDir.mkdirs();// 可创建多个级联文件 如:path = "/a/b/c/d" 就可以创建 a b c d 四个文件夹
        }
        File targetFile = new File(path,uploadFileName);
        try {
            // 将内存或临时磁盘文件写入到磁盘的指定文件中
            file.transferTo(targetFile);
            //< 文件上传成功
            // 将磁盘中的临时文件上传到Vsftpd服务器中
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //<已经上传到ftp服务器上
            // 删除磁盘文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

    @Override
    public Boolean delete(String[] filenames) {
        Boolean flag = false;
        try{
            flag = FTPUtil.deleteFile(filenames);
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }
}
