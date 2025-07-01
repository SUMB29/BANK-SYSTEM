import  java.util.Date;
import java.util.Set;

public class Accounts implements Comparable {
    //variables
    static int nextAccNo=10;   //class variable
    int accNo;
    String owner;
    city city;
    char gender;
    double balance;
    Date openDate;

    public Accounts() {

    }

    public Accounts(String owner, city city, char gender) {
        this.owner = owner;
        this.city = city;
        this.gender = gender;

        this.accNo=nextAccNo;
        nextAccNo+=10;
        balance=0.0;
        openDate=null;   //System.currentDate();
    }

    public Accounts(int accNo, String owner, city city, char gender, double balance) {
        this.accNo = accNo;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        this.balance = balance;
        SetBalance(balance);
    }

    @Override
    public int compareTo(Object o) {
        return this.owner.compareTo(((Accounts)o).owner);
    }

    public void SetBalance(double b){
        this.balance=(b>0.0)?b:0.0;
    }

    @Override
    public String toString(){
        return "Account:----- "+" accNo: "+accNo+", owner: "+owner+'\''+", city: "+city+",gender: "+gender+",balance: "+balance+
                ",openDate: "+openDate;
    }

    public double withdraw(double amount){
        if(amount<=balance){
            SetBalance(balance-amount);
        }else{
            amount=balance;
//            SetBalance(0.0);
        }
        return 0.0;
    }

    public void deposit(double amount){
        if(amount>0) SetBalance(balance+amount);
    }
}
