package com.example.security.controller;


import com.example.security.model.CodeEntity;
import com.example.security.security.SmsCode;
import com.example.security.service.CodeService;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;


@RestController
public class CodeSenderController {
    private final String ACCOUNT_SID = "ACb206a94b5cc805b15d629251f30a08ff";
    private final String AUTH_TOKEN = "78b5ca2d410880c5663332e472c05452";
    private final String PHONE_NUMBER = " +12059645458";

    private final CodeService codeService;

    public CodeSenderController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/phone/{username}")
    public void sendSms(@RequestBody String phoneNumber, @PathVariable String username) {
        String code = String.format("%06d", new Random().nextInt(999999));
        String info = "Your verification code is  " + code;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        MessageCreator message = Message.create(ACCOUNT_SID,
                new PhoneNumber(phoneNumber),
                new PhoneNumber(PHONE_NUMBER),
                info);
        message.execute();
        codeService.add(new CodeEntity(username, code, LocalDateTime.now().plusSeconds(1000)));
    }
}
