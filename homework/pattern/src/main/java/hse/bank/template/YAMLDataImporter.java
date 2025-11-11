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

public class YAMLDataImporter extends DataImporter {

    public YAMLDataImporter(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                            OperationFacade operationFacade) {
        super(accountFacade, categoryFacade, operationFacade);
    }

    @Override
    protected List<BankAccount> parseAccounts(String fileContent) {
        System.out.println("Parsing accounts from YAML...");
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("yaml1", "YAML счет", 12000));
        return accounts;
    }

    @Override
    protected List<Category> parseCategories(String fileContent) {
        System.out.println("Parsing categories from YAML...");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("yaml1", OperationType.INCOME, "YAML доход"));
        categories.add(new Category("yaml2", OperationType.EXPENSE, "YAML расход"));
        return categories;
    }

    @Override
    protected List<Operation> parseOperations(String fileContent) {
        System.out.println("Parsing operations from YAML...");
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation("yaml1", OperationType.INCOME, "yaml1",
                1500, LocalDateTime.now(), "YAML операция", "yaml1"));
        return operations;
    }
}