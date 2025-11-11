package hse.bank.command;

public class TimedCommandDecorator implements Command {
    private final Command decoratedCommand;

    public TimedCommandDecorator(Command decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();

        decoratedCommand.execute();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.printf("Command '%s' executed in %d ms%n", decoratedCommand.getName(), duration);
    }

    @Override
    public String getName() {
        return decoratedCommand.getName() + " (timed)";
    }
}