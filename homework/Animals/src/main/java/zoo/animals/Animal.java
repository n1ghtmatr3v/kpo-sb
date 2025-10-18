package zoo.animals;

import zoo.interfaces.IAlive;
import zoo.interfaces.IInventory;

public abstract class Animal implements IAlive, IInventory {
    protected String name;
    protected int food;
    protected int number;
    protected boolean isHealthy;

    public Animal (String name, int food) {
        this.name = name;
        this.food = food;
        this.isHealthy = false;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    public boolean isHealthy()  {
        return this.isHealthy;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public abstract boolean canInteractWithVisitors();
}
