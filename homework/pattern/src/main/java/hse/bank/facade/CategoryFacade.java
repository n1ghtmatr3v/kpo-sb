package hse.bank.facade;

import hse.bank.area.Category;
import hse.bank.area.OperationType;
import hse.bank.factory.DomainFactory;
import java.util.*;

public class CategoryFacade {
    private final Map<String, Category> categories = new HashMap<>();
    private final DomainFactory factory;

    public CategoryFacade(DomainFactory factory) {
        this.factory = factory;
    }

    public Category createCategory(OperationType type, String name) {
        Category category = factory.createCategory(type, name);
        categories.put(category.getId(), category);
        return category;
    }

    public void updateCategory(String id, String newName) {
        Category category = categories.get(id);
        if (category != null) {
            category.setName(newName);
        }
    }

    public void deleteCategory(String id) {
        categories.remove(id);
    }

    public Category getCategory(String id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public List<Category> getCategoriesByType(OperationType type) {
        return categories.values().stream()
                .filter(c -> c.getType() == type)
                .toList();
    }
}