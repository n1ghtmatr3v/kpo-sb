package hse.bank.command;

import hse.bank.area.OperationType;
import hse.bank.facade.OperationFacade;

public class CreateOperationCommand implements Command {
    private final OperationFacade operationFacade;
    private final OperationType type;
    private final String accountId;
    private final double amount;
    private final String description;
    private final String categoryId;

    public CreateOperationCommand(OperationFacade operationFacade, OperationType type,
                                  String accountId, double amount, String description, String categoryId) {
        this.operationFacade = operationFacade;
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void execute() {
        var operation = operationFacade.createOperation(type, accountId, amount, description, categoryId);
        System.out.println("Created operation: " + operation);
    }

    @Override
    public String getName() {
        return "Create " + type + " Operation: " + amount;
    }
}