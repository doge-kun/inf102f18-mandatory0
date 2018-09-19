package no.uib.ii.inf102.f18.mandatory0;

import java.util.Scanner;

public class PolishToInfix {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Stack<String> limbs = new Stack<String>();

        String expression = reader.nextLine();
        Scanner sc = new Scanner(expression);

        while(sc.hasNext()) {
            String input = sc.next();

            if(input.equals("*") || input.equals("/") || input.equals("+") || input.equals("-")) {
                String a = limbs.pop();
                String b = limbs.pop();
                String result = "(" + b + input + a + ")";
                limbs.push(result);
            } else {
                limbs.push(String.valueOf(input));
            }
        }
        System.out.println(limbs.pop());
    }
}

