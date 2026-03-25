// ======================
// Main.java
// Do nothing!
// ======================
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your student ID: ");
        String studentId = scanner.nextLine().trim();

        BrowserSimulator browser = new BrowserSimulator(studentId);

        int choice;
        do {
            printMenu();
            System.out.print("Your choice: ");

            // Simple menu input (you may assume valid integer input)
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    browser.openNewTab(scanner);
                    break;
                case 2:
                    browser.goBack();
                    break;
                case 3:
                    browser.goForward();
                    break;
                case 4:
                    browser.closeCurrentTab();
                    break;
                case 5:
                    browser.reopenClosedTab();
                    break;
                case 6:
                    browser.printStatus();
                    break;
                case 7:
                    browser.printSummaryAndExit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== BROWSER SIMULATOR =====");
        System.out.println("1 - Open new tab");
        System.out.println("2 - Go back");
        System.out.println("3 - Go forward");
        System.out.println("4 - Close current tab");
        System.out.println("5 - Reopen last closed tab");
        System.out.println("6 - Show status");
        System.out.println("7 - Print summary and exit");
    }
}
