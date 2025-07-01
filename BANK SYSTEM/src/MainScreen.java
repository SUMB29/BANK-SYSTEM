import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.TreeSet;

public class MainScreen extends JFrame {
    JLabel accnNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<city> citiesCMB;
    JButton newBTN, saveBTN,showBTN,quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Accounts> accountLST;
    JPanel p1,p2,p3,p4,p5;

    Set<Accounts> accountSet=new TreeSet<>();
    Accounts acc,x;
    boolean newRec=true;

    //combobox data
    DefaultComboBoxModel<city> citiesCMBMDL;
    DefaultComboBoxModel<Accounts> accountsLSTMDL;

    //table data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> transList=new ArrayList<>();

    public  MainScreen() {
        super("ACCOUNT OPERATIONS");
        setLayout(null);
        setSize(600,400);

        //adding components to Frame
        //1-labels
        accnNoLBL=new JLabel("Account No: ");
        ownerLBL=new JLabel("Owner: ");
        balanceLBL=new JLabel("Balance: ");
        cityLBL=new JLabel("City: ");
        genderLBL=new JLabel("Gender: ");
        amountLBL=new JLabel("Amount: ");

        //2-TextFields
        accNoTXT=new JTextField();     accNoTXT.setEnabled(true);
        ownerTXT=new JTextField();
        balanceTXT=new JTextField();
        balanceTXT.setEnabled(false);
        amountTXT=new JTextField();   amountTXT.setPreferredSize(new Dimension(150,25));

        //3-combobox
        citiesCMBMDL=new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new city("Brooklen","New York"));
        citiesCMBMDL.addElement(new city("Dothan","Alaboma"));
        citiesCMBMDL.addElement(new city("Panama","Florida"));
        citiesCMBMDL.addElement(new city("Miami","Florida"));

        //adding data to jcombobox
        citiesCMB=new JComboBox<city>(citiesCMBMDL);

        //4-RadioButtons
        maleRDB=new JRadioButton("male",true);
        femaleRDB=new JRadioButton("female");
        genderBTNGRP=new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        //5-buttons
        newBTN=new JButton("New");
        saveBTN=new JButton("Save");
        showBTN=new JButton("Show");
        quitBTN=new JButton("Quit");
        depositBTN=new JButton("Deposit");
        withdrawBTN=new JButton("Withdraw");

        //6-table
        accountsLSTMDL=new DefaultComboBoxModel<>();
        accountLST=new JList<>(accountsLSTMDL);

        //7-Panels
        p1=new JPanel();
        p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2=new JPanel();
        p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3=new JPanel();
        p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4=new JPanel();
        p4.setBounds(305,5,300,190);
        p4.setLayout(new BorderLayout());

        p5=new JPanel();
        p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());

        //adding components to Jpanel
        p1.add(accnNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountLST);

        //adding panels to frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        //table creation
        tableModel=new DefaultTableModel();
        table=new JTable(tableModel);
        tableModel.addColumn("TrsNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane=new JScrollPane(table);
        p5.add(scrollPane);

        //Functionality------------------------------------------------------------------------------------------------------------------------------------------------------------------
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accNoTXT.setText("");
                ownerTXT.setText("");
                balanceTXT.setText("");
                amountTXT.setText("");
                citiesCMB.setSelectedIndex(0);
                maleRDB.setSelected(true);
                newRec=true;
            }
        });
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newRec){
                    //insertion
                    if(ownerTXT.getText().length()!=0){
                        acc=new Accounts(ownerTXT.getText(),(city)citiesCMB.getSelectedItem(),maleRDB.isSelected()?'M':'F');
                        accNoTXT.setText(String.valueOf(acc.accNo));;
                        accountSet.add(acc);
                        newRec=false;
                        accountsLSTMDL.addElement(acc);
                    }else{
                        JOptionPane.showMessageDialog(null,"PLEASE FILL ALL FIELDS");
                    }
                }else{
                    accountSet.remove(acc);    //updating
                    int a=Integer.parseInt(accNoTXT.getText());
                    String o=ownerTXT.getText();
                    city c=(city) citiesCMB.getSelectedItem();
                    char g=maleRDB.isSelected()?'M':'F';
                    double b=Double.parseDouble(balanceTXT.getText());
                    acc=new Accounts(a,o,c,g,b);
                    accountSet.add(acc);
                    accountsLSTMDL.setSelectedItem(acc);
                    newRec=false;

                }
            }
        });
        showBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String s="";
            Iterator<Accounts> it=accountSet.iterator();
            while(it.hasNext()){
                s+=it.next().toString()+"\n";
                JOptionPane.showMessageDialog(null,s);
            }
            }
        });
        depositBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newRec && amountTXT.getText().length()!=0){
                    //adding transactions to table
                    Transaction t=new Transaction('D',acc,LocalDate.now(),Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionsInTable(t);
                    //perform deposit from account
                    acc.deposit(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });
        withdrawBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newRec && amountTXT.getText().length()!=0){
                    //adding transactions to table
                    Transaction t=new Transaction('W',acc,LocalDate.now(),Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionsInTable(t);
                    //perform deposit from account
                    acc.withdraw(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });
        accountLST.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                acc = x = accountLST.getSelectedValue();
                accNoTXT.setText(String.valueOf(acc.accNo));
                ownerTXT.setText(acc.owner);
                citiesCMB.setSelectedItem(acc.city);
                if (acc.gender == 'M') maleRDB.setSelected(true);
                else femaleRDB.setSelected(true);
                balanceTXT.setText(String.valueOf(acc.balance));
                amountTXT.setEnabled(true);
                depositBTN.setEnabled(true);
                newRec = false;
                //clear table
                for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                    tableModel.removeRow(i);
                }
                //get transactions to selected account
                SearchTransactionList(acc.accNo);
            }
        });

    }

    private void DisplayTransactionsInTable(Transaction t){
        //displaying data into table
        tableModel.addRow(new Object[]{
                t.getTrsNo(),t.getDate(),t.getOperation(),t.getAmount()
        });
        //adding object to arraylist
        transList.add(t);
    }
    private void SearchTransactionList(int accNo){
        List<Transaction> fl=new ArrayList<>();
        //iterate thorugh the list
        for(Transaction t:transList){
            //filter values that contains trsNo
            if(t.getAcc().accNo==accNo)fl.add(t);
        }
        //displaying the filteredlist fl
        for(int i=0;i<fl.size();i++){
            //displaying data into table
            tableModel.addRow(new Object[]{
                    fl.get(i).getTrsNo(),
                    fl.get(i).getDate(),
                    fl.get(i).getOperation(),
                    fl.get(i).getAmount(),
            });
        }
    }

    public static void main(String[] args) throws NullPointerException {
        MainScreen ms=new MainScreen();
        ms.setVisible(true);
        ms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
