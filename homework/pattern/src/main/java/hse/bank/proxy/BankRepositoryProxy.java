package hse.bank.proxy;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.*;

public class BankRepositoryProxy implements BankRepository {
    private final Map<String, BankAccount> accountCache = new HashMap<>();
    private final Map<String, Category> categoryCache = new HashMap<>();
    private final Map<String, Operation> operationCache = new HashMap<>();

    @Override
    public void saveAccount(BankAccount account) {
        accountCache.put(account.getId(), account);
        System.out.println("Account saved to cache: " + account.getId());
    }

    @Override
    public void saveCategory(Category category) {
        categoryCache.put(category.getId(), category);
        System.out.println("Category saved to cache: " + category.getId());
    }

    @Override
    public void saveOperation(Operation operation) {
        operationCache.put(operation.getId(), operation);
        System.out.println("Operation saved to cache: " + operation.getId());
    }

    @Override
    public BankAccount findAccountById(String id) {
        BankAccount account = accountCache.get(id);
        if (account != null) {
            System.out.println("Account found in cache: " + id);
        }
        return account;
    }

    @Override
    public Category findCategoryById(String id) {
        Category category = categoryCache.get(id);
        if (category != null) {
            System.out.println("Category found in cache: " + id);
        }
        return category;
    }

    @Override
    public Operation findOperationById(String id) {
        Operation operation = operationCache.get(id);
        if (operation != null) {
            System.out.println("Operation found in cache: " + id);
        }
        return operation;
    }

    @Override
    public List<BankAccount> findAllAccounts() {
        System.out.println("Returning all accounts from cache");
        return new ArrayList<>(accountCache.values());
    }

    @Override
    public List<Category> findAllCategories() {
        System.out.println("Returning all categories from cache");
        return new ArrayList<>(categoryCache.values());
    }

    @Override
    public List<Operation> findAllOperations() {
        System.out.println("Returning all operations from cache");
        return new ArrayList<>(operationCache.values());
    }
}