package com.dbybanez;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final byte NO_OF_ITEMS       = 3;
        final String[] ITEM_DETAILS  = {"name", "quantity", "price"};
        final byte NO_OF_DETAILS     = (byte) ITEM_DETAILS.length;
        final double SALES_TAX       = 6.25/100.00;
        DecimalFormat decimalFormat  = new DecimalFormat("0.00");
        double subtotal              = 0;
        final int INVOICE_NUMBER_MAX = 99999;

        System.out.println("Simple Invoice");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        String[][] items = new String[NO_OF_ITEMS][NO_OF_DETAILS];

        for (byte item_index = 0; item_index < NO_OF_ITEMS; item_index++) {
            for (byte desc_index = 0; desc_index < NO_OF_DETAILS; desc_index++) {
                System.out.print("Input " + ITEM_DETAILS[desc_index] + " of item no. " + (item_index + 1) + ": ");
                items[item_index][desc_index] = scanner.nextLine().trim();
            }
        }

        Random random = new Random();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        System.out.printf("============================================================\n");
        System.out.printf("%-30s\n", "Invoice for: " + name);
        System.out.printf("%-41s %-10s\n", "No: " + random.nextInt(INVOICE_NUMBER_MAX), "Date: " + today);
        System.out.printf("------------------------------------------------------------\n");
        System.out.printf("%-30s %-10s %-10s %-10s\n", "Item", "Quantity", "Price", "Total");
        System.out.printf("------------------------------------------------------------\n");

        for (byte item_index = 0; item_index < NO_OF_ITEMS; item_index++) {
            for (byte desc_index = 0; desc_index < NO_OF_DETAILS; desc_index++) {
                if (ITEM_DETAILS[desc_index] == "name") {
                    System.out.printf("%-30s ", items[item_index][desc_index]);
                } else {
                    System.out.printf("%-10s ", items[item_index][desc_index]);
                }
            }
            double qty = Double.parseDouble(items[item_index][1]);
            double price = Double.parseDouble(items[item_index][2]);
            double total = qty * price;
            subtotal += total;
            System.out.printf("%-10s", decimalFormat.format(total));
            System.out.printf("\n");
        }

        double subtotalWithTax = subtotal * SALES_TAX;

        System.out.printf("------------------------------------------------------------\n");
        System.out.printf("%-52s %-10s\n", "Subtotal", decimalFormat.format(subtotal));
        System.out.printf("%-52s %-10s\n", "6.25% sales tax", decimalFormat.format(subtotalWithTax));
        System.out.printf("%-52s %-10s\n", "Total", decimalFormat.format(subtotal + subtotalWithTax));
        System.out.printf("============================================================\n");
    }
}
