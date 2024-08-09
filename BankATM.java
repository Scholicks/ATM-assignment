import java.util.Scanner;

class BankATM {
    private double accountBalance;

    public BankATM(double initialAmount) {
        this.accountBalance = initialAmount;
    }

    public void displayBalance() {
        System.out.println("Your current balance is: $" + accountBalance);
    }

    public void withdrawFunds(double withdrawalAmount) {
        if (withdrawalAmount > accountBalance) {
            System.out.println("Error: Insufficient funds. Unable to withdraw more than the available balance.");
        } else {
            accountBalance -= withdrawalAmount;
            System.out.println("Success: You have withdrawn $" + withdrawalAmount);
            displayBalance();
        }
    }

    public void transferFunds(double transferAmount, String destinationBank) {
        double fee = 0;
        if (destinationBank.equalsIgnoreCase("external")) {
            fee = 0.001 * transferAmount;
        }

        double totalDeduction = transferAmount + fee;

        if (totalDeduction > accountBalance) {
            System.out.println("Error: Insufficient funds. Unable to transfer more than the available balance.");
        } else {
            accountBalance -= totalDeduction;
            System.out.println("Success: You have transferred $" + transferAmount + " to the " + destinationBank + " bank.");
            if (fee > 0) {
                System.out.println("Transfer fee applied: $" + fee);
            }
            displayBalance();
        }
    }

    public void depositFunds(double depositAmount) {
        accountBalance += depositAmount;
        System.out.println("Success: You have deposited $" + depositAmount);
        displayBalance();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankATM userATM = new BankATM(1000.00);

        while (true) {
            System.out.println("\nATM Services Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Transfer Funds");
            System.out.println("4. Deposit Funds");
            System.out.println("5. Quit");
            System.out.print("Please select an option: ");
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    userATM.displayBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount you wish to withdraw: ");
                    double withdrawal = input.nextDouble();
                    userATM.withdrawFunds(withdrawal);
                    break;
                case 3:
                    System.out.print("Enter the amount you wish to transfer: ");
                    double transfer = input.nextDouble();
                    System.out.print("Transfer to (same/external) bank: ");
                    String bank = input.next();
                    userATM.transferFunds(transfer, bank);
                    break;
                case 4:
                    System.out.print("Enter the amount you wish to deposit: ");
                    double deposit = input.nextDouble();
                    userATM.depositFunds(deposit);
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM services. Have a great day!");
                    input.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}