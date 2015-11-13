package sample;

/**
 * Created by user on 10/12/2015.
 */
public class EnergyMule extends Mule {
    public static final int ENERGY_COST = 150;
    public EnergyMule() {
        setCost(ENERGY_COST);
        setName("Energy Mule");
    }

}
