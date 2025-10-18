package zoo.services;

import org.springframework.stereotype.Service;
import zoo.animals.Animal;
import zoo.animals.Herbo;
import zoo.interfaces.IZooMeth;
import zoo.interfaces.IVeterinaryClinic;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooRealization {
    private final IZooMeth zooBase;
    private final IVeterinaryClinic clinic;

    public ZooRealization(IZooMeth zooBase, IVeterinaryClinic clinic) {
        this.zooBase = zooBase;
        this.clinic = clinic;
    }

    public boolean addAnimalWithHealth(Animal animal) {
        if (clinic.CheckHealth(animal) == true) {
            zooBase.addAnimal(animal);
            return true;
        }
        return false;
    }

    public List<Animal> getContactZooAnimals() {
        return zooBase.getAnimals()
                .stream()
                .filter(animal -> animal instanceof Herbo)
                .filter(herbo -> ((Herbo) herbo).getKindness() > 5)
                .collect(Collectors.toList());
    }

    public int getTotalZooFood() {
        return zooBase.getAnimals()
                .stream()
                .mapToInt(Animal::getFood)
                .sum();
    }

    public int getContactZooFood() {
        return getContactZooAnimals()
                .stream()
                .mapToInt(Animal::getFood)
                .sum();
    }

    public int getContactZooAnimalsCount() {
        return getContactZooAnimals().size();
    }


}