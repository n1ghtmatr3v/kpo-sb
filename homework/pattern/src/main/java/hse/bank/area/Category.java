package hse.bank.area;

public class Category {
    private final String id;
    private final OperationType type;
    private String name;

    public Category(String id, OperationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public OperationType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Category{id='%s', type=%s, name='%s'}", id, type, name);
    }
}