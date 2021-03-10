package app.nuke;

import app.nuke.one.Animal;
import io.micronaut.context.annotation.Prototype;

import javax.inject.Inject;
import java.util.Optional;

public class Zookeeper {
   /*
    Falhará miseravelmente sem a factory
    @Inject
    private Animal dog;

    Falhará miseravelmente sem a factory
    Ps: trará Optional.ofempty
   @Inject
   private Optional<Animal> dog;
*/
   @Inject
   private Optional<Animal> dog;
    public void showAnimals() {
        dog.ifPresent(d -> {
            d.setName("Dog");
            d.setWeight(52.00f);

            System.out.println(dog);
        });

    }
}
