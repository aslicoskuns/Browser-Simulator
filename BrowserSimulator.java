// ======================
// BrowserSimulator.java
// ======================
import java.util.Scanner;
public class BrowserSimulator {

    private MyStack<Tab> backStack;
    private MyStack<Tab> forwardStack;
    private MyStack<Tab> closedStack;

    private Tab currentTab;
    private String studentId;

    private int totalOpenedTabs;
    private int totalClosedTabs;

    private int mainCapacity;
    private int historyCapacity;
    private int closedCapacity;

    // Constructor
    public BrowserSimulator(String studentId) {
        this.studentId = studentId;
        this.totalOpenedTabs = 0;
        this.totalClosedTabs = 0;

        int sumDigits = computeDigitSum(this.studentId);
        this.mainCapacity =  5 + (sumDigits % 5);
        this.historyCapacity = 5 + (sumDigits % 5);
        this.closedCapacity  = 3 + (sumDigits % 5);

        this.backStack = new MyStack<Tab>(historyCapacity);
        this.forwardStack = new MyStack<Tab>(historyCapacity);
        this.closedStack = new MyStack<Tab>(closedCapacity);

        for(int i = 0; i < this.studentId.length(); i++){
            int d = Character.getNumericValue(studentId.charAt(i));
            String prefix = this.studentId.substring(0, i + 1);

            String category = determineCategory(d);
            String title = "Tab-" + i + " [" + category + "] (prefix " + prefix + ")";
            String url = "https://site" + d + ".com/" + prefix;
            Tab newTab = new Tab(d, title, url, category);

            if (i< this.studentId.length()-1){
                this.backStack.push(newTab);
            }else{
                this.currentTab = newTab;
            }
            this.totalOpenedTabs++;
        }

    }


    // Open a new tab
    public void openNewTab(Scanner scanner) {

        if (backStack.size() + 1 >= mainCapacity) {
            System.out.println("Cannot open new tab. Main capacity reached (" + mainCapacity + ")!");
            return;
        }

        System.out.println("Enter tab title: ");
        String title = scanner.next();
        System.out.println("Enter category: ");
        String category = scanner.next();

        String url = "https://www." + title + ".com/" ;

        if(currentTab != null){
            backStack.push(currentTab);
        }

        Tab newTab = new Tab(totalOpenedTabs,title,url ,category);
        currentTab = newTab;

        while(!forwardStack.isEmpty()){
            forwardStack.pop();
        }

        totalOpenedTabs++;

        System.out.println("New tab opened: " + title);
    }

    // Go back to previous page
    public void goBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentTab);
            currentTab = backStack.pop();
        }else{
            System.out.println("No page to go back to.");
        }
    }

    // Go forward to next page
    public void goForward() {
        if(!forwardStack.isEmpty()){
            backStack.push(currentTab);
            currentTab = forwardStack.pop();
        }else{
            System.out.println("No page to go forward to.");
        }
    }

    // Close the current tab
    public void closeCurrentTab() {
        if(currentTab != null){
            closedStack.push(currentTab);
            totalClosedTabs++;
            if(!backStack.isEmpty()){
                currentTab = backStack.pop();
            }else{
                currentTab = null;
            }
        }
    }

    // Reopen last closed tab
    public void reopenClosedTab() {
        if(!closedStack.isEmpty()){
            if(currentTab != null){
                backStack.push(currentTab);
            }
            currentTab = closedStack.pop();
            System.out.println("Reopened tab: " + currentTab);
        }else{
            System.out.println("No closed tab to reopen.");
        }
    }

    // Show current status and stacks
    public void printStatus() {
        System.out.println("----------BROWSER STATUS----------");
        System.out.println("Current Tab: " + (currentTab != null ? currentTab : "null"));

        System.out.println("Back Stack : ");
        backStack.printStack();

        System.out.print("Forward Stack: ");
        forwardStack.printStack();

        System.out.print("Closed Stack: ");
        closedStack.printStack();

        System.out.println("--------------------------");
    }

    // Print summary info and exit
    public void printSummaryAndExit() {
        System.out.println("Total opened tabs: " + totalOpenedTabs);
        System.out.println("Total closed tabs: " + totalClosedTabs);

        int currentCount = (currentTab == null ? 0 : 1);
        int accessible = backStack.size() + forwardStack.size() + currentCount;
        System.out.println("Accessible tabs: " + accessible);

        System.out.println("Back Stack Size: " + backStack.size());
        System.out.println("Forward Stack Size: " + forwardStack.size());
        System.out.println("Closed Stack Size: " + closedStack.size());

        System.out.println(studentId + " - OPEN=" + totalOpenedTabs +
                " - CLOSED=" + totalClosedTabs +
                " - BACK=" + backStack.size() +
                " - FORWARD=" + forwardStack.size());
    }

    // Compute sum of digits in ID
    private int computeDigitSum(String id) {
        int sum = 0;

        for (char c : id.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }

    // Map digit to tab category
    private String determineCategory(int digit) {
        if(digit % 4 == 0){
            return "News";
        }else if(digit % 4 == 1){
            return "Education";
        } else if (digit % 4 == 2) {
            return "Sports";
        } else if (digit % 4 == 3) {
            return "Entertainment";
        }
        return null;
    }
}

