package se.kh.iv1350.pointofsale.integration;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kh.iv1350.pointofsale.model.Receipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private Printer printer;
    private Receipt receipt;
    private PrintStream standardOut;
    private ByteArrayOutputStream outputStreamCaptor;


    @BeforeEach
    void setUp() {
        printer = new Printer();
        receipt = new Receipt(LocalTime.now());
        outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(outputStreamCaptor);
        standardOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        printer = null;
        receipt = null;
        outputStreamCaptor = null;
        System.setOut(standardOut);
    }

    @Test
    void printReceiptMeatballsTest() {
        printer.printReceipt(receipt);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "Meatballs: ";

        assertTrue(printout.contains(expectedOutput), "Wrong message");
    }

    @Test
    void printReceiptPringlesTest() {
        printer.printReceipt(receipt);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "Pringles: ";

        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void printReceiptTotalExcludingTaxTest() {
        printer.printReceipt(receipt);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "excluding ";

        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void printReceiptTotalIncludingTaxTest() {
        printer.printReceipt(receipt);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "including ";

        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void printReceiptChangeTest() {

        printer.printReceipt(receipt);
        String printout = outputStreamCaptor.toString();
        String expectedOutput = "change ";

        assertTrue(printout.contains(expectedOutput));
    }
}