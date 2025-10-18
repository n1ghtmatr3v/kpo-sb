package zoo.services;

import org.springframework.stereotype.Component;
import zoo.animals.Animal;
import zoo.interfaces.IZooMeth;
import zoo.things.Thing;

import java.util.ArrayList;
import java.util.List;

@Component
public class Zoo implements IZooMeth {
    private List<Animal> animals = new ArrayList<>();
    private List<Thing> things = new ArrayList<>();
    private int animalCounter = 1;
    private int thingCounter = 1;

    @Override
    public void addAnimal(Animal animal) {
        animal.setNumber(animalCounter);
        animalCounter++;
        animals.add(animal);
    }
    @Override
    public void addThing(Thing thing) {
        thing.setNumber(thingCounter);
        thingCounter++;
        things.add(thing);
    }
    @Override
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    @Override
    public List<Thing> getThings() {
        return new ArrayList<>(things);
    }
    @Override
    public int getAnimalsCount() {
        return animals.size();
    }
    @Override
    public int getThingsCount() {
        return things.size();
    }
}