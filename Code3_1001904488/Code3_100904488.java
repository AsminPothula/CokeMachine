/*
 *  Asmin Pothula 1001904488
check student ID and change it later 
 */
package code3_100904488;

import java.util.Scanner;

public class Code3_100904488
{
   
    public static String displayMoney (int amount)
    {
        String dollars = String.valueOf(amount/100);
        String cents = String.valueOf(amount%100);
        
        return "$" + dollars + "." + (cents.length() == 1? "0":"") + cents;
    }
    
    public static int CokeMenu(String machineName)//pass in name 
    {
        Scanner in = new Scanner(System.in);
        
        
        
        int userChoice = 0;
      
        //choice? and invalid - print statements - have correct ones - while loop 
        do
        {
            //System.out.println("CSE 1325 Coke Machine");
            //%s with name of coke machine 
           
            System.out.println("\n\nPlease choose from the following options");
            System.out.println("0. Walk away\n1. Buy a Coke\n2. Restock Machine\n3. Add change\n4. Display Machine Info");
            
            //correct message to diplay on screen???
            System.out.print("\nChoice? ");
            
            try
            {
                userChoice = in.nextInt();
            }
            catch (Exception e)
            {
                //should i let the user know? check video 
                System.out.print("\nInvalid menu choice. Please choose again.\n");
                userChoice = -1;
                in.nextLine();
            }
            
            if (userChoice<0 || userChoice>4)
            {
                //correct message to diplay on screen???
                System.out.println("\nInvalid menu choice. Please choose again.\n");
                userChoice = in.nextInt();
            }
        }
        while(userChoice<0 || userChoice>4);
        
        return userChoice;
    }
    

//Make sure when your at max change level, when you attempt to buy a coke it should give the print statement 
//Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment
//where???

    public static void main(String[] args)
    {
       Scanner in = new Scanner(System.in);
       ///
       CokeMachine name = new CokeMachine("CSE 1325 Coke Machine", 50,500, 100 );


       int userChoice = 0; 
       do
       {
           userChoice = CokeMenu(name.getMachineName());
           switch(userChoice)
           {
               case 0:
               {
                    //are you really thirsty
                    //bye 
                    //number of cokes sold - static - lecture 

                    //if no of cokes sold == 0, 
                    // = or == ???
                    if (name.getNumberOfCokesSold() == 0)
                    {
                        System.out.println("Are you sure you aren't really THIRSTY and need a Coke?");
                    }
                    else
                    {
                        System.out.println("Bye - enjoy your Coke");
                    }
                    break;
               }
               case 1:
               {
                   //coke price ,payment etc print statements 
                   //System.out.printf("A coke costs %d", name.getCokePrice());
                   
                   if (name.getInventoryLevel() == 0)
                   {
                       
                       System.out.println("This Coke Machine is out of inventory");
                   }
                   else
                   {
                       System.out.printf("A coke costs %s", displayMoney(name.getCokePrice()));
                       System.out.printf("\n\nInsert Payment: ");
                       int payment = in.nextInt();
                       CokeMachine.ACTION action = name.buyACoke(payment);
                      
                        switch(action)
                        {
                            case EXACTPAYMENT:
                            {
                                System.out.println("\nHere's your coke and thank you for the exact payment.");
                                break;
                            }
                            case DISPENSECHANGE:
                            {
                                //
                                //int change = payment - (name.getCokePrice());
                                System.out.printf("\n\nHere's your Coke and your change is %s\n", displayMoney(name.getChangeDispensed()));
                                break;
                            }
                            case INSUFFICIENTCHANGE:
                            {
                                System.out.println("\n\nUnable to give change at this time...returning your payment\n");
                                break;
                            }
                            case INSUFFICIENTFUNDS:
                            {
                                System.out.println("\n\nInsufficient payment...returning your payment\n");
                                break;
                            }
                            case NOINVENTORY:
                            {
                                System.out.println("\n\nUnable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment\n");
                                break;
                            }
                            default :
                            {
                                break;
                            }
                        }
                        
                        
                    }
                    break;
                }
                case 2:
                {
                    //print invenotry level whnever the amountoadd addition fails/succeeds
                    //machine has been restocmed and fail statement 
                    System.out.print("\nHow much change are you adding to the machine? ");
                    int amountToAdd = in.nextInt();
                    
                    if (name.incrementInventoryLevel(amountToAdd) == true) 
                    {
                        System.out.println("\nYour machine has been stocked");
                        System.out.printf("\nYour inventory level is %d\n", name.getInventoryLevel());
                        
                    }
                    else
                    {
                       System.out.println("\nYou have exceeded your machine's max capacity - no inventory was added.");
                       System.out.printf("\nYour inventory level is %d\n", name.getInventoryLevel());
                    }
                    break;
                }
                case 3:
                {
                    System.out.print("\nHow much change are you adding to the machine? ");
                    int amountToAdd = in.nextInt();
                    
                    if (name.incrementChangeLevel(amountToAdd) == true) 
                    {
                        System.out.println("\nYour change level has been updated");
                        System.out.printf("\nYour change level is %s with a max capacity of %s\n", displayMoney(name.getChangeLevel()), displayMoney(name.getMaxChangeCapacity()));
                        
                    }
                    else
                    {
                       System.out.println("\nYou exceeded your machine's max capacity - no change added.");
                       System.out.printf("\nYour change level is %s with a max capacity of %s\n", displayMoney(name.getChangeLevel()), displayMoney(name.getMaxChangeCapacity()));
                    }
                    break;
                }
                case 4:
                {
                    System.out.println(name.toString());
                    break;
                }
                default:
                {
                    System.out.println("\nInvalid menu option");
                    break;
                }
            }
        }
        while(userChoice != 0);
    
    }

    
}
