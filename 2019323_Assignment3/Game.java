import java.util.Scanner;
import java.util.*;
import java.math.*;

class mylist implements Comparator<Player>
{
    @Override
    public int compare(Player p1,Player p2)
    {
        if(p1.getPlayer_num()>p2.getPlayer_num())
            return 1;
        else
            return -1;
    }
}


public class Game implements Cloneable
{
    private int[] arr;
    private final int total;
    private final int num_mafia;
    private final int num_healer;
    private final int num_detective;
    private final int num_commoner;

    private LinkedList<Player> list;
    private LinkedList<Detective> listd;
    private LinkedList<Mafia> listm;
    private LinkedList<Healer> listh;
    private LinkedList<Commoner> listc;

    public Game(int n)
    {
        this.total=n;
        this.num_mafia=n/5;
        this.num_detective=n/5;
        this.num_healer=Math.max(1,Math.floorDiv(n,10));
        this.num_commoner=n-this.num_mafia-this.num_detective-this.num_healer;
        list=new LinkedList<Player>();
        listd=new LinkedList<Detective>();
        listm=new LinkedList<Mafia>();
        listh=new LinkedList<Healer>();
        listc=new LinkedList<Commoner>();
        arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=0;
        }
        this.createGame(this.total,this.num_mafia,this.num_detective,this.num_healer,this.num_commoner);
    }

    Scanner enter=new Scanner(System.in);

    public void change_arr(int num)
    {
        arr[num-1]=num;
    }

    public int check_arr(int num)
    {
        if(arr[num-1]==0)
            return 0;
        else
            return 1;
    }


    public void createGame(int n,int m,int d,int h,int c)
    {
        Mafia mafia=new Mafia();
        Detective detective=new Detective();
        Healer healer=new Healer();
        Commoner commoner=new Commoner();
        Random rand=new Random();
        int id=0;

        for(int i=0;i<m;i++)
        {


            Mafia mafia1=mafia.clone();

            list.add(mafia1);
            listm.add(mafia1);
            while(true)
            {
                id=1+rand.nextInt(n);
                if(check_arr(id)==0)
                {
                    mafia1.setPlayer_num(id);
                    change_arr(id);
                    break;

                }
                else
                    id=1+rand.nextInt(n);
            }


        }
        for(int i=0;i<d;i++)
        {
            Detective detective1=detective.clone();

            list.add(detective1);
            listd.add(detective1);
            while(true)
            {
                id=1+rand.nextInt(n);
                if(check_arr(id)==0)
                {
                    detective1.setPlayer_num(id);
                    detective1.addList(detective1);
                    change_arr(id);
                    break;

                }
                else
                    id=1+rand.nextInt(n);
            }

        }
        for(int i=0;i<h;i++)
        {
            Healer healer1=healer.clone();
            list.add(healer1);
            listh.add(healer1);
            while(true)
            {
                id=1+rand.nextInt(n);
                if(check_arr(id)==0)
                {

                    healer1.setPlayer_num(id);
                    change_arr(id);
                    break;

                }
                else
                    id=1+rand.nextInt(n);
            }

        }
        for(int i=0;i<c;i++)
        {
            Commoner commoner1=commoner.clone();
            list.add(commoner1);
            listc.add(commoner1);
            while(true)
            {
                id=1+rand.nextInt(n);
                if(check_arr(id)==0)
                {

                    commoner1.setPlayer_num(id);
                    change_arr(id);
                    break;

                }
                else
                    id=1+rand.nextInt(n);
            }

        }
        int count=0;

        //for(int i=0;i< list.size();i++)
        {
          //  Player p=list.get(i);
            //System.out.println(p.getPlayer_num());
            //count++;
        }
        //System.out.println(count);
    }

    public void user_detective()
    {
        Player p=list.get(this.num_mafia);
        System.out.println("You are Player"+p.getPlayer_num());

        System.out.println("You are a detective");
        System.out.println("Other detectives are:");


        for(int i=this.num_mafia+1;i<this.num_detective+this.num_mafia;i++)
        {
            Player p1=list.get(i);
            int num=p1.getPlayer_num();
            System.out.println("Player"+num);
        }
        startGame(p,2,p.getPlayer_num());
    }

    public void user_mafia()
    {

        Player p=list.get(0);
        System.out.println("You are Player"+p.getPlayer_num());

        System.out.println("You are a mafia");
        System.out.println("Other mafias are:");

        for(int i=1;i<this.num_mafia;i++)
        {
            Player p1=list.get(i);
            int num= p1.getPlayer_num();
            System.out.println("Player"+num);
        }
        startGame(p,1,p.getPlayer_num());
    }

    public void user_healer()
    {
        Player p=list.get(this.num_mafia+this.num_detective);
        System.out.println("You are Player"+p.getPlayer_num());

        System.out.println("You are a healer");
        System.out.println("Other healers are:");
        for(int i=this.num_mafia+this.num_detective+1;i<this.total-this.num_commoner;i++)
        {
            Player p1=list.get(i);
            int num=p1.getPlayer_num();
            System.out.println("Player"+num);
        }
        startGame(p,3,p.getPlayer_num());
    }

    public void user_commoner()
    {
        Player p=list.get(this.total-this.num_commoner);
        System.out.println("You are Player"+p.getPlayer_num());
        System.out.println("You are a commmoner");
        startGame(p,4,p.getPlayer_num());
    }

    public void user_random()
    {
        Random random=new Random();
        int choice= 1+random.nextInt(4);
        if(choice==1)
            this.user_mafia();
        else if(choice==2)
            this.user_detective();
        else if(choice==3)
            this.user_healer();
        else if(choice==4)
            this.user_healer();
    }

    public int check_target(int num,LinkedList<Mafia> listm)
    {
        int flag=0;
        for(int i=0;i< listm.size();i++)
        {
            Mafia m=listm.get(i);
            if(m.getPlayer_num()==num)
            {
                flag=1;
                return flag;
            }
        }
        return flag;
    }

    public int check_detective(int num,LinkedList<Detective> listd)
    {
        int flag=0;
        for(int i=0;i<listd.size();i++)
        {
            Detective d=listd.get(i);
            if(d.getPlayer_num()==num)
            {
                flag=1;
                return flag;
            }
        }
        return flag;
    }

    public int check_healer(int num,LinkedList<Healer> listh)
    {
        int flag=0;
        for(int i=0;i<listh.size();i++)
        {
            Healer d=listh.get(i);
            if(d.getPlayer_num()==num)
            {
                flag=1;
                return flag;
            }
        }
        return flag;
    }

    public int check_commoner(int num,LinkedList<Commoner> listc)
    {
        int flag=0;
        for(int i=0;i<listc.size();i++)
        {
            Commoner d=listc.get(i);
            if(d.getPlayer_num()==num)
            {
                flag=1;
                return flag;
            }
        }
        return flag;
    }

    public int check_remaining(int num,LinkedList<Player> remaining)
    {
        int flag=0;
        for(int i=0;i<remaining.size();i++)
        {
            Player p=remaining.get(i);
            if(p.getPlayer_num()==num)
            {
                flag=1;
                break;
            }
        }
        return flag;
    }

    public void display_end()
    {
        System.out.println("Mafias were");
        for(int i=0;i<this.num_mafia;i++)
        {
            Player p=list.get(i);
            System.out.print("Player"+p.getPlayer_num()+" ");
        }
        System.out.println("Detectives were:");
        for(int i=this.num_mafia;i<this.num_mafia+this.num_detective;i++)
        {
            Player p=list.get(i);
            System.out.println("Player"+p.getPlayer_num()+" ");
        }
        System.out.println("Healers were:");
        for(int i=this.num_mafia+this.num_detective;i<this.total-this.num_commoner;i++)
        {
            Player p=list.get(i);
            System.out.println("Player"+p.getPlayer_num()+" ");
        }
        System.out.println("Commoners were:");
        for(int i=this.total-this.num_commoner;i<list.size();i++)
        {
            Player p=list.get(i);
            System.out.println("Player"+p.getPlayer_num()+" ");
        }

    }



    public void startGame(Player player,int mode,int user_id)
    {
        LinkedList<Player> remaining=new LinkedList<Player>();
        for(int i=0;i<list.size();i++)
        {
            Player p=list.get(i);
            remaining.add(p);
        }

        int round=0;

        while(true)
        {

            round++;
            System.out.println("Round "+round+":");
            int left=remaining.size();
            System.out.println(left+" players are remaining: ");
            Collections.sort(remaining,new mylist());
            for(int i=0;i< remaining.size();i++)
            {
                Player player2=remaining.get(i);
                System.out.print("Player"+player2.getPlayer_num()+" ");
            }
            if(mode==1)
            {
                Random random2=new Random();
                int target;
                if(user_id!=0)
                {
                    System.out.println("Choose a target other than a Mafia");
                    target=enter.nextInt();
                    while(check_target(target,listm)!=0 || check_remaining(target,remaining)!=1)
                    {
                        System.out.println("Player"+target+" is a Mafia");
                        System.out.println("Please choose someone other than mafia");
                        target=enter.nextInt();
                    }
                }
                else
                {
                    System.out.println("Mafias are choosing a target");
                    target=1+ random2.nextInt(list.size());
                    while(check_target(target,listm)!=0 || check_remaining(target,remaining)!=1)
                    {
                        target=1+ random2.nextInt(list.size());
                    }

                }


                int combined=0;
                for(int i=0;i<listm.size();i++)
                {
                    Mafia mafia2=listm.get(i);
                    combined=combined+mafia2.getHp();
                }
                for(int i=0;i<remaining.size();i++)
                {
                    Player p=remaining.get(i);
                    if(p.getPlayer_num()==target)
                    {
                        int damage=p.getHp();
                        if(damage>combined)
                            p.setHp(damage-combined);
                        else
                            p.setHp(0);
                        int mafia_num=listm.size();
                        int total_damage=damage/mafia_num;
                        int rem=0;
                        for(int j=0;j< listm.size();j++)
                        {
                            Mafia mafia3=listm.get(j);
                            int inhp=mafia3.getHp();
                            if(inhp>total_damage)
                            {
                                inhp=inhp-total_damage-rem;
                                mafia3.setHp(inhp);
                            }
                            else
                            {
                                int add=Math.abs(inhp-total_damage);
                                rem=rem+add;
                                mafia3.setHp(0);
                            }

                        }



                    }
                }
                Random random1=new Random();
                int eliminated=0;
                Mafia elim_mafia=null;
                if(listd.size()==0)
                {
                    System.out.println("Detectives have chosen a player to test");
                }
                if(listd.size()!=0)
                {
                    System.out.println("Detectives have chosen a player to test");


                    int select=1+ random1.nextInt(list.size());
                    while(check_detective(select,listd)!=0 || check_remaining(select,remaining)!=1)
                    {
                        select=1+ random1.nextInt(list.size());
                    }
                    eliminated=0;
                    elim_mafia=null;


                    Detective d=listd.getFirst();

                    for(int i=0;i< listm.size();i++)
                    {
                        Mafia mafia4=listm.get(i);
                        if(mafia4.getPlayer_num()==select)
                        {
                            eliminated=select;
                            elim_mafia=mafia4;
                            break;
                        }
                    }
                }
                if(listh.size()==0)
                {
                    System.out.println("Healers have chosen someone to test");
                }

                if(listh.size()!=0)
                {
                    System.out.println("Healers have chosen someone to heal");
                    int heal=1+ random1.nextInt(list.size());
                    while(check_remaining(heal,remaining)!=1)
                    {
                        heal=1+random1.nextInt(list.size());
                    }
                    for(int i=0;i< remaining.size();i++)
                    {
                        Player p=remaining.get(i);
                        if(p.getPlayer_num()==heal)
                        {
                            int a=p.getHp();
                            a=a+500;
                            p.setHp(a);
                        }
                    }
                }

                System.out.println("--End of actions--");

                for(int i=0;i< remaining.size();i++)
                {
                    Player p=remaining.get(i);
                    if(p.getPlayer_num()==target)
                    {
                        if(p.getHp()==0)
                        {
                            System.out.println("Player"+target+" has died");
                            if(target==user_id)
                            {
                                user_id=0;
                            }
                            remaining.remove(p);
                            if(check_detective(target,listd)==1)
                                listd.remove(p);
                            if(check_healer(target,listh)==1)
                                listh.remove(p);
                            if(check_commoner(target,listc)==1)
                                listc.remove(p);

                            break;
                        }
                        else

                        {
                            System.out.println("No one has died");
                            break;
                        }

                    }
                }
                if(eliminated!=0)
                {
                    System.out.println("Player"+eliminated+"has been voted out");
                    remaining.remove(elim_mafia);
                    if(eliminated==user_id)
                    {
                        user_id=0;

                    }

                    listm.remove(elim_mafia);

                }
                if(listm.size()==0)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                int n=0;
                if(user_id!=0 && eliminated==0)
                {
                    System.out.println("Select a person to vote out");
                    n=enter.nextInt();
                }

                int vote;
                vote=1+ random1.nextInt(list.size());
                while(check_remaining(vote,remaining)!=1)
                {
                    vote=1+ random1.nextInt(list.size());
                }


                if(n==vote && eliminated==0)
                {
                    System.out.println("Player"+n+" has been voted out");
                    if(user_id==vote)
                    {
                        user_id=0;
                    }
                    for(int i=0;i< remaining.size();i++)
                    {
                        Player p=remaining.get(i);
                        if(p.getPlayer_num()==n)
                        {
                            remaining.remove(p);
                            if(check_detective(n,listd)==1)
                                listd.remove(p);
                            if(check_healer(n,listh)==1)
                                listh.remove(p);
                            if(check_commoner(n,listc)==1)
                                listc.remove(p);
                            break;
                        }
                    }
                    for(int i=0;i< listm.size();i++)
                    {
                        Mafia mafia5=listm.get(i);
                        if(mafia5.getPlayer_num()==n)
                        {
                            listm.remove(mafia5);
                        }
                    }
                    if(listm.size()==0)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    }
                    else if(listm.size()==remaining.size()-1)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }
                }
                else
                {
                    if(eliminated==0)
                    {
                        System.out.println("Player"+vote+" has been voted out");
                        if(user_id==vote)
                        {
                            user_id=0;
                        }
                        for(int i=0;i< remaining.size();i++)
                        {
                            Player p=remaining.get(i);
                            if(p.getPlayer_num()==vote)
                            {
                                remaining.remove(p);
                                if(check_detective(vote,listd)==1)
                                    listd.remove(p);
                                if(check_healer(vote,listh)==1)
                                    listh.remove(p);
                                if(check_commoner(vote,listc)==1)
                                    listc.remove(p);
                                break;
                            }
                        }
                        for(int i=0;i< listm.size();i++)
                        {
                            Mafia mafia5=listm.get(i);
                            if(mafia5.getPlayer_num()==vote)
                            {
                                listm.remove(mafia5);
                            }
                        }
                    }

                    if(listm.size()==0)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    }
                    else if(listm.size()==remaining.size()-1)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }

                }








            }
            if(mode==2)
            {
                Random random2 = new Random();
                int target;
                System.out.println("Mafias are choosing a target");
                target = 1 + random2.nextInt(list.size());
                while (check_target(target, listm) != 0 || check_remaining(target, remaining) != 1)
                {
                    target = 1 + random2.nextInt(list.size());
                }

                int combined = 0;
                for (int i = 0; i < listm.size(); i++)
                {
                    Mafia mafia2 = listm.get(i);
                    combined = combined + mafia2.getHp();
                }
                for (int i = 0; i < remaining.size(); i++)
                {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target)
                    {
                        int damage = p.getHp();
                        if (damage > combined)
                            p.setHp(damage - combined);
                        else
                            p.setHp(0);
                        int mafia_num = listm.size();
                        int total_damage = damage / mafia_num;
                        int rem = 0;
                        for (int j = 0; j < listm.size(); j++)
                        {
                            Mafia mafia3 = listm.get(j);
                            int inhp = mafia3.getHp();
                            if (inhp > total_damage)
                            {
                                inhp = inhp - total_damage - rem;
                                mafia3.setHp(inhp);
                            } else {
                                int add = Math.abs(inhp - total_damage);
                                rem = rem + add;
                                mafia3.setHp(0);
                            }

                        }


                    }
                }
                int select ;
                int eliminated=0;
                Mafia elim_mafia=null;
                if(listd.size()==0)
                {
                    System.out.println("Detectives are choosing a target");
                }

                if (user_id != 0)
                {
                    System.out.println("Choose a player to test");


                    select = enter.nextInt();
                    while (check_detective(select, listd) != 0 || check_remaining(select, remaining) != 1)
                    {
                        System.out.println("Please choose a valid option(cannot select another detective");
                        select = enter.nextInt();
                    }

                    Detective d = listd.getFirst();

                    for (int i = 0; i < listm.size(); i++)
                    {
                        Mafia mafia4 = listm.get(i);
                        if (mafia4.getPlayer_num() == select)
                        {
                            eliminated = select;
                            elim_mafia = mafia4;
                            break;
                        }
                    }
                } else if (listd.size() != 0)
                {
                    System.out.println("Detectives have chosen a player to test");


                    select = 1 + random2.nextInt(list.size());
                    while (check_detective(select, listd) != 0 || check_remaining(select, remaining) != 1)
                    {
                        select = 1 + random2.nextInt(list.size());
                    }

                    Detective d = listd.getFirst();

                    for (int i = 0; i < listm.size(); i++)
                    {
                        Mafia mafia4 = listm.get(i);
                        if (mafia4.getPlayer_num() == select) {
                            eliminated = select;
                            elim_mafia = mafia4;
                            break;
                        }
                    }
                }
                if(listh.size()==0)
                {
                    System.out.println("Healers have chosen someone to heal");
                }
                if (listh.size() != 0)
                {
                    System.out.println("Healers have chosen someone to heal");
                    int heal = 1 + random2.nextInt(list.size());
                    while (check_remaining(heal, remaining) != 1) {
                        heal = 1 + random2.nextInt(list.size());
                    }
                    for (int i = 0; i < remaining.size(); i++) {
                        Player p = remaining.get(i);
                        if (p.getPlayer_num() == heal) {
                            int a = p.getHp();
                            a = a + 500;
                            p.setHp(a);
                        }
                    }
                }

                System.out.println("--End of actions--");

                for (int i = 0; i < remaining.size(); i++)
                {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target)
                    {
                        if (p.getHp() == 0)
                        {
                            System.out.println("Player" + target + " has died");
                            if (target == user_id)
                            {
                                user_id = 0;
                            }
                            remaining.remove(p);
                            if (check_detective(target, listd) == 1)
                                listd.remove(p);
                            if (check_healer(target, listh) == 1)
                                listh.remove(p);
                            if (check_commoner(target, listc) == 1)
                                listc.remove(p);

                            break;
                        } else {
                            System.out.println("No one has died");
                            break;
                        }

                    }
                }
                if (eliminated != 0)
                {
                    System.out.println("Player" + eliminated + "has been voted out");
                    remaining.remove(elim_mafia);
                    if (eliminated == user_id)
                    {
                        user_id = 0;

                    }

                    listm.remove(elim_mafia);

                }
                if (listm.size() == 0)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                int n = 0;
                if (user_id != 0 && eliminated == 0)
                {
                    System.out.println("Select a person to vote out");
                    n = enter.nextInt();
                }

                int vote;
                vote = 1 + random2.nextInt(list.size());
                while (check_remaining(vote, remaining) != 1)
                {
                    vote = 1 + random2.nextInt(list.size());
                }


                if (n == vote && eliminated == 0)
                {
                    System.out.println("Player" + n + " has been voted out");
                    if (user_id == vote)
                    {
                        user_id = 0;
                    }
                    for (int i = 0; i < remaining.size(); i++)
                    {
                        Player p = remaining.get(i);
                        if (p.getPlayer_num() == n) {
                            remaining.remove(p);
                            if (check_detective(n, listd) == 1)
                                listd.remove(p);
                            if (check_healer(n, listh) == 1)
                                listh.remove(p);
                            if (check_commoner(n, listc) == 1)
                                listc.remove(p);
                            break;
                        }
                    }
                    for (int i = 0; i < listm.size(); i++)
                    {
                        Mafia mafia5 = listm.get(i);
                        if (mafia5.getPlayer_num() == n) {
                            listm.remove(mafia5);
                        }
                    }
                    if (listm.size() == 0) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    } else if (listm.size() == remaining.size()-1) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }
                }
                else {
                    if (eliminated == 0)
                    {
                        System.out.println("Player" + vote + " has been voted out");
                        if (user_id == vote) {
                            user_id = 0;
                        }
                        for (int i = 0; i < remaining.size(); i++) {
                            Player p = remaining.get(i);
                            if (p.getPlayer_num() == vote) {
                                remaining.remove(p);
                                if (check_detective(vote, listd) == 1)
                                    listd.remove(p);
                                if (check_healer(vote, listh) == 1)
                                    listh.remove(p);
                                if (check_commoner(vote, listc) == 1)
                                    listc.remove(p);
                                break;
                            }
                        }
                        for (int i = 0; i < listm.size(); i++) {
                            Mafia mafia5 = listm.get(i);
                            if (mafia5.getPlayer_num() == vote) {
                                listm.remove(mafia5);
                            }
                        }
                    }

                    if (listm.size() == 0) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    } else if (listm.size() == remaining.size()-1) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }


                }
            }
            if(mode==3)
            {
                Random random2 = new Random();
                int target;
                System.out.println("Mafias are choosing a target");
                target = 1 + random2.nextInt(list.size());
                while (check_target(target, listm) != 0 || check_remaining(target, remaining) != 1)
                {
                    target = 1 + random2.nextInt(list.size());
                }
                int combined = 0;
                for (int i = 0; i < listm.size(); i++)
                {
                    Mafia mafia2 = listm.get(i);
                    combined = combined + mafia2.getHp();
                }
                for (int i = 0; i < remaining.size(); i++)
                {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target)
                    {
                        int damage = p.getHp();
                        if (damage > combined)
                            p.setHp(damage - combined);
                        else
                            p.setHp(0);
                        int mafia_num = listm.size();
                        int total_damage = damage / mafia_num;
                        int rem = 0;
                        for (int j = 0; j < listm.size(); j++)
                        {
                            Mafia mafia3 = listm.get(j);
                            int inhp = mafia3.getHp();
                            if (inhp > total_damage)
                            {
                                inhp = inhp - total_damage - rem;
                                mafia3.setHp(inhp);
                            } else
                            {
                                int add = Math.abs(inhp - total_damage);
                                rem = rem + add;
                                mafia3.setHp(0);
                            }

                        }


                    }
                }
                Random random1=new Random();
                int eliminated=0;
                Mafia elim_mafia=null;
                if(listd.size()==0)
                {
                    System.out.println("Detectives have chosen a player to test");
                }
                if(listd.size()!=0)
                {
                    System.out.println("Detectives have chosen a player to test");


                    int select=1+ random1.nextInt(list.size());
                    while(check_detective(select,listd)!=0 || check_remaining(select,remaining)!=1)
                    {
                        select=1+ random1.nextInt(list.size());
                    }
                    eliminated=0;
                    elim_mafia=null;


                    Detective d=listd.getFirst();

                    for(int i=0;i< listm.size();i++)
                    {
                        Mafia mafia4=listm.get(i);
                        if(mafia4.getPlayer_num()==select)
                        {
                            eliminated=select;
                            elim_mafia=mafia4;
                            break;
                        }
                    }
                }
                int heal;
                if(listh.size()==0)
                {
                    System.out.println("Healers have chosen someone to heal");
                }
                if(user_id!=0)
                {
                    System.out.println("Select someone to heal out of the players alive");
                    heal=enter.nextInt();
                    for(int i=0;i< remaining.size();i++)
                    {
                        Player p=remaining.get(i);
                        if(p.getPlayer_num()==heal)
                        {
                            int a=p.getHp();
                            a=a+500;
                            p.setHp(a);
                        }
                    }

                }
                else if(listh.size()!=0)
                {
                    System.out.println("Healers have chosen someone to heal");
                    heal=1+ random1.nextInt(list.size());
                    while(check_remaining(heal,remaining)!=1)
                    {
                        heal=1+random1.nextInt(list.size());
                    }
                    for(int i=0;i< remaining.size();i++)
                    {
                        Player p=remaining.get(i);
                        if(p.getPlayer_num()==heal)
                        {
                            int a=p.getHp();
                            a=a+500;
                            p.setHp(a);
                        }
                    }
                }
                System.out.println("--End of actions--");

                for(int i=0;i< remaining.size();i++)
                {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target)
                    {
                        if (p.getHp() == 0)
                        {
                            System.out.println("Player" + target + " has died");
                            if (target == user_id)
                            {
                                user_id = 0;
                            }
                            remaining.remove(p);
                            if (check_detective(target, listd) == 1)
                                listd.remove(p);
                            if (check_healer(target, listh) == 1)
                                listh.remove(p);
                            if (check_commoner(target, listc) == 1)
                                listc.remove(p);

                            break;
                        } else
                        {
                            System.out.println("No one has died");
                            break;
                        }

                    }
                }
                if(eliminated!=0)
                {
                    System.out.println("Player"+eliminated+"has been voted out");
                    remaining.remove(elim_mafia);
                    if(eliminated==user_id)
                    {
                        user_id=0;

                    }

                    listm.remove(elim_mafia);

                }
                if(listm.size()==0)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                int n=0;
                if(user_id!=0 && eliminated==0)
                {
                    System.out.println("Select a person to vote out");
                    n=enter.nextInt();
                }

                int vote;
                vote=1+ random1.nextInt(list.size());
                while(check_remaining(vote,remaining)!=1)
                {
                    vote=1+ random1.nextInt(list.size());
                }


                if(n==vote && eliminated==0)
                {
                    System.out.println("Player"+n+" has been voted out");
                    if(user_id==vote)
                    {
                        user_id=0;
                    }
                    for(int i=0;i< remaining.size();i++)
                    {
                        Player p=remaining.get(i);
                        if(p.getPlayer_num()==n)
                        {
                            remaining.remove(p);
                            if(check_detective(n,listd)==1)
                                listd.remove(p);
                            if(check_healer(n,listh)==1)
                                listh.remove(p);
                            if(check_commoner(n,listc)==1)
                                listc.remove(p);
                            break;
                        }
                    }
                    for(int i=0;i< listm.size();i++)
                    {
                        Mafia mafia5=listm.get(i);
                        if(mafia5.getPlayer_num()==n)
                        {
                            listm.remove(mafia5);
                        }
                    }
                    if(listm.size()==0)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    }
                    else if(listm.size()==remaining.size()-1)
                    {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }
                }
                else
                {
                    if (eliminated == 0)
                    {
                        System.out.println("Player" + vote + " has been voted out");
                        if (user_id == vote)
                        {
                            user_id = 0;
                        }
                        for (int i = 0; i < remaining.size(); i++)
                        {
                            Player p = remaining.get(i);
                            if (p.getPlayer_num() == vote)
                            {
                                remaining.remove(p);
                                if (check_detective(vote, listd) == 1)
                                    listd.remove(p);
                                if (check_healer(vote, listh) == 1)
                                    listh.remove(p);
                                if (check_commoner(vote, listc) == 1)
                                    listc.remove(p);
                                break;
                            }
                        }
                        for (int i = 0; i < listm.size(); i++)
                        {
                            Mafia mafia5 = listm.get(i);
                            if (mafia5.getPlayer_num() == vote)
                            {
                                listm.remove(mafia5);
                            }
                        }
                    }
                }
                if(listm.size()==0)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                else if(listm.size()==remaining.size()-1)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have won");
                    display_end();
                    break;
                }





            }
            if(mode==4) {
                Random random2 = new Random();
                int target;
                System.out.println("Mafias are choosing a target");
                target = 1 + random2.nextInt(list.size());
                while (check_target(target, listm) != 0 || check_remaining(target, remaining) != 1) {
                    target = 1 + random2.nextInt(list.size());
                }
                int combined = 0;
                for (int i = 0; i < listm.size(); i++) {
                    Mafia mafia2 = listm.get(i);
                    combined = combined + mafia2.getHp();
                }
                for (int i = 0; i < remaining.size(); i++) {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target) {
                        int damage = p.getHp();
                        if (damage > combined)
                            p.setHp(damage - combined);
                        else
                            p.setHp(0);
                        int mafia_num = listm.size();
                        int total_damage = damage / mafia_num;
                        int rem = 0;
                        for (int j = 0; j < listm.size(); j++) {
                            Mafia mafia3 = listm.get(j);
                            int inhp = mafia3.getHp();
                            if (inhp > total_damage) {
                                inhp = inhp - total_damage - rem;
                                mafia3.setHp(inhp);
                            } else {
                                int add = Math.abs(inhp - total_damage);
                                rem = rem + add;
                                mafia3.setHp(0);
                            }

                        }
                    }
                }
                Random random1 = new Random();
                int eliminated = 0;
                Mafia elim_mafia = null;
                if(listd.size()==0)
                {
                    System.out.println("Detectives have chosen a player to test");
                }
                if (listd.size() != 0) {
                    System.out.println("Detectives have chosen a player to test");


                    int select = 1 + random1.nextInt(list.size());
                    while (check_detective(select, listd) != 0 || check_remaining(select, remaining) != 1) {
                        select = 1 + random1.nextInt(list.size());
                    }
                    eliminated = 0;
                    elim_mafia = null;


                    Detective d = listd.getFirst();

                    for (int i = 0; i < listm.size(); i++) {
                        Mafia mafia4 = listm.get(i);
                        if (mafia4.getPlayer_num() == select) {
                            eliminated = select;
                            elim_mafia = mafia4;
                            break;
                        }
                    }
                }

                if(listh.size()==0)
                {
                    System.out.println("Healers have chosen someone to heal");
                }
                if (listh.size() != 0) {
                    System.out.println("Healers have chosen someone to heal");
                    int heal = 1 + random2.nextInt(list.size());
                    while (check_remaining(heal, remaining) != 1) {
                        heal = 1 + random2.nextInt(list.size());
                    }
                    for (int i = 0; i < remaining.size(); i++) {
                        Player p = remaining.get(i);
                        if (p.getPlayer_num() == heal) {
                            int a = p.getHp();
                            a = a + 500;
                            p.setHp(a);
                        }
                    }
                }

                System.out.println("--End of Actions--");
                for (int i = 0; i < remaining.size(); i++) {
                    Player p = remaining.get(i);
                    if (p.getPlayer_num() == target) {
                        if (p.getHp() == 0) {
                            System.out.println("Player" + target + " has died");
                            if (target == user_id) {
                                user_id = 0;
                            }
                            remaining.remove(p);
                            if (check_detective(target, listd) == 1)
                                listd.remove(p);
                            if (check_healer(target, listh) == 1)
                                listh.remove(p);
                            if (check_commoner(target, listc) == 1)
                                listc.remove(p);

                            break;
                        } else {
                            System.out.println("No one has died");
                            break;
                        }

                    }
                }
                if (eliminated != 0) {
                    System.out.println("Player" + eliminated + "has been voted out");
                    remaining.remove(elim_mafia);
                    if (eliminated == user_id) {
                        user_id = 0;

                    }

                    listm.remove(elim_mafia);

                }
                if (listm.size() == 0) {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                int n = 0;
                if (user_id != 0 && eliminated == 0) {
                    System.out.println("Select a person to vote out");
                    n = enter.nextInt();
                }

                int vote;
                vote = 1 + random1.nextInt(list.size());
                while (check_remaining(vote, remaining) != 1) {
                    vote = 1 + random1.nextInt(list.size());
                }


                if (n == vote && eliminated == 0) {
                    System.out.println("Player" + n + " has been voted out");
                    if (user_id == vote) {
                        user_id = 0;
                    }
                    for (int i = 0; i < remaining.size(); i++) {
                        Player p = remaining.get(i);
                        if (p.getPlayer_num() == n) {
                            remaining.remove(p);
                            if (check_detective(n, listd) == 1)
                                listd.remove(p);
                            if (check_healer(n, listh) == 1)
                                listh.remove(p);
                            if (check_commoner(n, listc) == 1)
                                listc.remove(p);
                            break;
                        }
                    }
                    for (int i = 0; i < listm.size(); i++) {
                        Mafia mafia5 = listm.get(i);
                        if (mafia5.getPlayer_num() == n) {
                            listm.remove(mafia5);
                        }
                    }
                    if (listm.size() == 0) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have lost");
                        display_end();
                        break;
                    } else if (listm.size() == remaining.size()-1) {
                        System.out.println("Game Over");
                        System.out.println("Mafias have won");
                        display_end();
                        break;
                    }
                } else {
                    if (eliminated == 0) {
                        System.out.println("Player" + vote + " has been voted out");
                        if (user_id == vote) {
                            user_id = 0;
                        }
                        for (int i = 0; i < remaining.size(); i++) {
                            Player p = remaining.get(i);
                            if (p.getPlayer_num() == vote) {
                                remaining.remove(p);
                                if (check_detective(vote, listd) == 1)
                                    listd.remove(p);
                                if (check_healer(vote, listh) == 1)
                                    listh.remove(p);
                                if (check_commoner(vote, listc) == 1)
                                    listc.remove(p);
                                break;
                            }
                        }
                        for (int i = 0; i < listm.size(); i++) {
                            Mafia mafia5 = listm.get(i);
                            if (mafia5.getPlayer_num() == vote) {
                                listm.remove(mafia5);
                            }
                        }
                    }


                }
                if(listm.size()==0)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have lost");
                    display_end();
                    break;
                }
                else if(listm.size()==remaining.size()-1)
                {
                    System.out.println("Game Over");
                    System.out.println("Mafias have won");
                    display_end();
                    break;
                }
            }

        }
    }





}

