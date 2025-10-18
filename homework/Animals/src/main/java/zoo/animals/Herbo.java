package zoo.animals;

public abstract class Herbo extends Animal {
    protected int kindness;

    public Herbo(String name, int food, int kindness) {
        super(name, food);
        this.kindness = kindness;
    }

    @Override
    public boolean canInteractWithVisitors() {
        return this.kindness > 5; // доброта > 5 - может в контактный зоопарк
    }

    public int getKindness() {
        return this.kindness;
    }

    public void setKindness(int kindness) {
        this.kindness = kindness;
    }
}
