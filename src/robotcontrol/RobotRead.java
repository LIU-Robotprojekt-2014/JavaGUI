package robotcontrol; 

public class RobotRead implements Runnable
{  
    private ControlUI cui; 
    private DataStore ds;
    public RobotRead(DataStore ds, ControlUI cui) 
    { 
        this.cui = cui; 
        this.ds = ds; 
    }     
    @Override 
    public void run() 
    {         
        try 
        {        
            
            while (true) 
            {   
                
                if(cui.getButtonState() == true)
                {                   
                    Thread.sleep(1);                 
                    ds.updateUIflag = true;                                        
                }
                if(cui.getButtonState() == false)
                {
                    Thread.sleep(1);
                    ds.updateUIflag = false;
                }
                /*if(ds.bluetoothstart == true)
                { 
                    Thread.sleep(10);
                    t3.start();
                }*/
                /*if(cui.getButtonState2() == true)
                {
                    Thread.sleep(1);
                    ds.bluetoothstart = true;
                }
                else
                {
                    Thread.sleep(1);
                    ds.bluetoothstart = false;
                }*/
                if(cui.getButtonState3() == true)
                {
                    Thread.sleep(1);
                    ds.indikator = true;
                }
                if(cui.getButtonState3() == false)
                {
                    Thread.sleep(1);
                    ds.indikator = false;
                }
            } 
        } 
        catch (InterruptedException exception) {} 
    } 
}