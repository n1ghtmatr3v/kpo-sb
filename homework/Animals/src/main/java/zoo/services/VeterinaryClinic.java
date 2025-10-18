package zoo.services;

import org.springframework.stereotype.Service;
import zoo.animals.Animal;
import zoo.interfaces.IVeterinaryClinic;

import java.util.Random;

@Service
public class VeterinaryClinic implements IVeterinaryClinic {
    @Override
    public boolean CheckHealth(Animal animal) {
        Random random = new Random();
        return random.nextInt(10) < 8; // 80% шанс, что здоров
    }
}
