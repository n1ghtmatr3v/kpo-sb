package hse.bank.command;

import hse.bank.area.OperationType;
import hse.bank.facade.CategoryFacade;

public class CreateCategoryCommand implements Command {
    private final CategoryFacade categoryFacade;
    private final OperationType type;
    private final String name;

    public CreateCategoryCommand(CategoryFacade categoryFacade, OperationType type, String name) {
        this.categoryFacade = categoryFacade;
        this.type = type;
        this.name = name;
    }

    @Override
    public void execute() {
        var category = categoryFacade.createCategory(type, name);
        System.out.println("Created category: " + category);
    }

    @Override
    public String getName() {
        return "Create Category: " + name;
    }
}