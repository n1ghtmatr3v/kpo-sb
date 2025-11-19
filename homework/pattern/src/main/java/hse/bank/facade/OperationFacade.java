package hse.bank.facade;

import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import hse.bank.factory.DomainFactory;
import java.time.LocalDateTime;
import java.util.*;

public class OperationFacade {
    private final Map<String, Operation> operations = new HashMap<>();
    private final DomainFactory factory;
    private final BankAccountFacade accountFacade;

    public OperationFacade(DomainFactory factory, BankAccountFacade accountFacade) {
        this.factory = factory;
        this.accountFacade = accountFacade;
    }

    public Operation createOperation(OperationType type, String bankAccountId,
                                     double amount, String description, String categoryId) {
        Operation operation = factory.createOperation(type, bankAccountId, amount, description, categoryId);
        operations.put(operation.getId(), operation);

        accountFacade.updateBalance(bankAccountId, amount, type == OperationType.INCOME);

        return operation;
    }

    public void updateOperationDescription(String id, String newDescription) {
        Operation operation = operations.get(id);
        if (operation != null) {
            operation.setDescription(newDescription);
        }
    }

    public void deleteOperation(String id) {
        operations.remove(id);
    }

    public Operation getOperation(String id) {
        return operations.get(id);
    }

    public List<Operation> getAllOperations() {
        return new ArrayList<>(operations.values());
    }

    public List<Operation> getOperationsByAccount(String accountId) {
        return operations.values().stream()
                .filter(op -> op.getBankAccountId().equals(accountId))
                .toList();
    }

    public List<Operation> getOperationsByCategory(String categoryId) {
        return operations.values().stream()
                .filter(op -> op.getCategoryId().equals(categoryId))
                .toList();
    }

    public List<Operation> getOperationsByPeriod(LocalDateTime start, LocalDateTime end) {
        return operations.values().stream()
                .filter(op -> !op.getDate().isBefore(start) && !op.getDate().isAfter(end))
                .toList();
    }
}