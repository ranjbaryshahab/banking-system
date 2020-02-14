package ir.maktab.java32.project.spring.bankingsystem.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.java32.project.spring.bankingsystem.exceptions.AccountException;
import ir.maktab.java32.project.spring.bankingsystem.exceptions.CardException;
import ir.maktab.java32.project.spring.bankingsystem.models.*;
import ir.maktab.java32.project.spring.bankingsystem.repositories.BossRepository;
import ir.maktab.java32.project.spring.bankingsystem.repositories.BranchRepository;
import ir.maktab.java32.project.spring.bankingsystem.repositories.CustomerRepository;
import ir.maktab.java32.project.spring.bankingsystem.repositories.EmployeeRepository;
import ir.maktab.java32.project.spring.bankingsystem.services.CardService;
import ir.maktab.java32.project.spring.bankingsystem.services.CardServiceImpl;
import ir.maktab.java32.project.spring.bankingsystem.utils.AuthenticationService;
import ir.maktab.java32.project.spring.bankingsystem.utils.DisplayMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class BankingSystemApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner1 = new Scanner(System.in);
    private static Scanner scannerNumber = new Scanner(System.in);
    private static CardService cardService = new CardServiceImpl();

    public static void main(String[] args) {
        //initialize();
        ObjectMapper objectMapper = new ObjectMapper();
        String message = "";

        Card login = null;
        while (login == null) {
            System.out.println("Please enter your card number : ");
            String cardNumber = scanner.nextLine();
            System.out.println("Please enter your password : ");
            String password = scanner.nextLine();
            try {
                login = cardService.login(cardNumber, password);
                System.out.println("You are login.\n");
            } catch (CardException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            if (AuthenticationService.getInstance().getLoginCard() != null) {
                DisplayMenu.customerMenu();
                String command = scanner1.nextLine();

                switch (command) {
                    case "1" -> {
                        System.out.println("Please enter balance : ");
                        Long balance = scannerNumber.nextLong();
                        try {
                            message = objectMapper.writeValueAsString(cardService.withdraw(balance));
                        } catch (JsonProcessingException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println(message);
                    }

                    case "2" -> {
                        System.out.println("Please enter destination card number : ");
                        String destinationCardNumber = scanner1.nextLine();
                        System.out.println("Please enter amount for transfer : ");
                        Long amount = scannerNumber.nextLong();
                        try {
                            try {
                                message = objectMapper.writeValueAsString(cardService.transfer(destinationCardNumber, amount));
                            } catch (JsonProcessingException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(message);
                        } catch (CardException | AccountException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case "3" -> System.out.println(cardService.getBalance());
                    case "4" -> {
                        System.out.println("Please enter old password : ");
                        String oldPassword = scanner1.nextLine();
                        System.out.println("Please enter new password : ");
                        String newPassword = scanner1.nextLine();

                        if (AuthenticationService.getInstance().getLoginCard().getCardPasswordInfo().getPassword().equals(oldPassword)) {
                            cardService.changeFirstPassword(newPassword);
                            System.out.println("Your password was changed !");
                        } else System.out.println("Old password is not correct !!!");
                    }

                    case "5" -> {
                        System.out.println("Please enter old second password : ");
                        String oldPassword = scanner1.nextLine();
                        System.out.println("Please enter new second password : ");
                        String newPassword = scanner1.nextLine();

                        if (AuthenticationService.getInstance().getLoginCard().getCardPasswordInfo().getSecondPassword().equals(oldPassword)) {
                            cardService.changeSecondPassword(newPassword);
                            System.out.println("Your second password was changed !");
                        } else System.out.println("Old password is not correct !!!");
                    }
                }
            }
        }

    }

    public static void initialize() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        BranchRepository.getInstance().save(applicationContext.getBean("piroziBranch", Branch.class));
        BossRepository.getInstance().save(applicationContext.getBean("piroziBranchBoss", Boss.class));
        EmployeeRepository.getInstance().save(applicationContext.getBean("piroziBranchEmployee1", Employee.class));
        EmployeeRepository.getInstance().save(applicationContext.getBean("piroziBranchEmployee2", Employee.class));
        CustomerRepository.getInstance().save(applicationContext.getBean("piroziBranchCustomer1", Customer.class));
        CustomerRepository.getInstance().save(applicationContext.getBean("piroziBranchCustomer2", Customer.class));
    }
}
