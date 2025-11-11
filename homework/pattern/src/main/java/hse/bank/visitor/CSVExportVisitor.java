package hse.bank.visitor;

import hse.bank.area.BankAccount;
import hse.bank.area.Category;
import hse.bank.area.Operation;
import java.util.List;

public class CSVExportVisitor implements ExportVisitor {

    @Override
    public String exportAccounts(List<BankAccount> accounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,name,balance\n");
        for (BankAccount acc : accounts) {
            sb.append(String.format(
                    "%s,%s,%.2f\n",
                    acc.getId(), acc.getName(), acc.getBalance()
            ));
        }
        return sb.toString();
    }

    @Override
    public String exportCategories(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name\n");
        for (Category cat : categories) {
            sb.append(String.format(
                    "%s,%s,%s\n",
                    cat.getId(), cat.getType(), cat.getName()
            ));
        }
        return sb.toString();
    }

    @Override
    public String exportOperations(List<Operation> operations) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,accountId,amount,date,description,categoryId\n");
        for (Operation op : operations) {
            sb.append(String.format(
                    "%s,%s,%s,%.2f,%s,%s,%s\n",
                    op.getId(), op.getType(), op.getBankAccountId(), op.getAmount(),
                    op.getDate(), op.getDescription(), op.getCategoryId()
            ));
        }
        return sb.toString();
    }
}