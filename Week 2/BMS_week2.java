//Bank Management System(java project)

import java.util.Scanner;

class BMS_week2 {

    static Scanner sc = new Scanner(System.in);
    static int max_user = 100;
    static String[][] userDetails = new String[max_user][3];
    static int userCount = 0;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.print("\n||================================||\n");
            System.out.print("\n--||-- BANK MANAGEMENT SYSTEM --||--\n");
            System.out.print("\n||================================||\n");
            System.out.println("\n1 = Register \n2 = login \n3 = Exit"); //give numeric choice to user 
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            //to perform operation according to user choice
            String temp_user_name;
            String temp_password;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User Name : ");
                    temp_user_name = sc.nextLine();

                    System.out.print("Enter Password : ");
                    temp_password = sc.nextLine();

                    registerUser(temp_user_name, temp_password);
                    break;
                }
                case 2 -> {
                    System.out.print("Enter User Name : ");
                    temp_user_name = sc.nextLine();

                    System.out.print("Enter Password : ");
                    temp_password = sc.nextLine();

                    loginUser(temp_user_name, temp_password);
                    break;
                }
                case 3 ->
                    System.out.println("\n---Exiting... Thank you!---\n");
                default ->
                    System.out.print("\n---Invalid Input---\n");
            }
        } while (choice != 3);
    }

    // function for user registration
    static boolean registerUser(String user, String pass) {
        if (userCount >= max_user) {
            System.out.print("\n---No space available for more users---\n");
            return false;
        }

        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(user)) {
                System.out.println("\n---Username is already exist !! try another user name.---\n");
                return false;
            }
        }
        userDetails[userCount][0] = user;
        userDetails[userCount][1] = pass;
        userDetails[userCount][2] = "0.0";
        System.out.println("\n---Registration successful!!---");

        userCount++;
        return true;
    }

    // function for user login
    static boolean loginUser(String user, String pass) {

        //validation for user name & password
        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(user) && userDetails[i][1].equals(pass)) {
                System.out.println("\n---Login successful!---\n");
                System.out.println("\nWelcome " + userDetails[i][0]);
                // System.out.println("Your Balance = " + userDetails[i][2]);
                funcforuser(i);
                return true;
            }
        }
        System.out.println("\n => Invalid Password or User Name !! try again");
        return false;
    }

    //function for user functionalities
    static void funcforuser(int user) {
        int choice;
        double amount;
        do {
            System.out.println("\n---Which operation you want to perform with your Account---");
            System.out.println("\n4 = Deposit Money \n5 = Withdraw Money \n6 = Show Balance \n7 = View Account Details\n8 = Log Out");
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine(); //to clear buffer
            switch (choice) {
                case 4 -> {
                    System.out.print("\nEnter amount for deposit : ");
                    amount = sc.nextDouble();
                    deposit(user,amount);
                }
                case 5 ->{
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

    //function for deposit amount
    static boolean deposit(int user,double amount) {
        if (amount <= 0) {
            System.out.print("\n---Amount must be greater than 0---\n");
            return false;
        }

        double balance = Double.parseDouble(userDetails[user][2]);
        balance += amount;
        userDetails[user][2] = String.valueOf(balance);

        System.out.println("\n---Deposit Successful---\n");
        return true;
    }

    //function for withdraw amount
    static boolean  withdraw(int user,double amount) {
        double balance = Double.parseDouble(userDetails[user][2]);
        if (amount > balance) {
            System.out.println("\n---Not enough balance---\n");
            return false;
        } else {
            balance -= amount;
            userDetails[user][2] = String.valueOf(balance);
            System.out.println("\n---Withdraw Successful---\n");
        }
        return true;
    }

    //function for show balance
    static void showbalance(int user) {
        System.out.println("\nYour Balance = " + userDetails[user][2]);
    }

    //function for show user details
    static void details(int user) {
        System.out.print("\nName    :" + userDetails[user][0]);
        System.out.print("\nPassword:" + userDetails[user][1]);
        System.out.println("\nBalance :" + userDetails[user][2]);
    }
}
