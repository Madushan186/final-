import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.Year;

public class BloodPressureTracker {

    private static final int MAX_USERS = 5; // Maximum number of users allowed
    private final Map<String, UserData> userDatabase;

    public BloodPressureTracker() {
        this.userDatabase = new HashMap<>();
    }

    public static void main(String[] args) {
        BloodPressureTracker tracker = new BloodPressureTracker();
        tracker.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "1":
                    createRecord(scanner);
                    break;
                case "2":
                    showAllUserData();
                    break;
                case "3":
                    showUserData(scanner);
                    break;
                case "4":
                    deleteAllRecords();
                    break;
                case "5":
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create a record");
        System.out.println("2. Show blood pressure data for all users");
        System.out.println("3. Show blood pressure data for a selected user");
        System.out.println("4. Delete all records");
        System.out.println("5. Exit application");
    }

    private void createRecord(Scanner scanner) {
        if (userDatabase.size() >= MAX_USERS) {
            System.out.println("Cannot add more users. Maximum limit reached.");
            return;
        }

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter year of birth: ");
        int yearOfBirth = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter systolic blood pressure: ");
        double systolicBP = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter diastolic blood pressure: ");
        double diastolicBP = Double.parseDouble(scanner.nextLine());

        UserData userData = new UserData(name, yearOfBirth, systolicBP, diastolicBP);
        userDatabase.put(userId, userData);

        System.out.println("Record created successfully.");
    }

    private void showAllUserData() {
        System.out.println("\nBlood Pressure Data for All Users:");
        for (UserData userData : userDatabase.values()) {
            userData.display();
        }
    }

    private void showUserData(Scanner scanner) {
        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        if (userDatabase.containsKey(userId)) {
            userDatabase.get(userId).display();
        } else {
            System.out.println("User ID not found.");
        }
    }

    private void deleteAllRecords() {
        userDatabase.clear();
        System.out.println("All records deleted successfully.");
    }

    private void exit() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }

    private static class UserData {
        private final String name;
        private final int yearOfBirth;
        private final double systolicBP;
        private final double diastolicBP;

        public UserData(String name, int yearOfBirth, double systolicBP, double diastolicBP) {
            this.name = name;
            this.yearOfBirth = yearOfBirth;
            this.systolicBP = systolicBP;
            this.diastolicBP = diastolicBP;
        }

        public int calculateAge() {
            int currentYear = Year.now().getValue();
            return currentYear - yearOfBirth;
        }

        public void display() {
            int age = calculateAge();
            Result result = new Result(systolicBP, diastolicBP, age);
            String bloodPressureCategory = result.calculateBloodPressureCategory();
            System.out.println(
                    "Name: " + name + ", Age: " + age + ", Systolic BP: " + systolicBP + ", Diastolic BP: " + diastolicBP + ", Category: " + bloodPressureCategory);
        }
    }

    private static class Result {

        public Result(double systolicBP, double diastolicBP, int age) {
        }

        public String calculateBloodPressureCategory() {
            // Insert blood pressure categories logic here
            return "Normal"; // Placeholder
        }
    }
}
