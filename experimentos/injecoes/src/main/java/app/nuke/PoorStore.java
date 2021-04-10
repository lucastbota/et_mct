package app.nuke;

import app.nuke.two.Candy;

import javax.inject.Inject;
import javax.inject.Named;

public class PoorStore {

    /**
     @Inject private Candy chocolate;
     @Inject private Candy gum;
     Caused by: io.micronaut.context.exceptions.NonUniqueBeanException: Multiple possible bean candidates found: [app.nuke.two.Candy, app.nuke.two.Candy]
     */

    /**
     * Não resolve por field
     */
    @Inject
    @Named("chocolate")
    private Candy chocolate;
    @Inject
    @Named("gum")
    private Candy gum;

    public void get4Free() {
        System.out.println(chocolate);
        System.out.println(gum);
    }
}
