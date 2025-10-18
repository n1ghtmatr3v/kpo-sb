package zoo.factories;

import org.springframework.stereotype.Component;
import zoo.things.Computer;
import zoo.things.Table;
import zoo.things.Thing;

@Component
public class ThingFactory {

    public Thing createThing(String type, String name) {
        return switch (type.toLowerCase()) {
            case "table" -> new Table(name);
            case "computer" -> new Computer(name);
            default -> throw new IllegalArgumentException("Unknown thing type: " + type);
        };
    }

    public Thing createThingFromNumber(int choice, String name) {
        return switch (choice) {
            case 1 -> new Table(name);
            case 2 -> new Computer(name);
            default -> throw new IllegalArgumentException("Invalid thing choice: " + choice);
        };
    }
}