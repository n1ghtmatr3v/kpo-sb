package hse.bank.area;

import java.time.LocalDateTime;

public class Operation {
    private final String id;
    private final OperationType type;
    private final String bankAccountId;
    private final double amount;
    private final LocalDateTime date;
    private String description;
    private final String categoryId;

    public Operation(String id, OperationType type, String bankAccountId,
                     double amount, LocalDateTime date, String description, String categoryId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }
    public OperationType getType() {
        return type;
    }
    public String getBankAccountId() {
        return bankAccountId;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("Operation{id='%s', type=%s, amount=%.2f, date=%s, description='%s'}",
                id, type, amount, date, description);
    }
}