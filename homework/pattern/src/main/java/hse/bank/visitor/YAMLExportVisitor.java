package hse.bank.visitor;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.List;

public class YAMLExportVisitor implements ExportVisitor {

    @Override
    public String exportAccounts(List<BankAccount> accounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("accounts:\n");
        for (BankAccount acc : accounts) {
            sb.append(String.format(
                    "  - id: \"%s\"\n    name: \"%s\"\n    balance: %.2f\n",
                    acc.getId(), acc.getName(), acc.getBalance()
            ));
        }
        return sb.toString();
    }

    @Override
    public String exportCategories(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        sb.append("categories:\n");
        for (Category cat : categories) {
            sb.append(String.format(
                    "  - id: \"%s\"\n    type: \"%s\"\n    name: \"%s\"\n",
                    cat.getId(), cat.getType(), cat.getName()
            ));
        }
        return sb.toString();
    }

    @Override
    public String exportOperations(List<Operation> operations) {
        StringBuilder sb = new StringBuilder();
        sb.append("operations:\n");
        for (Operation op : operations) {
            sb.append(String.format(
                    "  - id: \"%s\"\n    type: \"%s\"\n    accountId: \"%s\"\n    amount: %.2f\n    date: \"%s\"\n    description: \"%s\"\n    categoryId: \"%s\"\n",
                    op.getId(), op.getType(), op.getBankAccountId(), op.getAmount(),
                    op.getDate(), op.getDescription(), op.getCategoryId()
            ));
        }
        return sb.toString();
    }
}