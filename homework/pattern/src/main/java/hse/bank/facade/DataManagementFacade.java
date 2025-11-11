package hse.bank.facade;

import hse.bank.area.BankAccount;
import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import java.util.List;

public class DataManagementFacade {
    private final BankAccountFacade accountFacade;
    private final OperationFacade operationFacade;

    public DataManagementFacade(BankAccountFacade accountFacade, OperationFacade operationFacade) {
        this.accountFacade = accountFacade;
        this.operationFacade = operationFacade;
    }

    public void recalculateAllBalances() {
        System.out.println("=== Starting automatic balance recalculation ===");

        List<BankAccount> accounts = accountFacade.getAllAccounts();

        for (BankAccount account : accounts) {
            double calculatedBalance = calculateBalanceForAccount(account.getId());
            double currentBalance = account.getBalance();

            if (Math.abs(calculatedBalance - currentBalance) > 0.01) {
                System.out.printf("Balance mismatch for account %s: current=%.2f, calculated=%.2f%n",
                        account.getName(), currentBalance, calculatedBalance);
                account.setBalance(calculatedBalance);
                System.out.printf("Balance corrected to: %.2f%n", calculatedBalance);
            } else {
                System.out.printf("Account %s: balance OK (%.2f)%n", account.getName(), currentBalance);
            }
        }

        System.out.println("=== Balance recalculation completed ===");
    }

    public void manualRecalculation(String accountId) {
        BankAccount account = accountFacade.getAccount(accountId);
        if (account == null) {
            System.out.println("Account not found: " + accountId);
            return;
        }

        double calculatedBalance = calculateBalanceForAccount(accountId);
        System.out.printf("Manual recalculation for account %s:%n", account.getName());
        System.out.printf("Current balance: %.2f%n", account.getBalance());
        System.out.printf("Calculated balance: %.2f%n", calculatedBalance);

        if (Math.abs(calculatedBalance - account.getBalance()) > 0.01) {
            System.out.println("Discrepancy detected! Correcting balance...");
            account.setBalance(calculatedBalance);
        } else {
            System.out.println("No discrepancy found.");
        }
    }

    private double calculateBalanceForAccount(String accountId) {
        List<Operation> accountOperations = operationFacade.getOperationsByAccount(accountId);
        double balance = 0.0;

        for (Operation operation : accountOperations) {
            if (operation.getType() == OperationType.INCOME) {
                balance += operation.getAmount();
            } else {
                balance -= operation.getAmount();
            }
        }

        return balance;
    }

    public void validateDataConsistency() {
        System.out.println("=== Data Consistency Validation ===");
        boolean consistent = true;

        List<BankAccount> accounts = accountFacade.getAllAccounts();
        for (BankAccount account : accounts) {
            double calculated = calculateBalanceForAccount(account.getId());
            if (Math.abs(calculated - account.getBalance()) > 0.01) {
                System.out.printf("INCONSISTENCY: Account %s - current: %.2f, calculated: %.2f%n",
                        account.getName(), account.getBalance(), calculated);
                consistent = false;
            }
        }

        if (consistent) {
            System.out.println("All accounts are consistent ✓");
        } else {
            System.out.println("Data inconsistencies found!");
        }
    }
}