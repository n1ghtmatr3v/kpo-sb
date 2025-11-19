package hse.bank.visitor;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.List;

public class JSONExportVisitor implements ExportVisitor {

    @Override
    public String exportAccounts(List<BankAccount> accounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"accounts\": [");
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            sb.append(String.format(
                    "{\"id\":\"%s\",\"name\":\"%s\",\"balance\":%.2f}",
                    acc.getId(), acc.getName(), acc.getBalance()
            ));
            if (i < accounts.size() - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public String exportCategories(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"categories\": [");
        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            sb.append(String.format(
                    "{\"id\":\"%s\",\"type\":\"%s\",\"name\":\"%s\"}",
                    cat.getId(), cat.getType(), cat.getName()
            ));
            if (i < categories.size() - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public String exportOperations(List<Operation> operations) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"operations\": [");
        for (int i = 0; i < operations.size(); i++) {
            Operation op = operations.get(i);
            sb.append(String.format(
                    "{\"id\":\"%s\",\"type\":\"%s\",\"accountId\":\"%s\",\"amount\":%.2f,\"date\":\"%s\",\"description\":\"%s\",\"categoryId\":\"%s\"}",
                    op.getId(), op.getType(), op.getBankAccountId(), op.getAmount(),
                    op.getDate(), op.getDescription(), op.getCategoryId()
            ));
            if (i < operations.size() - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}