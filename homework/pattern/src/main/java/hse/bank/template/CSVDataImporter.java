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

public class CSVDataImporter extends DataImporter {

    public CSVDataImporter(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                           OperationFacade operationFacade) {
        super(accountFacade, categoryFacade, operationFacade);
    }

    @Override
    protected List<BankAccount> parseAccounts(String fileContent) {
        System.out.println("Parsing accounts from CSV...");
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("csv1", "CSV счет 1", 8000));
        return accounts;
    }

    @Override
    protected List<Category> parseCategories(String fileContent) {
        System.out.println("Parsing categories from CSV...");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("csv1", OperationType.INCOME, "CSV доход"));
        return categories;
    }

    @Override
    protected List<Operation> parseOperations(String fileContent) {
        System.out.println("Parsing operations from CSV...");
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation("csv1", OperationType.INCOME, "csv1",
                2000, LocalDateTime.now(), "CSV операция", "csv1"));
        return operations;
    }
}