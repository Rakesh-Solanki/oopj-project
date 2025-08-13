//Bank Management System(java project)
import java.util.Scanner;

class Bank {

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
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> System.out.println("\n---Exiting... Thank you!---\n");
                default -> System.out.print("\n---Invalid Input---\n");
            }
        } while (choice != 3);
    }

    // function for user registration
    static void registerUser() {
        if (userCount >= max_user) {
            System.out.print("\n---There are not space for another users---\n");
            return;
        }

        System.out.print("\nEnter User Name : ");
        String temp_user_name = sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(temp_user_name)) {
                System.out.println("\n---Username is already exist !! try another user name.---\n");
                return;
            }
        }
        userDetails[userCount][0] = temp_user_name;

        System.out.print("Enter Password : ");
        userDetails[userCount][1] = sc.nextLine();

        userDetails[userCount][2] = "0.0";

        System.out.println("\n---Registration successful!!---");

        userCount++;
    }

    // function for user login
    static void loginUser() {
        System.out.print("\nEnter User Name : ");
        String temp_user_name = sc.nextLine();

        System.out.print("Enter Password : ");
        String temp_password = sc.nextLine();

        //validation for usser name & password
        for (int i = 0; i < userCount; i++) {
            if (userDetails[i][0].equals(temp_user_name) && userDetails[i][1].equals(temp_password)) {
                System.out.println("\n---Login successful!---\n");
                System.out.println("\nWelcome " + userDetails[i][0]);
                // System.out.println("Your Balance = " + userDetails[i][2]);
                funcforuser(i);
                return;
            }
        }
        System.out.println("\n => Invalid Password or User Name !! try again");
    }

    //function for user functionalities
    static void funcforuser(int user){
        int choice;
        do{
            System.out.println("\n---Which operation you want to perform with your Account---");
            System.out.println("\n4 = Deposit Money \n5 = Withdraw Money \n6 = Show Balance \n7 = View Account Details\n8 = Log Out");
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();
            switch(choice)
            {
                case 4 -> deposit(user);
                case 5 -> withdraw(user);
                case 6 -> showbalance(user);   
                case 7 -> details(user); 
                case 8 -> System.out.print("\n---Log Out---\n");
                default -> System.out.print("\n---Invalid Input---\n");    
            }
        }while(choice != 8);
    }

    //function for deposit amount
    static void deposit(int user) {
        System.out.print("\nEnter deposit amount: ");
        double amount = sc.nextDouble();
        if(amount < 0){
            System.out.print("\n---Amount must be grater than 0---\n");
            return;
        }

        double balance = Double.parseDouble(userDetails[user][2]);
        balance += amount;
        userDetails[user][2] = String.valueOf(balance);

        System.out.println("\n---Deposit Successful---\n");
    }

    //function for withdraw amount
    static void withdraw(int user){
        System.out.print("\nEnter withdrawal amount : ");
        double amount = sc.nextDouble();

        double balance = Double.parseDouble(userDetails[user][2]);
        if(amount > balance){
            System.out.println("\n---Not enough balance---\n");
        }
        else{
            balance -= amount;
            userDetails[user][2] = String.valueOf(balance);
            System.out.println("\n---Withdraw Successful---\n");
        }
    }

    //function for show balance
    static void showbalance(int user){
        System.out.println("\nYour Balance = "+userDetails[user][2]);
    }

    //function for show user details
    static void details(int user){
        System.out.print("\nName    :"+userDetails[user][0]);
        System.out.print("\nPassword:"+userDetails[user][1]);
        System.out.println("\nBalance :"+userDetails[user][2]);
    }
}