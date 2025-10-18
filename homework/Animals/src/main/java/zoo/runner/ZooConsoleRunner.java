package zoo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zoo.services.ConsoleService;

@Component
public class ZooConsoleRunner implements CommandLineRunner {

    private final ConsoleService consoleService;

    public ZooConsoleRunner(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        consoleService.start();
    }
}