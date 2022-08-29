/**
 * Luvina Software JSC, 2022
 * SendMailRestController.java, Bui Quang Hieu
 */
package com.hieubq.SendMails;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hieubq.Beans.SendMail;

/**
 * @author BUI_QUANG_HIEU
 */
@RestController
public class SendMailRestController {
	@Autowired
	SmtpMailSender smtpMailSender;

	@PostMapping("/api/mail/send")
	public ResponseEntity<SendMail> sendMail(@RequestBody SendMail mail) throws MessagingException {
		try {
			String text = "<table width='100%' border='1' align='center'>" + "<tr align='center'>"
					+ "<td><b>Email <b></td>" + "<td><b>Connect<b></td>" + "</tr>";

			text = text + "<tr align='center'>" + "<td>" + mail.getTo() + "</td>" + "<td>" + "Hello world !" + "</td>"
					+ "</tr>";
	        mail.setContent(mail.getContent()+"\n"+ text);
			System.out.println(mail.toString());
			smtpMailSender.sendMail(mail.getTo(), mail.getSubject(), mail.getContent());
			return ResponseEntity.ok(mail);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().build();
		}
		
	}

}
