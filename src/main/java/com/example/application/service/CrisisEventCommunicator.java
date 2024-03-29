package com.example.application.service;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
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
    private final String AUTH_TOKEN= "31525fedbd7af6415592ccdd7571a2fa";
    private String name;
    private String description;
    private LocalDateTime meetingDate;

    private final String code = RandomStringUtils.randomAlphanumeric(32);

    public CrisisEventCommunicator(String name, String description, LocalDateTime meetingDate) {
        this.name = name;
        this.description = description;
        this.meetingDate = meetingDate;
    }

    public boolean sendSms(String toPhoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        try {
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber("+18125059409"),
                    this.description + "\nhttp://localhost:8080/signup?code=" + this.code + "&phone-number=" + toPhoneNumber
            ).create();

            return true;
        } catch (ApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    * toEmail to send email correctly. toPhoneNumber acts as get GET request param
    * */
    public boolean sendEmail(String toEmail, String toPhoneNumber) {

        try {
            Email email = new SimpleEmail();
            String url = "http://localhost:8080/sign-up?phone-number=" + toPhoneNumber;

            email.setHostName("smtp.office365.com");
            email.setSmtpPort(587);
            //email.setSSLOnConnect(true);
            //TODO - need to set default authenticator

            email.setStartTLSEnabled(true);
            email.setFrom("jonas@fibrenetix.com");
            email.addTo(toEmail);
            email.setSubject("Reset Password - Fibrenetix Calculator");
            email.setMsg(url);
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }

    }

}
