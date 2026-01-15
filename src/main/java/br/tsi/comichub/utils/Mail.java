package br.tsi.comichub.utils;

import java.util.List;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

public class Mail {

    private static final String TOKEN = "";
    private static final long INBOX_ID = 4306917L;
    
    public boolean sendCode(String destination, String code) {
        
    	String subject = "ComicHub - Seu código de acesso",
    		   content = "Olá! Seu código de verificação é: " + code;
    	
    	return sendMail(destination, content, subject);
    }
    
    public boolean sendMail(String destination, String content, String subject) {
        
        final MailtrapConfig config = new MailtrapConfig.Builder().token(TOKEN).build();
        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail mail = MailtrapMail.builder()
            .from(new Address("hello@demomailtrap.co", "ComicHub"))
            .to(List.of(new Address(destination)))
            .subject(subject)
            .text(content)
            .build();

        try {
            client.switchToEmailTestingApi(INBOX_ID);
            client.send(mail);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao enviar: " + e.getMessage());
            return false;
        }
    }
    
    
}