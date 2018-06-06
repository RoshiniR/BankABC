import java.util.*;
import java.text.*;

public class SavingsAccount extends Account
{
    public final float interestRate;
    public float initialBalance;
    public float dailyBalance;
    public float totalInterestAmt;
    
    SavingsAccount(){
     interestrate = 4.0f;
     }
    
    public SavingsAccount(String customerName,float amount)
    {
        //super(customerName,amount);
        initialBalance = amount;
        //savingsInterest = 4.0;
    }
    
    public void deposit(float amount)
    {
        initialBalance = initialBalance + amount;
    }
    
    public void withdraw(float amount)
    {
        if(amount<initialBalance)
        {
            initialBalance = initialBalance - amount;
        }
        else
        {
            System.out.println("Insufficient Balance in Savings Account. Cannot withdraw " + amount);
        }
    }
    
    //public void calcInterestTotalInterest()
    //{
        
    //}
}
