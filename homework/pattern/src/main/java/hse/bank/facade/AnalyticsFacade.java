package hse.bank.facade;

import hse.bank.area.Category;
import hse.bank.area.Operation;
import hse.bank.area.OperationType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsFacade {
    private final OperationFacade operationFacade;
    private final CategoryFacade categoryFacade;

    public AnalyticsFacade(OperationFacade operationFacade, CategoryFacade categoryFacade) {
        this.operationFacade = operationFacade;
        this.categoryFacade = categoryFacade;
    }

    public double calculateBalanceDifference(LocalDateTime start, LocalDateTime end) {
        List<Operation> operations = operationFacade.getOperationsByPeriod(start, end);

        double totalIncome = operations.stream()
                .filter(op -> op.getType() == OperationType.INCOME)
                .mapToDouble(Operation::getAmount)
                .sum();

        double totalExpense = operations.stream()
                .filter(op -> op.getType() == OperationType.EXPENSE)
                .mapToDouble(Operation::getAmount)
                .sum();

        return totalIncome - totalExpense;
    }

    public Map<String, Double> groupOperationsByCategory(OperationType type,
                                                         LocalDateTime start, LocalDateTime end) {
        List<Operation> operations = operationFacade.getOperationsByPeriod(start, end)
                .stream()
                .filter(op -> op.getType() == type)
                .toList();

        return operations.stream()
                .collect(Collectors.groupingBy(
                        Operation::getCategoryId,
                        Collectors.summingDouble(Operation::getAmount)
                ));
    }

    public void printCategoryAnalysis(OperationType type, LocalDateTime start, LocalDateTime end) {
        Map<String, Double> categorySums = groupOperationsByCategory(type, start, end);

        System.out.println(type + " analysis for period " + start + " to " + end + ":");
        categorySums.forEach((categoryId, sum) -> {
            Category category = categoryFacade.getCategory(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                System.out.printf("  %s: %.2f%n", categoryName, sum);
            } else {
                System.out.printf("  [Unknown Category ID: %s]: %.2f%n", categoryId, sum);
            }
        });

        double total = categorySums.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("Total %s: %.2f%n", type.toString().toLowerCase(), total);
    }
}