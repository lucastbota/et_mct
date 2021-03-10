package app.nuke.two;

public class Gum implements Candy{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gum{" +
                "name='" + name + '\'' +
                '}';
    }
}
