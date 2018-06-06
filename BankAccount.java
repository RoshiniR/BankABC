import java.util.*;
import java.io.*;

public class BankAccount
{
  String owner;
  float balance;
  Date date;
  String accountType;
  
  BankAccount()
  {
  }
  
  public BankAccount(String customerName, String accType, float amount)
  {
    owner = customerName;
    accountType = accType;
    balance = amount;
  }
  
  public void deposit(float amount, Date dt)
  {
    balance = balance + amount;
    // to-do date component
    
   }

  public void print()
    {
    System.out.println("Name : " + owner);
    System.out.println("Account Type : " + accountType);
    System.out.println("Balance : " + balance);
    }

}
