package hse.bank.facade;

import hse.bank.template.CSVDataImporter;
import hse.bank.template.DataImporter;
import hse.bank.template.JSONDataImporter;
import hse.bank.template.YAMLDataImporter;

public class ImportFacade {
    private final BankAccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;

    public ImportFacade(BankAccountFacade accountFacade, CategoryFacade categoryFacade,
                        OperationFacade operationFacade) {
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
        this.operationFacade = operationFacade;
    }

    public void importFromJSON(String filePath) {
        System.out.println("=== Importing from JSON ===");
        DataImporter importer = new JSONDataImporter(accountFacade, categoryFacade, operationFacade);
        importer.importData(filePath);
    }

    public void importFromCSV(String filePath) {
        System.out.println("=== Importing from CSV ===");
        DataImporter importer = new CSVDataImporter(accountFacade, categoryFacade, operationFacade);
        importer.importData(filePath);
    }

    public void importFromYAML(String filePath) {
        System.out.println("=== Importing from YAML ===");
        DataImporter importer = new YAMLDataImporter(accountFacade, categoryFacade, operationFacade);
        importer.importData(filePath);
    }

    public void importAllFormats() {
        importFromJSON("data.json");
        importFromCSV("data.csv");
        importFromYAML("data.yaml");
    }
}