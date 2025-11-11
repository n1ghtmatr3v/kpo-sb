package hse.bank.facade;

import hse.bank.visitor.CSVExportVisitor;
import hse.bank.visitor.ExportVisitor;
import hse.bank.visitor.JSONExportVisitor;
import hse.bank.visitor.YAMLExportVisitor;

public class ExportFacade {
    private final BankAccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;

    public ExportFacade(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                        OperationFacade operationFacade) {
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
    }

    public void exportToJSON() {
        System.out.println("=== Exporting to JSON ===");
        ExportVisitor visitor = new JSONExportVisitor();
        exportWithVisitor(visitor);
    }

    public void exportToCSV() {
        System.out.println("=== Exporting to CSV ===");
        ExportVisitor visitor = new CSVExportVisitor();
        exportWithVisitor(visitor);
    }

    public void exportToYAML() {
        System.out.println("=== Exporting to YAML ===");
        ExportVisitor visitor = new YAMLExportVisitor();
        exportWithVisitor(visitor);
    }

    public void exportAllData() {
        exportToJSON();
        System.out.println();
        exportToCSV();
        System.out.println();
        exportToYAML();
    }

    private void exportWithVisitor(ExportVisitor visitor) {
        String accounts = visitor.exportAccounts(accountFacade.getAllAccounts());
        String categories = visitor.exportCategories(categoryFacade.getAllCategories());
        String operations = visitor.exportOperations(operationFacade.getAllOperations());

        System.out.println("Accounts:\n" + accounts);
        System.out.println("Categories:\n" + categories);
        System.out.println("Operations:\n" + operations);
    }
}