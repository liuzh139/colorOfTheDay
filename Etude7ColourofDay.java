/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etude7colourofday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author daisy
 */
public class Etude7ColourofDay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int doomDay = 200;
        TreeMap<Integer, String> calender = new TreeMap<>();

        for (int i = 1; i < doomDay; i++) {
            if (isPrime(i)) {
                if ("Monday".equals(isDay(i-1)) || "Thursday".equals(isDay(i-1))) {
                    calender.put(i, "Red");
                } else if ("Tuesday".equals(isDay(i-1)) || "Friday".equals(isDay(i-1))) {
                    calender.put(i, "Green");
                } else if ("Wednesday".equals(isDay(i-1)) || "Saturday".equals(isDay(i-1))) {
                    calender.put(i, "Blue");
                } else {
                    calender.put(7, "Gold");
                }
            } else {
               // calender.put(i, "Not prime!");
                calender.put(i, findColor(primeFactor(i)));
            }
        }
        
        System.out.println(calender);
    }

    /* Test if the date is a prime day */
    public static boolean isPrime(int date) {
        for (int i = 2; i < date; i++) {
            if (date % i == 0) {
                return false;
            }
        }
        return true;
    }

    /* Find only prime factor */
    public static TreeMap primeFactor(int date) {
        // command-line argument
        int n = date;
        List<Integer> factors = new ArrayList<Integer>();

        // for each potential factor i
        for (int i = 2; i * i <= n; i++) {
            // if i is a factor of N, repeatedly divide it out
            while (n % i == 0) {
                factors.add(i);
                n = n / i;
            }
        }  
        // if biggest factor occurs only once, n > 1
        factors.add(1);
        
        TreeMap<Integer, Integer> factorCount = new TreeMap<>(); //fisrt Integer will be the factor, 2nd one will be the count value
        for (int j = 1; j < 8; j++) {
            factorCount.put(j, Collections.frequency(factors, j));
        }
        return factorCount;
    }

    public static String findColor(TreeMap<Integer, Integer> factors) {

        int colRed = factors.get(1) + factors.get(4);
        int colGreen = factors.get(2) + factors.get(5);
        int colBlue = factors.get(3) + factors.get(6);

        if (colRed == colGreen && colRed == colBlue) {
            return "Gold";
        } else if (colRed == colGreen) {
            return "Blue";
        } else if (colRed == colBlue) {
            return "Green";
        } else if (colGreen == colBlue) {
            return "Red";
        } else {
            if (colRed > colGreen && colRed > colBlue) {
                return "Red";
            } else if (colBlue > colGreen && colBlue > colRed) {
                return "Blue";
            } else {
                return "Green";
            }
        }
    }
    /* Returning the day of the date */
    public static String isDay(int date) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int day = date % 7;
        return week[day];
    }
}
