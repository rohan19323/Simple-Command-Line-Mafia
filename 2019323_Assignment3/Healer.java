public class Healer extends Player {
    public Healer clone()
    {


            Healer copy=(Healer) super.clone();
            return copy;


    }

    public Healer()
    {
        super();
        this.setHp(800);
    }
}
