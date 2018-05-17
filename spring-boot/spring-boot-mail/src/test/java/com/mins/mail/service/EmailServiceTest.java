package com.mins.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/25
 * No struggle, talent how to match the willfulness.
 * Description: 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService service;

    @Test
    public void testSendSimpleMessage() {
        service.sendSimpleMessage(
                new String[]{"1594998836@qq.com", "zjmzjm970028@gmail.com"},
                "This is a test email",
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>forget</title>\n" +
                        "</head>\n" +
                        "   <body>\n" +
                        "       \n" +
                        "       <h1>Hello</h1>\n" +
                        "   </body>\n" +
                        "</html>",
                true
        );
    }

    @Test
    public void testSendMessageWithAttachment() {
        Map<String, String> pathAttachments = new HashMap<>();
        pathAttachments.put("filename", "path");
        pathAttachments.put("filename2", "path");
        service.sendMessageWithAttachment(new String[]{"1594998836@qq.com"}, "This is a test mail", "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>forget</title>\n" +
                        "</head>\n" +
                        "   <body>\n" +
                        "       \n" +
                        "       <h1>Hello</h1>\n" +
                        "   </body>\n" +
                        "</html>",
                pathAttachments, true);
    }
}
