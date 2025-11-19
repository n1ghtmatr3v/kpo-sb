package hse.bank.proxy;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.List;

public interface BankRepository {
    void saveAccount(BankAccount account);
    void saveCategory(Category category);
    void saveOperation(Operation operation);

    BankAccount findAccountById(String id);
    Category findCategoryById(String id);
    Operation findOperationById(String id);

    List<BankAccount> findAllAccounts();
    List<Category> findAllCategories();
    List<Operation> findAllOperations();
}