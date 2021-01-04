import java.util.*;

public class Mafia extends Player {
    int target;

    public Mafia clone()
    {


            Mafia copy=(Mafia) super.clone();
            return copy;


    }

    public Mafia()
    {
        super();
        this.setHp(2500);
    }

    public int setTarget(int num)
    {
        this.target=num;
        return target;
    }


}
