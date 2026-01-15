package br.tsi.comichub.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.tsi.comichub.dao.LoanDAO;
import br.tsi.comichub.model.Account;
import br.tsi.comichub.model.Loan;
import br.tsi.comichub.utils.Mail;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AutoReminder implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("AutoReminderScheduler iniciado");

        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleWithFixedDelay(
            this::checkOverdueLoans,
            1,              // delay inicial
            24,              // intervalo
            TimeUnit.HOURS  // unidade
        );
    }

    private void checkOverdueLoans() {
    	
        System.out.println("Verificação de atrasos executando...");

        List<Loan> lateLoans = new LoanDAO().findLateLoans();
        List<Account> users = new ArrayList<>();

        if (lateLoans != null && !lateLoans.isEmpty()) {

            for (Loan loan : lateLoans) {
            	
                Account user = loan.getUser();
                
                if (!users.contains(user))
                    users.add(user);
            }

            Mail mailSender = new Mail();
            String content = "Você tem revistas pendentes de devolução cujo prazo já expirou. Por favor, devolva.";

            for (Account user : users) {
            	
                mailSender.sendMail(user.getMail(), content, "ComicHub - Você tem devoluções atrasadas!");
                System.out.println(
                	    "Enviando email para " + user.getMail() +
                	    " em " + LocalTime.now()
                	);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            
            }
            
            System.out.println("E-mails enviados com sucesso.");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	
        if (scheduler != null) {
            scheduler.shutdownNow();
            System.out.println("AutoReminderScheduler finalizado");
        }
    }
}
