package cn.lovingliu.lovingmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-04
 */
@Slf4j
public class FTPUtil {
    private static String FTPIP = "49.235.110.134";
    private static String FTPUSER = "ftpuser";
    private static String FTPPASS = "123456";
    private static Integer FTPPORT = 443;


    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public FTPUtil(String ip,int port,String user,String pwd){
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    private boolean connectServer(String ip,int port,String user,String pwd){

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);// 是否登录成功
        } catch (IOException e) {
            log.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }
    /**
     * @Desc 上传文件
     * @Author LovingLiu
     */

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if(connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                // 1.设置路径
                ftpClient.changeWorkingDirectory(remotePath);
                // 2.设置缓冲区大小
                ftpClient.setBufferSize(1024);
                // 3.设置编码
                ftpClient.setControlEncoding("UTF-8");
                // 4.设置文件类型为二进制文件类型（避免乱码）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // 5.打开被动模式 因为vsftpd服务器开放的是被动模式的端口范围
                ftpClient.enterLocalPassiveMode();

                for(File fileItem : fileList){
                    fis = new FileInputStream(fileItem);
                    // 存储文件
                    ftpClient.storeFile(fileItem.getName(),fis);
                }

            } catch (IOException e) {
                log.error("上传文件异常",e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                // 别忘了关闭流和释放资源
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }
    /**
     * @Desc 删除图片
     * @Author LovingLiu
     */
    private boolean deleteFile(String remotePath,String[] filenames){
        boolean flag = false;
        if(connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                log.info("开始删除文件");
                ftpClient.changeWorkingDirectory(remotePath);
                for(String filename: filenames){
                    ftpClient.dele(filename);
                }
                flag = true;
                log.info("删除文件成功");
            } catch (Exception e) {
                log.info("删除文件失败");
                e.printStackTrace();
            } finally {
                if (ftpClient.isConnected()) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @Desc 删除图片
     * @Author LovingLiu
     */
    public static boolean deleteFile(String[] filenames) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(FTPIP,FTPPORT,FTPUSER,FTPPASS);
        log.info("开始连接ftp服务器");
        boolean result = ftpUtil.deleteFile("images",filenames);
        log.info("开始连接ftp服务器,删除结果:{}",result);
        return result;
    }

    /**
     * @Desc 上传图片
     * @Author LovingLiu
    */
    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(FTPIP,FTPPORT,FTPUSER,FTPPASS);
        log.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("images",fileList);// 即/ftpfile/images
        log.info("开始连接ftp服务器,结束上传,上传结果:{}",result);
        return result;
    }
}
