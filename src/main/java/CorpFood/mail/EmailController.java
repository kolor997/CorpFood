package CorpFood.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;


@RestController
@RequestMapping(value = "/corpFood/mail")
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @PutMapping("/send")
    public String send() throws MessagingException {
        Context context = new Context();
        context.setVariable("header", "New order!");
        context.setVariable("title", "New CorpFood order is ready to go!");
        context.setVariable("description", "CorpFood Inc. automatic message.");

        String body = templateEngine.process("mailTemplate", context);
        emailSender.sendEmail("tt.olech@gmail.com", "CorpFood order is ready to go!", body);
        return "index";
    }
}
