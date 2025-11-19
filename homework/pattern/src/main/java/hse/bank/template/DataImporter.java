package hse.bank.template;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import hse.bank.facade.BankAccountFacade;
import hse.bank.facade.CategoryFacade;
import hse.bank.facade.OperationFacade;
import java.time.LocalDateTime;
import java.util.List;

public abstract class DataImporter {
    protected final BankAccountFacade accountFacade;
    protected final CategoryFacade categoryFacade;
    protected final OperationFacade operationFacade;

    public DataImporter(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                        OperationFacade operationFacade) {
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
    }

    public final void importData(String filePath) {
        System.out.println("Starting import from: " + filePath);

        String fileContent = readFile(filePath);
        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("File is empty or cannot be read");
            return;
        }

        List<BankAccount> accounts = parseAccounts(fileContent);
        List<Category> categories = parseCategories(fileContent);
        List<Operation> operations = parseOperations(fileContent);

        saveImportedData(accounts, categories, operations);

        System.out.println("Import completed: " + accounts.size() + " accounts, " +
                categories.size() + " categories, " +
                operations.size() + " operations");
    }

    private String readFile(String filePath) {
        System.out.println("Reading file: " + filePath);
        return simulateFileReading(filePath);
    }

    private void saveImportedData(List<BankAccount> accounts, List<Category> categories,
                                  List<Operation> operations) {
        System.out.println("Saving imported data...");

        for (BankAccount account : accounts) {
            accountFacade.createAccount(account.getName(), account.getBalance());
        }

        for (Category category : categories) {
            categoryFacade.createCategory(category.getType(), category.getName());
        }

        for (Operation operation : operations) {
            operationFacade.createOperation(operation.getType(), operation.getBankAccountId(),
                    operation.getAmount(), operation.getDescription(),
                    operation.getCategoryId());
        }
    }

    protected abstract List<BankAccount> parseAccounts(String fileContent);
    protected abstract List<Category> parseCategories(String fileContent);
    protected abstract List<Operation> parseOperations(String fileContent);

    private String simulateFileReading(String filePath) {
        return "simulated_file_content_for_" + filePath;
    }
}