package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author：LovingLiu
 * @Description: 邮件的发送
 * @Date：Created in 2019-10-30
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    /**
     * @Desc 发送普通文本邮件
     * @Author LovingLiu
    */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        /**
         * 设置主题
        */
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
    /**
     * @Desc 发送HTML格式的邮件
     * @Author LovingLiu
    */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("亲爱的用户<strong>"+to+"</strong>,您的当前重置密码的验证码是:<strong>"+content+"</strong>");
        stringBuilder.append("</br>");
        stringBuilder.append("<p>如果您有什么意见和建议 欢迎发送邮件至<strong>"+from+"</strong></p>");
        stringBuilder.append("<p>如果您在使用过程中有什么问题 欢迎<span style=\"color:red;\">点击联系</span><a title=\"点击联系客服\" href=\"tencent://message/?uin=416180475&Site=http://vps.shuidazhe.com&Menu=yes\">QQ客服</a></p>");
        stringBuilder.append("</body></html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送人
            mimeMessageHelper.setFrom(from);
            //邮件接收人
            mimeMessageHelper.setTo(to);
            //邮件主题
            mimeMessage.setSubject(subject);
            //邮件内容，html格式
            mimeMessageHelper.setText(stringBuilder.toString(), true);
            //发送
            javaMailSender.send(mimeMessage);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }
    /**
     * @Desc 发送带有附件的邮件
     * @Author LovingLiu
    */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName, file);
            javaMailSender.send(mimeMessage);

            //日志信息
            log.info("带【附件】邮件已经发送");
        } catch (MessagingException e) {
            log.error("发送带【附件】邮件邮件时发生异常！", e);
        }
    }
}
