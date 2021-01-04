public class Commoner extends Player {

    public Commoner clone()
    {


            Commoner copy=(Commoner) super.clone();
            return copy;


    }
    public Commoner()
    {
        super();
        this.setHp(1000);
    }


}
