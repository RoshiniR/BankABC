import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

enum TransactionType 
        {
            deposit, withdraw;
        }

public class Transaction 
{
    Date dateTimeOfTransaction;
    float amount;
    TransactionType transType;
       
    Transaction(String datestr, float amt, TransactionType ttype)
    {
    	SimpleDateFormat dtformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	Date transdate = null;
		try {
			transdate = dtformat.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        this.dateTimeOfTransaction = transdate;
        this.amount = amt;
        this.transType = ttype;
    }
    
    public Date getDate() {
    	return this.dateTimeOfTransaction;
    }
    
    public void printTransaction() {
    	System.out.println(this.dateTimeOfTransaction.toString()+", "+this.amount+", "+this.transType.toString());
    }
    
    public static Comparator<Transaction> datecomparator = new Comparator<Transaction> () {
		@Override
		public int compare(Transaction o1, Transaction o2) {
			// TODO Auto-generated method stub
			return o1.getDate().compareTo(o2.getDate());
		}
	};
    
}
