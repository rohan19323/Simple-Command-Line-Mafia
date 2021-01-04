import java.util.ArrayList;

public class Detective extends Player
{
    int test;
    private ArrayList<Detective> list;
    @Override
    public Detective clone()
    {

            Detective copy=(Detective) super.clone();
            return copy;


    }



    public int getId(Detective d)
    {
        if(d.getPlayer_num()==this.getPlayer_num())
            return 0;
        else
            return 1;
    }

    public Detective()
    {
        super();
        this.setHp(800);
        list=new ArrayList<Detective>();

    }

    public void addList(Detective d)
    {
        if(getId(d)==1)
            list.add(d);
    }

    public void chooseTest(int num)
    {
        this.test=num;
    }

    public void displaylist()
    {
        for(int i=0;i< list.size();i++)
        {
            Detective d=list.get(i);
            System.out.println(d.getPlayer_num());
        }
    }


}

