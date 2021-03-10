package app.nuke;

import io.micronaut.context.ApplicationContext;

public class Application {

    public static void main(String[] args) {
        try (ApplicationContext context = ApplicationContext.run()) {
            context.getBean(Zookeeper.class).showAnimals();

            context.getBean(PoorStore.class).get4Free();
        }
    }
}
