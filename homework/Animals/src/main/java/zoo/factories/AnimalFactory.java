package zoo.factories;


import org.springframework.stereotype.Component;
import zoo.animals.*;
import zoo.enums.AnimalType;

@Component
public class AnimalFactory {

    public Animal createAnimal(AnimalType type, String name, int food, Integer kindness) {
        return switch (type) {
            case MONKEY -> new Monkey(name, food, kindness);
            case RABBIT -> new Rabbit(name, food, kindness);
            case TIGER -> new Tiger(name, food);
            case WOLF -> new Wolf(name, food);
        };
    }

    public boolean requiresKindness(AnimalType type) {
        return type == AnimalType.MONKEY || type == AnimalType.RABBIT;
    }

    public AnimalType getTypeFromNumber(int choice) {
        return switch (choice) {
            case 1 -> AnimalType.MONKEY;
            case 2 -> AnimalType.RABBIT;
            case 3 -> AnimalType.TIGER;
            case 4 -> AnimalType.WOLF;
            default -> throw new IllegalArgumentException("Неверный выбор животного");
        };
    }
}