package zoo.things;

import zoo.interfaces.IInventory;

public abstract class Thing implements IInventory {
    protected String name;
    protected int number;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
