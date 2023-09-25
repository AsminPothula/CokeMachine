/*
 *  Asmin Pothula 1001904488
 *  Coding Assignment 4
 */
package code4_1001904488;

public class CokeMachine
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT, NOINVENTORY;
    }
    
    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity = 5000;
    private int inventoryLevel;
    private int maxInventoryCapacity = 100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold = 0;
    
    public CokeMachine(String name, int cost, int change, int inventory)
    {
        machineName = name;
        CokePrice = cost;
        changeLevel = change;
        inventoryLevel = inventory;
    }
    
    public String getMachineName()
    {
        return machineName;
    }
    
    public int getChangeLevel()
    {
        return changeLevel;
    }
    
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
    }
    
    public int getInventoryLevel()
    {
        return inventoryLevel;
    }
    
    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity;
    }
    
    public int getCokePrice()
    {
        return CokePrice;
    }
    
    public int getChangeDispensed()
    {
        return changeDispensed;
    }
    
    public static int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }
   
    
    public boolean incrementInventoryLevel(int amountToAdd)
    {
       
        boolean boo;
        if (!((amountToAdd + inventoryLevel) > maxInventoryCapacity)) 
        {
            inventoryLevel = inventoryLevel + amountToAdd;
            boo = true;
        }
        else
        {  
            boo = false;
        }
        return boo;
    }
    
    
    public boolean incrementChangeLevel(int amountToAdd)
    {
        boolean boo;
        if (!((amountToAdd + changeLevel) > maxChangeCapacity)) 
        {
            changeLevel = changeLevel + amountToAdd;
            boo = true;
        }
        else
        {  
            boo = false;
        }
        return boo;
        
    }
    
    
    public ACTION buyACoke(int payment)
    {
        ACTION action = ACTION.EXACTPAYMENT;
        
        if (payment == CokePrice && !(changeLevel == maxChangeCapacity))
        {
            action =  ACTION.EXACTPAYMENT;
            inventoryLevel = inventoryLevel - 1;
            changeLevel = changeLevel + payment;
            numberOfCokesSold++;
            
        }
        else if(payment > CokePrice && !(changeLevel == maxChangeCapacity))
        {
            if (changeLevel >= (payment -CokePrice))
            {
                action = ACTION.DISPENSECHANGE;
                
                changeDispensed = payment - (CokePrice);
                inventoryLevel = inventoryLevel - 1;
                changeLevel = (changeLevel - changeDispensed) + payment;
                numberOfCokesSold++;
                
            }
            else
            {
                action = ACTION.INSUFFICIENTCHANGE;
            }
        }
        else if (payment < CokePrice && !(changeLevel == maxChangeCapacity))
        {
            action = ACTION.INSUFFICIENTFUNDS;
        }
        
        else if(changeLevel == maxChangeCapacity)
        {
            action = ACTION.NOINVENTORY;
        }
        
      
        
        return action;
    }
    
    public String toString()
    {
        return String.format("\nMachine Name\t\t\t%s\nCurrent Inventory Level\t\t%d\nCurrent Change Level\t\t%d\nLast Change Dispensed\t\t%d\nInventory Capacity\t\t%d\nChange Capacity\t\t\t%d\nCoke Price\t\t\t%d\nCokes Sold\t\t\t%d\n",
        machineName, inventoryLevel, changeLevel, changeDispensed, maxInventoryCapacity, maxChangeCapacity, CokePrice, numberOfCokesSold);
    }
    
    
    public void setMachineName (String newMachineName)
    {
        machineName = newMachineName;
    }
    
    public void setCokePrice(int newCokePrice)
    {
        CokePrice = newCokePrice;
    }
    
    public CokeMachine()
    {
        machineName = "New Machine";
        CokePrice = 50;
        changeLevel = 500;
        inventoryLevel = 100;
    }
    
    
}

