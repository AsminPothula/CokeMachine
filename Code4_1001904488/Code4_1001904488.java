/*
 *  Asmin Pothula 1001904488
 *  Coding Assignment 4
 */
package code4_1001904488;

import java.util.Scanner;
//import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 


public class Code4_1001904488
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
        
        do
        {
            //System.out.println("\n\nPlease choose from the following options");
            System.out.println("\n\n0. Walk away\n1. Buy a Coke\n2. Restock Machine\n3. Add change\n4. Display Machine Info\n5. Update Machine Name\n6. Update Coke Price");
            
            System.out.print("\n\nChoice? ");
            
            try
            {
                userChoice = in.nextInt();
            }
            catch (Exception e)
            {
                System.out.print("\nInvalid menu choice. Please choose again.1\n");
                userChoice = -1;
                in.nextLine();
            }
            
            if (userChoice<0 || userChoice>6)
            {
                System.out.println("\nInvalid menu choice. Please choose again.2\n");
                userChoice = in.nextInt();
                
            }
        }
        while(userChoice<0 || userChoice>6);
        
        return userChoice;
    }
    
    
   
    public static void ReadFile(String inputFileName, ArrayList <CokeMachine> SetOfCokeMachines) 
    {
        File FH = new File (inputFileName);
       
        Scanner iFH = null;
        
        try
        {
            iFH = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("\n%s file name does not exist...exiting\n", inputFileName);
            System.exit(0);
        }
        
        int k = 0;
        //String file[] = new String [10];
        
        while(iFH.hasNextLine())
        {
            String FileLine[] = iFH.nextLine().split("[|]"); 
            
            SetOfCokeMachines.add(new CokeMachine(FileLine[0], Integer.parseInt(FileLine[1]), Integer.parseInt(FileLine[2]), Integer.parseInt(FileLine[3])));
         
        }
            
       //SetOfCokeMachines.add(new CokeMachine(file[0], Integer.parseInt(file[1]), Integer.parseInt(file[2]), Integer.parseInt(file[3])));
       iFH.close();
    }
    
    public static int MachineMenu(ArrayList<CokeMachine> SetOfCokeMachines)
    {
       
        int counter = 1;
        int userChoice = 0;
        Scanner in = new Scanner(System.in);
        
        System.out.print("\n\nPick a Coke Machine: \n");
        
        do 
        {
            
            System.out.println("\n0. Exit");
            
            for(int i = 0; i<= SetOfCokeMachines.size() - 1; i++)
            {
                System.out.printf("%d. %s\n", counter, SetOfCokeMachines.get(i).getMachineName());
                counter++;
            }
            
            System.out.printf("%d. Add new machine", counter);
            System.out.print("\n\nChoice? ");
             
            try
            {
                userChoice = in.nextInt();
            }
            catch (Exception e)
            {
                userChoice = -1;
                in.nextLine();
                
            }
            
            if (userChoice<0 || userChoice>(SetOfCokeMachines.size()+1))
            {
                System.out.println("\n\nInvalid menu choice. Please choose again.3\n");
               
                userChoice = in.nextInt();
            }
           
        }
        while(userChoice<0 || userChoice>(SetOfCokeMachines.size()+1));
        
        
        
        return userChoice;
    }

    
    public static void OutputFile (String outputFileName, ArrayList <CokeMachine> SetOfCokeMachines) throws FileNotFoundException
    {
        PrintWriter out = null;
       
        try
        {
            out = new PrintWriter(outputFileName);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("\nUnable to write output file");
            System.out.printf("\nException: %s\n", e.getMessage());
            throw(e);
        }
        
        for (CokeMachine k : SetOfCokeMachines)
        {
            out.printf("%s|%d|%d|%d\n", k.getMachineName(), k.getCokePrice(), k.getChangeLevel(), k.getInventoryLevel());
        }
        
        out.close();
    }
    
    
    public static void main(String[] args) throws FileNotFoundException
    {
       ArrayList <CokeMachine> SetOfCokeMachines = new ArrayList<>();
       Scanner in = new Scanner(System.in);
       
       CokeMachine name = new CokeMachine("CSE 1325 Coke Machine", 50,500, 100 );

       
       String inputFileName = "";
       String outputFileName = "";
       
       if (args.length != 2)
       {
           System.out.println("\n\nMissing command line parameters – - Usage : INPUTFILENAME= OUTPUTFILENAME=");
           System.exit(0);
       }
       else
       {
            inputFileName = args[0].substring(args[0].indexOf('=') + 1);
            outputFileName = args[1].substring(args[1].indexOf('=') + 1);
       }
       
       ReadFile(inputFileName, SetOfCokeMachines);
       int menuChoice = MachineMenu(SetOfCokeMachines);
       
       String newMachineName = "";
       int newCokePrice = 0;
       
       int userChoice = 0; 
       
       while(menuChoice != 0)
       {
           if(menuChoice == (SetOfCokeMachines.size()+1))
           {
               SetOfCokeMachines.add(new CokeMachine());
           }
           else
           {
               //name.setMachineName(String.valueOf(SetOfCokeMachines.get(menuChoice)));
               name = SetOfCokeMachines.get(menuChoice-1);
       
                do
                {
                    System.out.printf("\n\n%s\n", name.getMachineName());
                    userChoice = CokeMenu(name.getMachineName());
                    
                    switch(userChoice)
                    {
                        case 0:
                        {
                   
                            if (name.getNumberOfCokesSold() == 0)
                            {
                                System.out.println("\nAre you sure you aren't really THIRSTY and need a Coke?");
                            }
                            else
                            {
                                System.out.println("\nBye - enjoy your Coke");
                            }
                            break;
                        }
                        case 1:
                        {
                   
                            if (name.getInventoryLevel() == 0)
                            {
                       
                                System.out.println("\nThis Coke Machine is out of inventory");
                            }
                            else
                            {
                                System.out.printf("\nA coke costs %s", displayMoney(name.getCokePrice()));
                                System.out.printf("\n\nInsert Payment: ");
                                int payment = in.nextInt();
                                //in.nextLine();
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
                        case 5:
                        {
                            in.nextLine();
                            
                            System.out.print("\nEnter a new machine name: ");
                            newMachineName = in.nextLine();
                            
                            name.setMachineName(newMachineName);
                            System.out.print("\nMachine name has been updated.");
                            break;
                        }
                        case 6:
                        {
                            System.out.print("\nEnter a new Coke price: ");
                            newCokePrice = in.nextInt();
       
                            name.setCokePrice(newCokePrice);
                            System.out.println("\nCoke price has been updated.");
                            break;
                        }
                        default:
                        {
                            System.out.println("\nInvalid menu option or system not working \n\n");
                            break;
                        }
                    }
                }
                while(userChoice != 0);
            }
            menuChoice = MachineMenu(SetOfCokeMachines);
       }
       //Once the user chooses to exit the machine menu, write the machines to the output file.
       OutputFile(outputFileName, SetOfCokeMachines);
       
       //System.out.printf("%d Coke(s) sold today\n\n", name.getNumberOfCokesSold());
       System.out.printf("\n\n%d Coke(s) sold today\n\n", CokeMachine.getNumberOfCokesSold());
    }

    
}
