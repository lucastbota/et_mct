package app.nuke.two;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class Condiment {
    private Set<String> condiments;

    public Condiment() {
        condiments = new HashSet<>();
        condiments.add("perna de barata");
        condiments.add("perna de grilo");
        condiments.add("antena de barata");
        condiments.add("asa do mosquito");
        condiments.add("pasta de amendoim");
        condiments.add("adoçante diet cancerígeno");
    }

    public String getSpecialCondiment() {

        return condiments.stream().findAny().get();
    }
}
