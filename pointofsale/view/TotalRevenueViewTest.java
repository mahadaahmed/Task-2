package se.kh.iv1350.pointofsale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kh.iv1350.pointofsale.integration.AccountingSystem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    private AccountingSystem accountingSystem;
    private TotalRevenueView totalRevenueView;
    private PrintStream standardOut;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        accountingSystem = new AccountingSystem();
        totalRevenueView = new TotalRevenueView();
        outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(outputStreamCaptor);
        standardOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        accountingSystem = null;
        totalRevenueView = null;
        outputStreamCaptor = null;
        System.setOut(standardOut);
    }

    @Test
    void updateTest() {
        totalRevenueView.update(accountingSystem.getAmountOfMoneyInSystem());

        String printout = outputStreamCaptor.toString();
        String expectedOutput = ("the current total revenue is ");

        assertTrue(printout.contains(expectedOutput), "Wrong message");
    }
}