import java.util.Stack;
interface ICommand {
    void execute();
    void undo();
}

class Light {
    public void turnOn() {
        System.out.println("Свет включен.");
    }

    public void turnOff() {
        System.out.println("Свет выключен.");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта.");
    }

    public void close() {
        System.out.println("Дверь закрыта.");
    }
}

class Thermostat {
    private int temperature = 22;

    public void increaseTemperature() {
        temperature++;
        System.out.println("Температура увеличена до: " + temperature);
    }

    public void decreaseTemperature() {
        temperature--;
        System.out.println("Температура уменьшена до: " + temperature);
    }
}


class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}
class DoorOpenCommand implements ICommand {
    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

class DoorCloseCommand implements ICommand {
    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}

class IncreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public IncreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.increaseTemperature();
    }

    @Override
    public void undo() {
        thermostat.decreaseTemperature();
    }
}

class DecreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public DecreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.decreaseTemperature();
    }

    @Override
    public void undo() {
        thermostat.increaseTemperature();
    }
}




class Invoker {
    private Stack<ICommand> commandHistory = new Stack<>();

    public void executeCommand(ICommand command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoCommand() {
        if (!commandHistory.isEmpty()) {
            ICommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("Нет команд для отмены.");
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();


        ICommand lightOn = new LightOnCommand(light);
        ICommand lightOff = new LightOffCommand(light);
        ICommand doorOpen = new DoorOpenCommand(door);
        ICommand doorClose = new DoorCloseCommand(door);
        ICommand increaseTemp = new IncreaseTemperatureCommand(thermostat);
        ICommand decreaseTemp = new DecreaseTemperatureCommand(thermostat);


        Invoker remote = new Invoker();


        remote.executeCommand(lightOn);
        remote.executeCommand(doorOpen);
        remote.executeCommand(increaseTemp);


        remote.undoCommand();
        remote.undoCommand();
        remote.undoCommand();
        remote.undoCommand();

    }
}


