package hse.bank.factory;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import java.time.LocalDateTime;

public interface DomainFactory {
    BankAccount createBankAccount(String name, double initialBalance);
    Category createCategory(OperationType type, String name);
    Operation createOperation(OperationType type, String bankAccountId,
                              double amount, String description, String categoryId);
}