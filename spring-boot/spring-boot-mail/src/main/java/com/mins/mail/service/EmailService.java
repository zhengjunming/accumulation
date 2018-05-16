package com.mins.mail.service;

import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/25
 * No struggle, talent how to match the willfulness.
 * Description: 逻辑层接口
 */
public interface EmailService {
    /**
     * 发送简单文本邮件
     *
     * @param to 收件人邮箱数组
     * @param subject 标题
     * @param text 正文
     * @param html 是否为html文本
     */
    void sendSimpleMessage(String[] to, String subject, String text, boolean html);

    /**
     * 发送带有附件的邮件
     *
     * @param to 收件人邮箱数组
     * @param subject 标题
     * @param text 正文
     * @param pathToAttachments 附件路径
     * @param html 是否为html文本
     */
    void sendMessageWithAttachment(String[] to, String subject, String text, Map<String, String> pathToAttachments, boolean html);
}
