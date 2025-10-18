package zoo.animals;

public class Predator extends Animal {
    public Predator(String name, int food) {
        super(name, food);
    }

    @Override
    public boolean canInteractWithVisitors() {
        return false;
    }
}
