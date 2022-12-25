package com.bms.bookmanagementsystem.helper;

public class GenerateIsbnNumber {
    public static String generate() {
        String firstPart = "ISBN-";
        StringBuilder secondPart = new StringBuilder();
        StringBuilder thirdPart = new StringBuilder();
        StringBuilder fourthPart = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            secondPart.append((int) (Math.random() * 10));
        }

        for (int i = 0; i < 3; i++) {
            thirdPart.append((int) (Math.random() * 10));
        }

        for (int i = 0; i < 3; i++) {
            fourthPart.append((int) (Math.random() * 10));
        }

        return firstPart + secondPart + "-" + thirdPart + "-" + fourthPart;
    }
}
