package com.tahmidu.notes_web_app.event;

import com.tahmidu.notes_web_app.config.AppConfig;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.model.VerificationToken;
import com.tahmidu.notes_web_app.repository.IUserRepository;
import com.tahmidu.notes_web_app.repository.IVerificationTokenRepository;
import com.tahmidu.notes_web_app.util.TimeUtil;
import com.tahmidu.notes_web_app.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Async
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationComplete> {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final IVerificationTokenRepository verificationTokenRepository;
    private final IUserRepository userRepository;

    @Value("classpath:static/images/email_logo_banner.png")
    private Resource emailHeaderImg;

    @Autowired
    public RegistrationListener(JavaMailSender mailSender, SpringTemplateEngine templateEngine,
                                IVerificationTokenRepository verificationTokenRepository, IUserRepository userRepository) {

        this.mailSender = mailSender;
        this.templateEngine = templateEngine;

        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(OnRegistrationComplete event) {
        sendTokenEmail(event);
    }

    public void sendTokenEmail(OnRegistrationComplete event){

        User user = event.getUser();
        int token = TokenUtil.generateToken();
        int expiryTime = 15;

        VerificationToken verificationToken = new VerificationToken(token, TimeUtil.calculateCodeExpiryDate(expiryTime),
                true, user);

        verificationTokenRepository.save(verificationToken);

        final String htmlBody = prepareEmailStructureHTML(user.getFirstName(), String.valueOf(token));
        final String emailSubject = "Registration Confirmation Notes";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(AppConfig.EMAIL);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(emailSubject);
            mimeMessageHelper.setText(htmlBody, true);
            mimeMessageHelper.addInline("email_logo_banner", emailHeaderImg);
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    private String prepareEmailStructureHTML(String firstName, String tokenMsg){

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("first_name", firstName);
        templateModel.put("code", tokenMsg);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        return templateEngine.process("email_registration.html", thymeleafContext);
    }
}
