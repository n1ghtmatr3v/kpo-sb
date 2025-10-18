package zoo.services;

import org.springframework.stereotype.Service;
import zoo.animals.Animal;
import zoo.animals.Herbo;
import zoo.enums.AnimalType;
import zoo.factories.AnimalFactory;
import zoo.factories.ThingFactory;
import zoo.things.Thing;

import java.util.Scanner;

@Service
public class ConsoleService {
    private final ZooRealization zooService;
    private final AnimalFactory animalFactory;
    private final ThingFactory thingFactory;
    private final Scanner scanner;

    public ConsoleService(ZooRealization zooService, AnimalFactory animalFactory, ThingFactory thingFactory) {
        this.zooService = zooService;
        this.animalFactory = animalFactory;
        this.thingFactory = thingFactory;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> addThing();
                case 3 -> showAnimals();
                case 4 -> showContactAnimals();
                case 5 -> showFoodConsumption();
                case 6 -> showInventoryReport();
                case 0 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== МОСКОВСКИЙ ЗООПАРК ===");
        System.out.println("1. Добавить животное");
        System.out.println("2. Добавить вещь");
        System.out.println("3. Показать всех животных");
        System.out.println("4. Показать контактных животных");
        System.out.println("5. Потребление еды");
        System.out.println("6. Инвентарный отчет");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void addAnimal() {
        System.out.println("\nВыберите тип животного:");
        System.out.println("1. Обезьяна");
        System.out.println("2. Кролик");
        System.out.println("3. Тигр");
        System.out.println("4. Волк");
        System.out.print("Ваш выбор: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        AnimalType animalType = animalFactory.getTypeFromNumber(typeChoice);

        System.out.print("Имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Потребление еды (кг/день): ");
        int food = scanner.nextInt();

        Integer kindness = null;
        if (animalFactory.requiresKindness(animalType)) {
            System.out.print("Уровень доброты (1-10): ");
            kindness = scanner.nextInt();
        }

        Animal animal = animalFactory.createAnimal(animalType, name, food, kindness);
        if (zooService.addAnimalWithHealth(animal)) {
            System.out.println("Животное принято в зоопарк!");
        } else {
            System.out.println("Животное не принято - не здорово!");
        }
    }

    private void addThing() {
        System.out.println("Добавление вещей временно недоступно");
    }

    private void showAnimals() {
        System.out.println("Просмотр животных временно недоступен");
    }

    private void showContactAnimals() {
        System.out.println("\n=== КОНТАКТНЫЕ ЖИВОТНЫЕ ===");
        var contactAnimals = zooService.getContactZooAnimals();
        if (contactAnimals.isEmpty()) {
            System.out.println("Контактных животных нет");
            return;
        }
        contactAnimals.forEach(animal -> {
            if (animal instanceof Herbo) {
                Herbo herbo = (Herbo) animal;
                System.out.println("- " + animal.getName() + " (доброта: " + herbo.getKindness() + ", еда: " + animal.getFood() + " кг/день)");
            }
        });
    }

    private void showFoodConsumption() {
        System.out.println("\n=== ПОТРЕБЛЕНИЕ ЕДЫ ===");
        System.out.println("Общее потребление: " + zooService.getTotalZooFood() + " кг/день");
        System.out.println("Контактные животные: " + zooService.getContactZooFood() + " кг/день");
    }

    private void showInventoryReport() {
        System.out.println("Инвентарный отчет временно недоступен");
    }
}