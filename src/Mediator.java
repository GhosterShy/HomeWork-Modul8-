import java.util.ArrayList;
import java.util.List;


interface IMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
    void removeUser(User user);
}



class ChatRoom implements IMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // Сообщение отправляется всем пользователям, кроме отправителя
            if (user != sender) {
                user.receive(message, sender);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        notifyAllUsers(user.getName() + " присоединился к чату.");
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        notifyAllUsers(user.getName() + " покинул чат.");
    }

    // Уведомление всех участников чата о новом событии
    private void notifyAllUsers(String message) {
        for (User user : users) {
            user.receiveSystemMessage(message);
        }
    }
}

abstract class User {
    protected IMediator mediator;
    protected String name;

    public User(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        System.out.println(this.name + ": Отправка сообщения = " + message);
        mediator.sendMessage(message, this);
    }

    public void sendPrivate(String message, User receiver) {
        System.out.println(this.name + ": Отправка личного сообщения = " + message + " к " + receiver.getName());
        receiver.receive(message, this);
    }

    public abstract void receive(String message, User sender);

    public void receiveSystemMessage(String message) {
        System.out.println("[Системное сообщение]: " + message);
    }
}



class ConcreteUser extends User {
    public ConcreteUser(IMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void receive(String message, User sender) {
        System.out.println(this.name + " получил сообщение от " + sender.getName() + ": " + message);
    }
}






public class Mediator {
    public static void main(String[] args) {
        ChatRoom chatRoom =new ChatRoom();

        User user1 = new ConcreteUser(chatRoom, "Алиса");
        User user2 = new ConcreteUser(chatRoom, "Боб");
        User user3 = new ConcreteUser(chatRoom, "Чарли");

        // Добавление пользователей в чат
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        // Отправка общего сообщения
        user1.send("Привет всем!");

        // Отправка личного сообщения
        user2.sendPrivate("Привет, Алиса!", user1);

        // Покидание чата
        chatRoom.removeUser(user3);
    }
}
