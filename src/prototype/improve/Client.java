package prototype.improve;

public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();
        Sheep sheep5 = (Sheep) sheep.clone();

        System.out.println("sheep2 " + sheep2.toString());
        System.out.println("sheep3 " + sheep3.toString());
        System.out.println("sheep4 " + sheep4.toString());
        System.out.println("sheep5 " + sheep5.toString());
    }
}
