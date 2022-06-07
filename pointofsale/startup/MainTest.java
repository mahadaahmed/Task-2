package se.kh.iv1350.pointofsale.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kh.iv1350.pointofsale.exceptions.ItemNotFoundException;
import se.kh.iv1350.pointofsale.exceptions.ServerConnectionFailException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(outputStreamCaptor);
        standardOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        outputStreamCaptor = null;
        System.setOut(standardOut);
    }

    @Test
    void startProgramMenuUITest() {
        Main.startProgramMenuUI();
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "press 1";
        assertTrue(printout.contains(expectedOutput), "Main started incorrectly");
    }

    @Test
    void itemNotFoundLogTest() {
        Main.itemNotFoundLog(new ItemNotFoundException("item not found"));
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "ItemNotFoundException was thrown";
        assertTrue(printout.contains(expectedOutput), "Item not found exception was not printed to view.");
    }

    @Test
    void serverConnectionFailedLogTest() {
        Main.serverConnectionFailedLog(new ServerConnectionFailException("Server connection failed."));
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "server being down";
        assertTrue(printout.contains(expectedOutput), "Server connection failed exception was not printed to view.");
    }

    @Test
    void askForPaymentPrintTest() {
        Main.askForPaymentPrint(10);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "It costs";
        assertTrue(printout.contains(expectedOutput), "Program did not ask for payment through view.");
    }

    @Test
    void paymentNotEnoughPrintTest() {
        Main.paymentNotEnoughPrint();
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "Inserted";
        assertTrue(printout.contains(expectedOutput), "Program did not ask for additional money through view.");
    }

    @Test
    void shutDownSystemQueryPrintTest() {
        Main.shutDownSystemQueryPrint();
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "shut down";
        assertTrue(printout.contains(expectedOutput), "Program did not ask to shut down system through view.");
    }


}