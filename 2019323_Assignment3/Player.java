import java.util.*;


public class Player implements Cloneable
{
    private int player_num;
    private int hp;
    private String status;
    public Player clone()
    {
        try
        {
            Player copy=(Player)super.clone();
            return copy;
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
    }

    public int vote()
    {
        System.out.println("Select a player to vote out of the remaining players");
        Scanner enter=new Scanner(System.in);
        int n=enter.nextInt();
        return n;

    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int value)
    {
        this.hp=value;
    }

    public void setPlayer_num(int id)
    {
        this.player_num=id;
    }

    public int getPlayer_num()
    {
        return player_num;
    }
}

