import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    private String username;
    private String password;
    private String email;
    private double balance;

    // Constructor
    public User(String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public double getBalance() { return balance; }

    // Deposit
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("\n---Amount must be greater than 0---");
            return false;
        }
        balance += amount;
        System.out.println("\n---Deposit Successful---");
        return true;
    }

    // Withdraw
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("\n---Not enough balance---");
            return false;
        }
        balance -= amount;
        System.out.println("\n---Withdraw Successful---");
        return true;
    }

    // Show details
    public void showDetails() {
        System.out.println("\n---Account Details---");
        System.out.println("Name     : " + username);
        System.out.println("Password : " + password);
        System.out.println("Email    : " + email);
        System.out.println("Balance  : " + balance);
    }
}

public class BMS_Week4 {
    static Scanner sc = new Scanner(System.in);
    static final int MAX_USERS = 100;
    static User[] users = new User[MAX_USERS];
    static int userCount = 0;

    public static void main(String[] args) {
        System.out.println("\n||================================||");
        System.out.println("--||-- BANK MANAGEMENT SYSTEM --||--");
        System.out.println("||================================||");

        int choice;
        while (true) {
            System.out.println("\n1 = Register \n2 = Login \n3 = Exit");
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> {
                    System.out.println("\n---Exiting... Thank you!---");
                    return;
                }
                default -> System.out.println("\n---Invalid Input!! Enter valid choice---");
            }
        }
    }

    // ---------------- Registration ----------------
    static void registerUser() {
        if (userCount >= MAX_USERS) {
            System.out.println("\n---No space available for more users---");
            return;
        }

        System.out.print("Enter User Name : ");
        String username = sc.nextLine().trim();

        // Check duplicate
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                System.out.println("\n---Username already exists! Try another.---");
                return;
            }
        }

        // Email validation
        String email_regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
        String email;
        while (true) {
            System.out.print("Enter Email : ");
            email = sc.nextLine().trim();
            if (!Pattern.matches(email_regex, email)) {
                System.out.println("\n---Invalid email format. Try again---");
            } else break;
        }

        System.out.print("Enter Starting Amount : ");
        double balance = sc.nextDouble();
        sc.nextLine();

        // Password validation
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?]).{6,}$";
        String password;
        while (true) {
            System.out.print("Enter Password : ");
            password = sc.nextLine().trim();
            if (!Pattern.matches(password_regex, password)) {
                System.out.println("\n---Password must have upper, lower, digit, special char, min 6 chars---");
            } else break;
        }

        // Create User object
        users[userCount++] = new User(username, password, email, balance);
        System.out.println("\n---Registration successful!!---");
    }

    // ---------------- Login ----------------
    static void loginUser() {
        System.out.print("Enter User Name : ");
        String username = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                System.out.println("\n---Login successful!---");
                System.out.println("Welcome " + users[i].getUsername());
                userMenu(users[i]);
                return;
            }
        }
        System.out.println("\n=> Invalid Username or Password! Try again.");
    }

    // ---------------- User Menu ----------------
    static void userMenu(User user) {
        int choice;
        double amount;
        do {
            System.out.println("\n---Account Menu---");
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
                    System.out.print("Enter deposit amount: ");
                    amount = sc.nextDouble();
                    sc.nextLine();
                    user.deposit(amount);
                }
                case 5 -> {
                    System.out.print("Enter withdrawal amount: ");
                    amount = sc.nextDouble();
                    sc.nextLine();
                    user.withdraw(amount);
                }
                case 6 -> System.out.println("\nYour Balance = " + user.getBalance());
                case 7 -> user.showDetails();
                case 8 -> System.out.println("\n---Log Out---");
                default -> System.out.println("\n---Invalid Input---");
            }
        } while (choice != 8);
    }
}
