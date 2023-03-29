package com.dev.backend.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private Configuration fmConfiguration;

  @Value("${spring.mail.username}")
  private String mailFrom;

  public String sendTextEmail(String mailTo, String subject, String message) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(mailFrom);
      simpleMailMessage.setTo(mailTo);
      simpleMailMessage.setSubject(subject);
      simpleMailMessage.setText(message);
      javaMailSender.send(simpleMailMessage);
      return "Email sended successfully!";
    } catch (Exception e) {
      return "Error sending the email";
    }
  }

  public void sendTemplateEmail(String mailTo, String subject, Map<String, Object> properties) {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setFrom(mailFrom);
      mimeMessageHelper.setTo(mailTo);
      mimeMessageHelper.setText(getTemplateContent(properties), true);

      javaMailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  public String getTemplateContent(Map<String, Object> model) {
    StringBuffer content = new StringBuffer();

    try {
      content.append(FreeMarkerTemplateUtils
          .processTemplateIntoString(fmConfiguration.getTemplate("recovery-password-email.flth"), model));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content.toString();
  }
}
