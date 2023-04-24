package test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ALLIN100B {

    public static void main(String[] args) {
        Set<String> account = new HashSet<String>();
        int x;
        for (int i = 0; i < 10; i++) {
            String stringTemp = get5Number();
            System.out.println(stringTemp);
        }

    }

    public static int getX(int max, int min) {
        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }

    public static String get5Number() {
        Set<Integer> ints = new HashSet<Integer>();
        Set<String> stringSet = new HashSet<String>();
        String stringTemp = "";

        while (true) {
            int x = getX(55,1);
            if(ints.contains(x) == true) {
                x = getX(55,1);
            } else  {
                ints.add(x);
                stringTemp = stringTemp + "_" + String.valueOf(x);
            }
            if (ints.size() == 6) {
                break;
            }
        }
        //System.out.println("String temp" + stringTemp);
        return stringTemp;
    }
}
