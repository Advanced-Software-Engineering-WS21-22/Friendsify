package aau.at.friendsifyemailservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConfiguration {

    @Value("${spring.mail.host}")
    private String mailServer;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String serverAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String tlsEnabled;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailServer);
        javaMailSender.setPort(mailServerPort);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", serverAuth);
        properties.put("mail.smtp.stattls.enable", tlsEnabled);
        properties.put("mail.debug", "true");

        return javaMailSender;
    }
}
