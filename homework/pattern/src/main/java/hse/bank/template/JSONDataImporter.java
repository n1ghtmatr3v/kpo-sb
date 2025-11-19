package hse.bank.template;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import hse.bank.facade.BankAccountFacade;
import hse.bank.facade.CategoryFacade;
import hse.bank.facade.OperationFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JSONDataImporter extends DataImporter {

    public JSONDataImporter(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                            OperationFacade operationFacade) {
        super(accountFacade, categoryFacade, operationFacade);
    }

    @Override
    protected List<BankAccount> parseAccounts(String fileContent) {
        System.out.println("Parsing accounts from JSON...");
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("imported1", "Импортированный счет 1", 10000));
        accounts.add(new BankAccount("imported2", "Импортированный счет 2", 5000));
        return accounts;
    }

    @Override
    protected List<Category> parseCategories(String fileContent) {
        System.out.println("Parsing categories from JSON...");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("imported1", OperationType.INCOME, "Импорт доход"));
        categories.add(new Category("imported2", OperationType.EXPENSE, "Импорт расход"));
        return categories;
    }

    @Override
    protected List<Operation> parseOperations(String fileContent) {
        System.out.println("Parsing operations from JSON...");
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation("imported1", OperationType.INCOME, "imported1",
                1000, LocalDateTime.now(), "Импорт доход", "imported1"));
        operations.add(new Operation("imported2", OperationType.EXPENSE, "imported1",
                500, LocalDateTime.now(), "Импорт расход", "imported2"));
        return operations;
    }
}