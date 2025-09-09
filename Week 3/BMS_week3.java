//Bank Management System

import java.util.Scanner;
import java.util.regex.Pattern;

class BMS_week3 {

    static Scanner sc = new Scanner(System.in);
    static int max_user = 100;
    static String[][] userDetails = new String[max_user][4];
    // [0]=username, [1]=password, [2]=email, [3]=balance
    static int userCount = 0;

    public static void main(String[] args) {
        System.out.print("\n||================================||\n");
        System.out.print("\n--||-- BANK MANAGEMENT SYSTEM --||--\n");
        System.out.print("\n||================================||\n");
        int choice;
        while (true) {
            System.out.println("\n1 = Register \n2 = Login \n3 = Exit");
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();

            sc.nextLine();

            String temp_user_name;
            String temp_password;
            String temp_email;
            String email_regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
            String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?]).{6,}$";

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User Name : ");
                    temp_user_name = sc.nextLine();

                    while (true) {
                        System.out.print("Enter E-mail : ");
                        temp_email = sc.nextLine();
                        if (!Pattern.matches(email_regex, temp_email)) {
                            System.out.println("\n---Invalid email format. Please enter a valid email (e.g., user@example.com).---\n");
                        } else {
                            break;
                        }
                    }

                    System.out.print("Enter Starting Amount : ");
                    Double temp_amount = sc.nextDouble();
                    sc.nextLine();

                    while (true) {
                        System.out.print("Enter Password : ");
                        temp_password = sc.nextLine();
                        if (!Pattern.matches(password_regex, temp_password)) {
                            System.out.println("\n ---Password must be at least 6 characters long, "
                                    + "and include uppercase, lowercase, number, and special character.---\n");
                        } else {
                            break;
                        }
                    }

                    registerUser(temp_user_name.trim(), temp_email.trim(), temp_password.trim(), temp_amount);
                }
                case 2 -> {
                    System.out.print("Enter User Name : ");
                    temp_user_name = sc.nextLine();

                    System.out.print("Enter Password : ");
                    temp_password = sc.nextLine();

                    loginUser(temp_user_name, temp_password);
                }
                case 3 ->
                    System.out.println("\n---Exiting... Thank you!---\n");
                default ->
                    System.out.print("\n---Invalid Input!! Enter valid choice---\n");

            }
        }
    }

    // function for user registration
    static boolean registerUser(String user, String email, String pass, double amount) {
        if (userCount >= max_user) {
            System.out.print("\n---No space available for more users---\n");
            return false;
        }

        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(user)) {
                System.out.println("\n---Username already exists! Try another.---\n");
                return false;
            }
        }

        userDetails[userCount][0] = user;
        userDetails[userCount][1] = pass;
        userDetails[userCount][2] = email;
        userDetails[userCount][3] = String.valueOf(amount); // initial balance
        System.out.println("\n---Registration successful!!---");

        userCount++;
        return true;
    }

    // function for user login
    static boolean loginUser(String user, String pass) {
        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(user) && userDetails[i][1].equals(pass)) {
                System.out.println("\n---Login successful!---\n");
                System.out.println("Welcome " + userDetails[i][0]);
                funcforuser(i);
                return true;
            }
        }
        System.out.println("\n=> Invalid Password or User Name! Try again.");
        return false;
    }

    // function for user functionalities
    static void funcforuser(int user) {
        int choice;
        double amount;
        do {
            System.out.println("\n---Which operation you want to perform with your Account---");
            System.out.println("4 = Deposit Money");
            System.out.println("5 = Withdraw Money");
            System.out.println("6 = Show Balance");
            System.out.println("7 = View Account Details");
            System.out.println("8 = Log Out");
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 4 -> {
                    System.out.print("\nEnter amount for deposit : ");
                    amount = sc.nextDouble();
                    deposit(user, amount);
                }
                case 5 -> {
                    System.out.print("\nEnter amount for withdraw : ");
                    amount = sc.nextDouble();
                    withdraw(user, amount);
                }
                case 6 ->
                    showbalance(user);
                case 7 ->
                    details(user);
                case 8 ->
                    System.out.print("\n---Log Out---\n");
                default ->
                    System.out.print("\n---Invalid Input---\n");
            }
        } while (choice != 8);
    }

    // deposit function
    static boolean deposit(int user, double amount) {
        if (amount <= 0) {
            System.out.print("\n---Amount must be greater than 0---\n");
            return false;
        }

        double balance = Double.parseDouble(userDetails[user][3]);
        balance += amount;
        userDetails[user][3] = String.valueOf(balance);

        System.out.println("\n---Deposit Successful---\n");
        return true;
    }

    // withdraw function
    static boolean withdraw(int user, double amount) {
        double balance = Double.parseDouble(userDetails[user][3]);
        if (amount > balance) {
            System.out.println("\n---Not enough balance---\n");
            return false;
        } else {
            balance -= amount;
            userDetails[user][3] = String.valueOf(balance);
            System.out.println("\n---Withdraw Successful---\n");
        }
        return true;
    }

    // show balance
    static void showbalance(int user) {
        System.out.println("\nYour Balance = " + userDetails[user][3]);
    }

    // show details
    static void details(int user) {
        System.out.println("\n---Account Details---");
        System.out.println("Name     : " + userDetails[user][0]);
        System.out.println("Password : " + userDetails[user][1]);
        System.out.println("Email    : " + userDetails[user][2]);
        System.out.println("Balance  : " + userDetails[user][3]);
    }

}
