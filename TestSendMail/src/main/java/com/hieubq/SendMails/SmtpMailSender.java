/**
 * Luvina Software JSC, 2022
 * SmtpMailSender.java, Bui Quang Hieu
 */
package com.hieubq.SendMails;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author BUI_QUANG_HIEU
 */

@Service
public class SmtpMailSender {
	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message
		
		helper.setFrom(from); // <--- THIS IS IMPORTANT
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);// true indicates body is html
		javaMailSender.send(message);
	}

}
