package robotcontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RobotControlOne 
{
    DataStore ds;
    ControlUI cui;
    BluetoothTransceiver bt;
    OptPlan op;
    Nearest_Neighbour nn;
    Millis mi;
    
    RobotControlOne() 
    {
        /*
         * Initialize the DataStore call where all "global" data will be stored
         */
        ds = new DataStore();
        /*
         * The sets the file path and read network text file. Adjust for your
         * needs.
         */
        ds.setFileName("C:/Plugg/TNK111 - Kandidatkusren, robot/streets_final.txt");
        ds.readNet();
        /*
         * Initialize and show the GUI. The constructor gets access to the
         * DataStore
         */
        
        //mi = new Millis(ds);
        
        cui = new ControlUI(ds, this);
        cui.setVisible(true);
        cui.showStatus();
        
        bt = new BluetoothTransceiver(cui,ds,op);
        
        
        RobotRead p1 = new RobotRead(ds,cui); 
        Thread t1 = new Thread(p1); 
               
        GuiUpdate p2 = new GuiUpdate(ds,cui,op,bt);
        Thread t2 = new Thread(p2);
        
        //OptPlan p5 = new OptPlan(ds,cui,nn);
        //Thread t5 = new Thread(p5);

        
        t1.start();
        t2.start();
        //t5.start();

        nn = new Nearest_Neighbour(ds,cui);
        
        OptPlan op = new OptPlan(ds,cui,nn,bt); 
       
        //op.createPlan();
        /*op.CreateList();
        System.out.println(op.route_plan);*/
        
        
        
    }
    
    public void startt3()
    {        
        BluetoothTransceiver p3 = new BluetoothTransceiver(cui,ds,op);
        Thread t3 = new Thread(p3);        
        t3.start();
    }
    /*public void tid()
    {
        Millis p4=new Millis(ds, cui);
        Thread t4 = new Thread(p4);
        t4.start();
    }*/
    public void opt()
    {
        OptPlan p5 = new OptPlan(ds,cui,nn,bt);
        Thread t5 = new Thread(p5);
        t5.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /*
         * This is the "main" method what gets called when the application
         * starts All that is done here is to make an instance of the
         * RobotControl class, and thereby, call the RobotControl constructor.
         */
        RobotControlOne x = new RobotControlOne();
    }
}
