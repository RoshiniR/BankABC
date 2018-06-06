public class Transaction
{
    String dateTimeOfTransaction;
    float amount;
    enum natureOfTransaction 
        {
            deposit, withdraw;
        }
        
    Transaction(String date, float amt, natureOfTransaction not)
    {
        this.dateTimeOfTransaction = date;
        this.amount = amt;
        this.natureOfTransaction = not;
    }
}
