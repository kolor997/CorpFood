package CorpFood.mail;

import CorpFood.model.service.UserResponseService;
import CorpFood.model.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ContentServiceImpl contentService;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine, ContentServiceImpl contentService){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.contentService = contentService;
    }

    @PutMapping("/send")
    public String send() throws MessagingException {

        Context context = new Context();
        context.setVariable("header", "");
        context.setVariable("title", "New CorpFood order is ready to go!");
//        context.setVariable("responses", userResponseService.listAllUserResponses());
        context.setVariable("descriptions", contentService.getAllFoodOrder());
        context.setVariable("price", contentService.getAllPrices());

        String body = templateEngine.process("mailTemplate", context);
        emailSender.sendEmail("tt.olech@gmail.com", "CorpFood order is ready to go!", body);
        return "index";
    }

}
