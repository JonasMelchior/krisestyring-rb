package com.example.application.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.time.LocalDateTime;

public class CrisisEventCommunicator {

    private final String ACCOUNT_SID= "AC7bfa25f912c587160e8b4d080ef2895c";
    private final String AUTH_TOKEN= "ae928035f8344552d5734f3b72288c0a";
    private String name;
    private String description;
    private LocalDateTime meetingDate;

    private final String code = RandomStringUtils.randomAlphanumeric(32);

    public CrisisEventCommunicator(String name, String description, LocalDateTime meetingDate) {
        this.name = name;
        this.description = description;
        this.meetingDate = meetingDate;
    }

    public void sendSms(String toPhoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber("+18125059409"),
                this.description + "\nhttp://localhost:8080/signup?code=" + this.code + "&phone-number=" + toPhoneNumber
        ).create();

        System.out.println(message.getSid());
    }

    /*
    * toEmail to send email correctly. toPhoneNumber acts as get GET request param
    * */
    public void sendEmail(String toEmail, String toPhoneNumber) {

        try {
            Email email = new SimpleEmail();
            String url = "http://localhost:8080/sign-up?phone-number=" + toPhoneNumber;

            email.setHostName("smtp.office365.com");
            email.setSmtpPort(587);
            //email.setSSLOnConnect(true);
            email.setAuthenticator(new DefaultAuthenticator("jonas@fibrenetix.com", "Majo1230192"));
            email.setStartTLSEnabled(true);
            email.setFrom("Jonas@fibrenetix.com");
            email.addTo(toEmail);
            email.setSubject("Reset Password - Fibrenetix Calculator");
            email.setMsg(url);
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }

}
