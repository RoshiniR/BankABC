import java.util.*;
import java.io.*;

public abstract class BankAccount
{
  
  abstract void evalInterestCompBalance();
  
  abstract void deposit(float amount, String transdatestr);
  
  abstract void withdraw(float amount, String transdatestr);
  
  abstract void printAccountStatement();

}
