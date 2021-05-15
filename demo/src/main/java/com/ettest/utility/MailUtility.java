package com.ettest.utility;

import com.ettest.constants.Constants;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component("com.ettest.utility.MailUtility")
public class MailUtility {


  public void sendQrViaEmail(String emailId, String path) throws MessagingException {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", Constants.host);
    properties.put("mail.user", Constants.userName);
    properties.put("mail.password", Constants.password);
    Authenticator authenticator = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(Constants.userName, Constants.password);
      }
    };
    Session session = Session.getInstance(properties,authenticator);
    Message message = new MimeMessage(session);

    message.setFrom(new InternetAddress(Constants.userName));
    InternetAddress[] toAddress = { new InternetAddress(emailId) };
    message.setRecipients(Message.RecipientType.TO, toAddress);
    message.setSubject(Constants.subject);
    message.setSentDate(new Date());

    StringBuffer htmlBody = new StringBuffer("<html> The mail contains an encrypted QR code for you profile.<br>");
    htmlBody.append("<img src=\"cid:image1\" width=\"50%\" height=\"50%\" /><br>");
    htmlBody.append("Kindly scan the code using your scanner and enter the password.");
    htmlBody.append("</html>");
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(htmlBody, "text/html");
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);
    Map<String, String> mapInlineImages = new HashMap<>();
    mapInlineImages.put("image1",path);
    if (mapInlineImages != null && mapInlineImages.size() > 0) {
      Set<String> setImageID = mapInlineImages.keySet();
      for (String contentId : setImageID) {
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setHeader("Content-ID", "<" + contentId + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        String imageFilePath = mapInlineImages.get(contentId);
        try {
          imagePart.attachFile(imageFilePath);
        } catch (IOException ex) {
          ex.printStackTrace();
        }

        multipart.addBodyPart(imagePart);
      }
    }
    message.setContent(multipart);
    Transport.send(message);
  }
}
