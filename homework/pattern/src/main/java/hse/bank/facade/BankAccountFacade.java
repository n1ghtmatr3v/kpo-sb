package hse.bank.facade;

import hse.bank.area.BankAccount;
import hse.bank.factory.DomainFactory;
import java.util.*;

public class BankAccountFacade {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private final DomainFactory factory;

    public BankAccountFacade(DomainFactory factory) {
        this.factory = factory;
    }

    public BankAccount createAccount(String name, double initialBalance) {
        BankAccount account = factory.createBankAccount(name, initialBalance);
        accounts.put(account.getId(), account);
        return account;
    }

    public void updateAccount(String id, String newName) {
        BankAccount account = accounts.get(id);
        if (account != null) {
            account.setName(newName);
        }
    }

    public void deleteAccount(String id) {
        accounts.remove(id);
    }

    public BankAccount getAccount(String id) {
        return accounts.get(id);
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void updateBalance(String accountId, double amount, boolean isIncome) {
        BankAccount account = accounts.get(accountId);
        if (account != null) {
            double newBalance = isIncome ?
                    account.getBalance() + amount :
                    account.getBalance() - amount;
            account.setBalance(newBalance);
        }
    }
}