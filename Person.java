import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Person
{
    protected String name;
    
    HashMap<String, SavingsAccount> savAccMap = new HashMap<String, SavingsAccount>();
    HashMap<String, CheckingAccount> chckAccMap = new HashMap<String, CheckingAccount>();
    

    Person(String name){
    	this.name = name;
    }
    
    public SavingsAccount addSavingsAcc(float bal, String createdatestr, int accid) {

    	String id = this.name+"-S-"+Integer.toString(accid);
    	SavingsAccount svacc = new SavingsAccount(bal, createdatestr);
    	savAccMap.put(id, svacc);
    	return svacc;
    }
 
    public void addSavingsAccTrans(int accno, String transtype, float amt, String datestr) {
    	String id = this.name+"-S-"+Integer.toString(accno);
    	if (transtype.equals("deposit"))
    		savAccMap.get(id).deposit(amt, datestr);
    	else if (transtype.equals("withdraw"))
    		savAccMap.get(id).withdraw(amt, datestr);
    	return;
    }
    
    public void addCheckingAccTrans(int accno, String transtype, float amt, String datestr) {
    	String id = this.name+"-C-"+Integer.toString(accno);
    	if (transtype.equals("deposit"))
    		chckAccMap.get(id).deposit(amt, datestr);
    	else if (transtype.equals("withdraw"))
    		chckAccMap.get(id).withdraw(amt, datestr);
    	return;
    }
    
    public CheckingAccount addChckAcc(float bal, String createdatestr, int accid) {

    	String id = this.name+"-C-"+Integer.toString(accid);
    	CheckingAccount chkacc = new CheckingAccount(bal, createdatestr);
    	chckAccMap.put(id, chkacc);
    	return chkacc;
    }
    
    public void printAccountDetails(int accid) {
    	System.out.println("Summary of Transactions:");
    	String id1 = this.name+"-S-"+Integer.toString(accid);
    	String id2 = this.name+"-C-"+Integer.toString(accid);
    	if (savAccMap.containsKey(id1)) {
    		
    		savAccMap.get(id1).printAccountStatement();
    	}
    	else if (chckAccMap.containsKey(id2)) {
    		chckAccMap.get(id2).printAccountStatement();
    	}
    	return;
    }
    
    
    public void putName(String name)
    {
        this.name=name;
    }
    
    public String getName()
    {
        return this.name;
    }
}
