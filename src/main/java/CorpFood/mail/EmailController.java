package CorpFood.mail;

import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/corpFood/mail")
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final UserResponseService userResponseService;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine, UserResponseService userResponseService){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userResponseService = userResponseService;
    }

    @PutMapping("/send")
    public String send() throws MessagingException {

        Context context = new Context();
        context.setVariable("header", "");
        context.setVariable("title", "New CorpFood order is ready to go!");
        context.setVariable("description", getAllUserResponses());

        String body = templateEngine.process("mailTemplate", context);
        emailSender.sendEmail("tt.olech@gmail.com", "CorpFood order is ready to go!", body);
        return "index";
    }

    public String getAllUserResponses(){
        StringBuilder sb = new StringBuilder();
        Set<UserResponseDTO> result = new HashSet<>();

        Set<UserResponse> all = userResponseService.findAll();
        all.forEach(b -> result.add(new UserResponseDTO(b)));


        for (UserResponseDTO udto : result) {
            sb.append(udto.getUser().getFirstName() + "\n");
            sb.append(udto.getUser().getLastName() + "\n");
            sb.append(udto.getYourOrder() + "\n");
            sb.append(udto.getPrice().toString() + "\n");
            sb.append("\n");
        }

        return sb.toString();
    }
}
