package robotcontrol;
import java.awt.*;
import javax.swing.*;
public class Millis extends JFrame implements Runnable
{
    private ControlUI cui;
    private DataStore ds;
    private OptPlan op;
    private long startTime = 10000;
    private final static java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat("ss");
    private final JButton startStopButton = new JButton();
    String time;// = Double.toString(op.total_time);
     
    public void run()
    {
        try
        {
            double TIME = Double.parseDouble(cui.text4);
            while(ds.isRunning && TIME != -1)
            {              
                //op.total_time = op.total_time + 1;
                //double TIME = Double.parseDouble(cui.text4);
                //time = cui.text4;//Double.toString(op.total_time);
                //cui.appendStatus(cui.text4);
                //cui.appendStatus(""+TIME);
                cui.appendtimer(""+TIME);
                //startStopButton.setText(""+TIME);
                TIME = TIME - 1.00;
                if(TIME == 9.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 8.00)
                {
                    ds.redBackground = false;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 7.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 6.00)
                {
                    ds.redBackground = false;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 5.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 4.00)
                {
                    ds.redBackground = false;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 3.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 2.00)
                {
                    ds.redBackground = false;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 1.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == 0.00)
                {
                    ds.redBackground = false;
                    Toolkit.getDefaultToolkit().beep();
                }
                if(TIME == -1.00)
                {
                    ds.redBackground = true;
                    Toolkit.getDefaultToolkit().beep();                    
                }
                
                //startStopButton.setText(cui.text4);
                //SwingUtilities.invokeAndWait(displayUpdater);
                Thread.sleep(1000);
                
            }
        }
        /*catch(java.lang.reflect.InvocationTargetException ite)
        {
            ite.printStackTrace(System.err);
            // Should never happen!
        }*/
        catch(InterruptedException ie) {}
        // Ignore and return!
    }
     
    public Millis(DataStore ds, ControlUI cui)
    {
        this.ds = ds;
        this.cui = cui;
        //startStopButton.addActionListener(this);
        //getContentPane().add(startStopButton);
        //setSize(200,100);
        //setVisible(true);
    }
}

/* //private Thread updater;
public void actionPerformed(ActionEvent ae)
         {
         if(ds.isRunning)
         {
             long elapsed = System.currentTimeMillis() - startTime;
             ds.isRunning = false;
             try
             {
                 updater.join();
                 // Wait for updater to finish
             }
             catch(InterruptedException ie) {}
             displayElapsedTime(elapsed);
             // Display the end-result
         }
         else
         {
             startTime = System.currentTimeMillis();
             ds.isRunning = true;
             updater = new Thread(this);
             updater.start();
         }
     }*/