package hse.bank.command;

import hse.bank.facade.AnalyticsFacade;
import java.time.LocalDateTime;

public class ShowAnalyticsCommand implements Command {
    private final AnalyticsFacade analyticsFacade;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public ShowAnalyticsCommand(AnalyticsFacade analyticsFacade, LocalDateTime start, LocalDateTime end) {
        this.analyticsFacade = analyticsFacade;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        System.out.println("=== Financial Analytics ===");

        double balanceDiff = analyticsFacade.calculateBalanceDifference(start, end);
        System.out.printf("Balance difference: %.2f%n", balanceDiff);

        analyticsFacade.printCategoryAnalysis(hse.bank.area.OperationType.INCOME, start, end);
        analyticsFacade.printCategoryAnalysis(hse.bank.area.OperationType.EXPENSE, start, end);
    }

    @Override
    public String getName() {
        return "Show Analytics";
    }
}