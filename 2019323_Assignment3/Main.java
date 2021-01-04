import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Game g=null;
        Scanner enter=new Scanner(System.in);
        System.out.println("Welcome to Mafia");
        System.out.println("Enter number of players");
        int n=enter.nextInt();
        if(n>=6)
        {
            g=new Game(n);
        }
        else
        {
            int n1;
            do
            {
                System.out.println("Please enter a number greater than or equal to 6");
                n1=enter.nextInt();
                if(n1>=6)
                {
                    g=new Game(n1);
                    break;
                }


            }while(n1<6);

        }
        System.out.println("Choose a character");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
        int choice=enter.nextInt();
        if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5)
        {
            while(true)
            {
                System.out.println("Please select a valid option(1-5)");
                choice=enter.nextInt();
                if(choice==1 || choice==2 || choice==3|| choice==4 || choice==5)
                    break;
            }
        }

        switch (choice) {
            case 1:
                g.user_mafia();
                break;
            case 2:
                g.user_detective();
                break;
            case 3:
                g.user_healer();
                break;
            case 4:
                g.user_commoner();
                break;

            case 5:
                g.user_random();
                break;

            default:
                System.out.println("Invalid choice");


        }


}   }

