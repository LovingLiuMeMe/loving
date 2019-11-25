package cn.lovingliu.lovingmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：LovingLiu
 * @Description: 商品的service
 * @Date：Created in 2019-11-04
 */
public interface FileService {
    String upload(MultipartFile file, String path);
    Boolean delete(String[] filenames);
}
