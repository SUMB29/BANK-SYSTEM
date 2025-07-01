import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>, Serializable {

    //variables
    private static int next=1;
    char Operation;
    double amount;
    int trsNo;
    Accounts acc;
    LocalDate date;

    public Transaction(char operation, Accounts acc, LocalDate date, double amount) {
        Operation = operation;
        this.acc = acc;
        this.date = date;
        this.amount = amount;
        this.trsNo=next++;
    }

    public Transaction(char operation,LocalDate date, Accounts acc ) {
        Operation = operation;
        this.acc = acc;
        this.date = date;
    }

    public char getOperation() {
        return Operation;
    }

    public void setOperation(char operation) {
        Operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTrsNo() {
        return trsNo;
    }

    public void setTrsNo(int trsNo) {
        this.trsNo = trsNo;
    }

    public Accounts getAcc() {
        return acc;
    }

    public void setAcc(Accounts acc) {
        this.acc = acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static int getNext() {
        return next;
    }

    public static void setNext(int next) {
        Transaction.next = next;
    }

    @Override public String toString(){
        return "Transaction:--------- "+" trsNo: "+trsNo+", acc: "+acc+", date: "+date+", operation: "+Operation+
                ", amount: "+amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.trsNo-o.trsNo;
    }
}
