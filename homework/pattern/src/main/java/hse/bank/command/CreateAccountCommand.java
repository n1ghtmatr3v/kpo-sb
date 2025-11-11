package hse.bank.command;

import hse.bank.facade.BankAccountFacade;

public class CreateAccountCommand implements Command {
    private final BankAccountFacade accountFacade;
    private final String name;
    private final double initialBalance;

    public CreateAccountCommand(BankAccountFacade accountFacade, String name, double initialBalance) {
        this.accountFacade = accountFacade;
        this.name = name;
        this.initialBalance = initialBalance;
    }

    @Override
    public void execute() {
        var account = accountFacade.createAccount(name, initialBalance);
        System.out.println("Created account: " + account);
    }

    @Override
    public String getName() {
        return "Create Account: " + name;
    }
}