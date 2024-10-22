import java.util.Scanner;

abstract class Beverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }


    private void boilWater() {
        System.out.println("Кипячение воды.");
    }

    private void pourInCup() {
        System.out.println("Наливание в чашку.");
    }


    abstract void brew();
    abstract void addCondiments();


    boolean customerWantsCondiments() {
        return true;
    }
}


class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Заваривание чая.");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавление лимона.");
    }
}




class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Заваривание кофе.");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавление сахара и молока.");
    }

    @Override
    boolean customerWantsCondiments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Хотите добавить сахар и молоко (y/n)? ");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("y");
    }
}


public class Template_Method {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        Beverage coffee = new Coffee();

        System.out.println("\nПриготовление чая...");
        tea.prepareRecipe();

        System.out.println("\nПриготовление кофе...");
        coffee.prepareRecipe();
    }
}


