package zoo.interfaces;

import zoo.animals.Animal;
import zoo.things.Thing;
import java.util.List;

public interface IZooMeth {
    // чтобы юзать в Zoo создадим интерфейсы
    // для животных
    void addAnimal(Animal animal);
    List<Animal> getAnimals();
    int getAnimalsCount();

    // для вещей
    void addThing(Thing thing);
    List<Thing> getThings();
    int getThingsCount();
}