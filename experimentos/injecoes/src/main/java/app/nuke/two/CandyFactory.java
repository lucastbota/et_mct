package app.nuke.two;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;

@Factory
public class CandyFactory {

    @Prototype
    public Chocolate chocolate(Condiment condiment) {
        var chocolate = new Chocolate();
        chocolate.setName("Chocolate c/ ".concat(condiment.getSpecialCondiment()));
        return chocolate;
    }

    @Prototype
    public Gum gum(Condiment condiment) {
        var gum = new Gum();
        gum.setName("Goma de mascar c/ ".concat(condiment.getSpecialCondiment()));
        return gum;
    }
}
