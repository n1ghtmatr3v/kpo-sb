package hse.bank.factory;

import hse.bank.area.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class SimpleDomainFactory implements DomainFactory {

    @Override
    public BankAccount createBankAccount(String name, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        return new BankAccount(UUID.randomUUID().toString(), name, initialBalance);
    }

    @Override
    public Category createCategory(OperationType type, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        return new Category(UUID.randomUUID().toString(), type, name);
    }

    @Override
    public Operation createOperation(OperationType type, String bankAccountId, double amount, String description, String categoryId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Operation amount must be positive");
        }
        return new Operation(
                UUID.randomUUID().toString(),
                type,
                bankAccountId,
                amount,
                LocalDateTime.now(),
                description,
                categoryId
        );
    }
}