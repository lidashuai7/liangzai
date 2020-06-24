package com.cy.shop.common.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmailUtil {
	public static void sendEmail(String to, String content,String title) throws AddressException, MessagingException, Exception {
		Properties props = new Properties();
		// 1.1设置邮件发送的协议
//		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.transport.protocol", "smtp");
		MailSSLSocketFactory sf=new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		Session session = Session.getInstance(props);


		Message msg = new MimeMessage(session);
		msg.setSubject(title);
		msg.setText(content);
		msg.setFrom(new InternetAddress("371905996@qq.com"));
		
		Transport transport = session.getTransport();
		transport.connect("smtp.qq.com", "371905996@qq.com", "lvakpxtmtpslcaif");

		transport.sendMessage(msg, new Address[] {new InternetAddress(to)});
		transport.close();
	}
}

