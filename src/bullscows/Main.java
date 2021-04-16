package bullscows;

import java.util.Scanner;
public class Main {
    static Scanner s = new Scanner(System.in);
    static String secret;
    static String guess;
    static int n;
    static int pos;

    static void driver() {
        String x = "";
        String y = "";
        try {
            System.out.println("Input the length of the secret code:");
            x = s.nextLine();
            n = Integer.parseInt(x);
            System.out.println("Input the number of possible symbols in the code:");
            y = s.nextLine();
            pos = Integer.parseInt(y);
        } catch (Exception e) {
            System.out.println("Error: \"" + x + "\" isn't a valid number.");
        }
        //s.nextLine();
        if(pos < n || n == 0 || pos == 0) {
            System.out.printf("Error: it's not possible to generate a code with a " +
                    "length of %d with %d unique symbols.", n, pos);
            return;
        }
        if(pos>36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        secret = code_generator();
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        do{
            System.out.println("Turn " + turn + ":");
            guess = s.nextLine();
            turn++;
        }while(!test());
    }
    static boolean test() {
        String t_secret = secret;
        String t_guess;
        int bulls = 0;
        int cows = 0;
        for(int i = 0; i < n; i++) {
            t_guess = guess;
            for(int j = 0; j < n; j++) {
                if((secret.charAt(i)) == (guess.charAt(j))) {
                    if(i==j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
                t_guess = t_guess.substring(0, t_guess.length()-1);
            }
            t_secret = t_secret.substring(0, t_secret.length()-1);
        }
        if(bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        }
        else if(bulls == 0) {
            if (cows > 1)
                System.out.printf("Grade: %d cow(s).\n", cows);
            else
                 System.out.printf("Grade: %d cow.\n", cows);
        }
        else if(cows == 0) {
            if(bulls>1)
                System.out.printf("Grade: %d bull(s).\n", bulls);
            else
                System.out.printf("Grade: %d bull.\n", bulls);
        }
        else {
            if(bulls == 1 && cows == 1) {
                System.out.printf("Grade: %d bull and %d cow.\n", bulls, cows);

            }
            else if(bulls == 1) {
                System.out.printf("Grade: %d bull and %d cows.\n", bulls, cows);
            }
            else if (cows == 1) {
                System.out.printf("Grade: %d bulls and %d cow.\n", bulls, cows);
            }
            else {
                System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows);
            }
        }
        if(bulls == n) {
            System.out.printf("Congratulations! You guessed the secret code.");
            return true;
        }
        else
            return false;
    }

    static String code_generator() {
        if(n < 36) {
            StringBuilder secret = new StringBuilder();
            String AlphaNumericString = "0123456789" + "abcdefghijklmnopqrstuvxyz";
            if(pos<36)
            AlphaNumericString = AlphaNumericString.substring(0, pos + 1);
            while(secret.length()!=n) {
                for(int i = 0; i < AlphaNumericString.length(); i++) {
                    int index = (int) (AlphaNumericString.length()*Math.random());
                    if(!secret.toString().contains(String.valueOf(AlphaNumericString.charAt(index)))){
                        secret.append(AlphaNumericString.charAt(index));
                    }
                    if(secret.length() == n) {
                        break;
                    }
                }
            }
            if(pos>10) {
                char f = (char) ('a' + pos - 11);
                System.out.println("The secret is prepared: " + secret.toString().replaceAll("[a-z0-9]", "*") + " (0-9, a-" + f + ").");
            }
            else {
                System.out.println("The secret is prepared: " + secret.toString().replaceAll("[a-z0-9]", "*") + " (0-9");
            }
            return secret.toString();
        }
        else {
            System.out.println("Error: can't generate a secret " +
                        "number with a length of "+ n + " because there aren't enough unique digits.");
                return "";

        }
    }

    public static void main(String[] args) {
        driver();
    }
}
