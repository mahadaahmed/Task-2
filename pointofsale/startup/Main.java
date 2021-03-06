package se.kh.iv1350.pointofsale.startup;

import se.kh.iv1350.pointofsale.controller.Controller;
import se.kh.iv1350.pointofsale.exceptions.ItemNotFoundException;
import se.kh.iv1350.pointofsale.exceptions.ServerConnectionFailException;
import se.kh.iv1350.pointofsale.integration.Printer;
import se.kh.iv1350.pointofsale.view.View;

import java.util.Scanner;

/**
 * Contains main method that starts entire application.
 */
public class Main {
    /**
     * The main method that starts entire application.
     *
     * @param args The application does not take any command-line parameters.
     * */
    public static void main(String[] args) {

        Printer printer = new Printer();
        Controller contr = new Controller(printer);
        View view = new View(contr);
        String answerForSale = "";
        String shutDownSystem = "no";
        while(!shutDownSystem.equals("yes")) {
            contr.startSale();
            answerForSale = "";
            while (!answerForSale.equals("terminate")) {
                Scanner sc = new Scanner(System.in);
                startProgramMenuUI();
                answerForSale = sc.nextLine();

                switch (answerForSale) {
                    case "1":
                        try {
                            contr.addItemToSale(1);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            itemNotFoundLog(e);
                        } catch (ServerConnectionFailException p) {
                            System.out.println(p.getMessage());
                            serverConnectionFailedLog(p);
                        }
                        break;

                    case "2":
                        try {
                            contr.addItemToSale(2);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            itemNotFoundLog(e);
                        } catch (ServerConnectionFailException p) {
                            System.out.println(p.getMessage());
                            serverConnectionFailedLog(p);
                        }
                        break;

                    case "3":
                        try {
                            contr.addItemToSale(3);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            itemNotFoundLog(e);
                        } catch (ServerConnectionFailException p) {
                            System.out.println(p.getMessage());
                            serverConnectionFailedLog(p);
                        }

                        break;

                    case "terminate":
                        int totalPriceWithTax = contr.endSale();
                        int totalPayment = 0;

                        askForPaymentPrint(totalPriceWithTax);
                        int payment = sc.nextInt();
                        totalPayment += payment;
                        if (payment < totalPriceWithTax)
                        {
                            totalPriceWithTax -= payment;
                            while (totalPriceWithTax > 0) {
                                paymentNotEnoughPrint();
                                payment = sc.nextInt();
                                totalPayment += payment;
                                totalPriceWithTax -= payment;
                            }
                        }
                        contr.recievesPayment(totalPayment);

                        break;


                    default:
                        try {
                            contr.addItemToSale(Integer.parseInt(answerForSale));
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            itemNotFoundLog(e);
                        } catch (ServerConnectionFailException p) {
                            System.out.println(p.getMessage());
                            serverConnectionFailedLog(p);
                        }
                        break;
                }
            }
            shutDownSystemQueryPrint();
            Scanner stringScanner = new Scanner(System.in);
            shutDownSystem = stringScanner.nextLine();
        }
    }

    public static void startProgramMenuUI() {
        System.out.println("press 1 for meatball \npress 2 for pringles \nwrite terminate to end sale ");
    }

    public static void itemNotFoundLog(ItemNotFoundException e) {
        System.out.println("LOG: ItemNotFoundException was thrown due to " + e.getMessage());
        System.out.println("LOG: stacktrace: "+ e.getStackTrace());
    }

    public static void serverConnectionFailedLog(ServerConnectionFailException p) {
        System.out.println("LOG: Could not add item to sale due to server being down");
        System.out.println("LOG: stacktrace: "+ p.getStackTrace());
    }

    public static void askForPaymentPrint(int totalPriceWithTax) {
        System.out.println("It costs " + totalPriceWithTax + " kr");
        System.out.println("How much will you pay");
    }

    public static void paymentNotEnoughPrint() {
        System.out.println("Inserted payment is not enough, please insert more money");
    }

    public static void shutDownSystemQueryPrint() {
        System.out.println("Do you want to shut down the system? Write yes or no");
    }

}
