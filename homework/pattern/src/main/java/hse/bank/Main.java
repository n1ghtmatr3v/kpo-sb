package hse.bank;

import hse.bank.command.*;
import hse.bank.area.OperationType;
import hse.bank.facade.*;
import hse.bank.factory.SimpleDomainFactory;
import hse.bank.proxy.BankRepositoryProxy;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HSE Bank Financial Management System ===\n");

        SimpleDomainFactory factory = new SimpleDomainFactory();
        BankRepositoryProxy repository = new BankRepositoryProxy();

        BankAccountFacade accountFacade = new BankAccountFacade(factory);
        CategoryFacade categoryFacade = new CategoryFacade(factory);
        OperationFacade operationFacade = new OperationFacade(factory, accountFacade);
        AnalyticsFacade analyticsFacade = new AnalyticsFacade(operationFacade, categoryFacade);
        ExportFacade exportFacade = new ExportFacade(accountFacade, categoryFacade, operationFacade);
        ImportFacade importFacade = new ImportFacade(accountFacade, categoryFacade, operationFacade);
        DataManagementFacade dataManagementFacade = new DataManagementFacade(accountFacade, operationFacade);

        Command createMainAccount = new CreateAccountCommand(accountFacade, "Основной счет", 10000);
        Command createSavingsAccount = new CreateAccountCommand(accountFacade, "Накопительный счет", 5000);

        Command createSalaryCategory = new CreateCategoryCommand(categoryFacade, OperationType.INCOME, "Зарплата");
        Command createCafeCategory = new CreateCategoryCommand(categoryFacade, OperationType.EXPENSE, "Кафе");
        Command createHealthCategory = new CreateCategoryCommand(categoryFacade, OperationType.EXPENSE, "Здоровье");

        Command timedCreateMainAccount = new TimedCommandDecorator(createMainAccount);
        Command timedCreateSalaryCategory = new TimedCommandDecorator(createSalaryCategory);

        System.out.println("=== Creating Accounts and Categories ===");
        timedCreateMainAccount.execute();
        createSavingsAccount.execute();
        timedCreateSalaryCategory.execute();
        createCafeCategory.execute();
        createHealthCategory.execute();

        var accounts = accountFacade.getAllAccounts();
        var categories = categoryFacade.getAllCategories();

        String mainAccountId = accounts.get(0).getId();
        String salaryCategoryId = categories.stream()
                .filter(c -> c.getName().equals("Зарплата"))
                .findFirst().get().getId();
        String cafeCategoryId = categories.stream()
                .filter(c -> c.getName().equals("Кафе"))
                .findFirst().get().getId();
        String healthCategoryId = categories.stream()
                .filter(c -> c.getName().equals("Здоровье"))
                .findFirst().get().getId();

        System.out.println("\n=== Creating Operations ===");
        Command[] operations = {
                new CreateOperationCommand(operationFacade, OperationType.INCOME,
                        mainAccountId, 50000, "Зарплата за октябрь", salaryCategoryId),
                new CreateOperationCommand(operationFacade, OperationType.EXPENSE,
                        mainAccountId, 1500, "Обед в кафе", cafeCategoryId),
                new CreateOperationCommand(operationFacade, OperationType.EXPENSE,
                        mainAccountId, 3000, "Визит к врачу", healthCategoryId),
                new CreateOperationCommand(operationFacade, OperationType.EXPENSE,
                        mainAccountId, 800, "Кофе с коллегами", cafeCategoryId)
        };

        for (Command op : operations) {
            Command timedOp = new TimedCommandDecorator(op);
            timedOp.execute();
        }

        System.out.println("\n=== Data Integrity Check ===");
        dataManagementFacade.validateDataConsistency();

        System.out.println("\n=== Balance Recalculation Demo ===");
        dataManagementFacade.manualRecalculation(mainAccountId);

        System.out.println("\n=== Automatic Balance Recalculation ===");
        dataManagementFacade.recalculateAllBalances();

        System.out.println("\n=== Importing Data ===");
        importFacade.importFromJSON("data.json");
        importFacade.importFromCSV("data.csv");
        importFacade.importFromYAML("data.yaml");

        System.out.println("\n=== Financial Analytics ===");
        LocalDateTime start = LocalDateTime.now().minusDays(30);
        LocalDateTime end = LocalDateTime.now();

        Command analyticsCommand = new ShowAnalyticsCommand(analyticsFacade, start, end);
        Command timedAnalytics = new TimedCommandDecorator(analyticsCommand);
        timedAnalytics.execute();

        System.out.println("\n=== Data Export ===");
        exportFacade.exportAllData();

        System.out.println("\n=== Repository Demo ===");
        accounts.forEach(repository::saveAccount);
        categories.forEach(repository::saveCategory);
        operationFacade.getAllOperations().forEach(repository::saveOperation);

        var foundAccount = repository.findAccountById(mainAccountId);
        System.out.println("Found account: " + foundAccount);

        System.out.println("\n=== Final State ===");
        System.out.println("Total accounts: " + accountFacade.getAllAccounts().size());
        System.out.println("Total categories: " + categoryFacade.getAllCategories().size());
        System.out.println("Total operations: " + operationFacade.getAllOperations().size());

        accountFacade.getAllAccounts().forEach(acc ->
                System.out.printf("Account %s: %.2f%n", acc.getName(), acc.getBalance()));
    }
}