package hse.bank.visitor;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.List;

public interface ExportVisitor {
    String exportAccounts(List<BankAccount> accounts);
    String exportCategories(List<Category> categories);
    String exportOperations(List<Operation> operations);
}