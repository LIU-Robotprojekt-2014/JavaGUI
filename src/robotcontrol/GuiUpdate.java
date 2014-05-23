package robotcontrol; 

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.LinkedList;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class GuiUpdate implements Runnable
{ 
    private ControlUI cui; 
    private DataStore ds;  
    private OptPlan op;
    private BluetoothTransceiver bt;
    private RobotControlOne rc;
    
    double pavement;
    int turn_counter;
    int id;    
    List<String> route_plan = new ArrayList<String>();
    

    public GuiUpdate(DataStore ds, ControlUI cui, OptPlan op, BluetoothTransceiver bt) 
    { 
        this.cui = cui; 
        this.ds = ds;
        this.op = op;
        this.bt = bt;
        this.rc = rc;
    }         
    
    @Override 
    public void run() 
    {   
        try 
        { 
            //BluetoothTransceiver p3 = new BluetoothTransceiver(ds,cui);
            //Thread t3 = new Thread(p3);
            Millis p4=new Millis(ds, cui);
            Thread t4 = new Thread(p4);       
            int t = 30;
            int t2 = 1;
            int turn = 1000;
            
            double hastighet = (1.0*10*1/t);
            while (true) 
            {                                    
                if(ds.updateUIflag == true)
                {    
                    t4.start();
                    
                   
                    
                    //cui.appendStatus("Hastighet: "+hastighet+" cm/s");
                    ds.nodeColor[0] = 73;
                  
                 
                    id=1;
                    turn_counter=1;
                    
                    //SMOOTH KÖRNING!
                   /* for(int j = 1; j <= ds.nodeColor.length; j++)
                    {
                        double Length = Math.abs((int)(ds.nodeX[ds.nodeColor[j-1]-1]+ds.nodeY[ds.nodeColor[j-1]-1]-ds.nodeX[ds.nodeColor[j]-1]-ds.nodeY[ds.nodeColor[j]-1]));
                        if((ds.nodeX[ds.nodeColor[j-1]-1] > ds.nodeX[ds.nodeColor[j]-1]) && (ds.nodeY[ds.nodeColor[j]-1] == ds.nodeY[ds.nodeColor[j-1]-1]))
                        {
                            //cui.appendStatus("Kör vänster");
                            ds.riktning[j] = 4;
                            cui.appendsensor1("------");
                                cui.appendsensor2("------");
                                cui.appendsensor3("------");
                                cui.appendsensor4("------");
                        }
                        else if((ds.nodeX[ds.nodeColor[j-1]-1] < ds.nodeX[ds.nodeColor[j]-1]) && (ds.nodeY[ds.nodeColor[j]-1] == ds.nodeY[ds.nodeColor[j-1]-1]))
                        {
                            //cui.appendStatus("Kör höger");
                            ds.riktning[j] = 2;
                            cui.appendsensor1("------");
                            cui.appendsensor2("------");
                            cui.appendsensor3("------");
                            cui.appendsensor4("------");
                        }
                        else if((ds.nodeY[ds.nodeColor[j-1]-1] < ds.nodeY[ds.nodeColor[j]-1]) && (ds.nodeX[ds.nodeColor[j]-1] == ds.nodeX[ds.nodeColor[j-1]-1]))
                        {
                            //cui.appendStatus("Kör upp");
                            ds.riktning[j] = 3;
                            cui.appendsensor1("------");
                            cui.appendsensor2("------");
                            cui.appendsensor3("------");
                            cui.appendsensor4("------");
                        }
                        else
                        {
                            //cui.appendStatus("Kör ner");
                            ds.riktning[j] = 1;
                            cui.appendsensor1("------");
                            cui.appendsensor2("------");
                            cui.appendsensor3("------");
                            cui.appendsensor4("------");
                        }
                        
                        
                        
                        
                        
                        if(ds.riktning[j] == 1)
                        {
                            //ds.robotX = ds.nodeX[ds.nodeColor[j-1]-1];
                            //ds.robotY = ds.nodeY[ds.nodeColor[j-1]-1];
                            //ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid-1]-1];
                            //ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid-1]-1];
                            for(int k = 1; k <= (int)Length; k++ )
                            {
                                Thread.sleep(t);
                                ds.robotY = ds.robotY - 1; 
                                cui.repaint();
                            }
                        }
                        else if(ds.riktning[j] == 3)
                        {
                            //ds.robotX = ds.nodeX[ds.nodeColor[j-1]-1];
                            //ds.robotY = ds.nodeY[ds.nodeColor[j-1]-1];
                            //ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid-1]-1];
                            //ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid-1]-1];
                            for(int k = 1; k <= (int)Length; k++ )
                            {
                                Thread.sleep(t);
                                ds.robotY = ds.robotY + 1; 
                                cui.repaint();
                            }
                        }
                        if(ds.riktning[j] == 2)
                        {
                            //ds.robotX = ds.nodeX[ds.nodeColor[j-1]-1];
                            //ds.robotY = ds.nodeY[ds.nodeColor[j-1]-1];
                            //ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid-1]-1];
                            //ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid-1]-1];
                            for(int k = 1; k <= (int)Length; k++ )
                            {
                                Thread.sleep(t);
                                ds.robotX = ds.robotX + 1; 
                                cui.repaint();
                            }
                        }
                        else if(ds.riktning[j] == 4)
                        {
                            //ds.robotX = ds.nodeX[ds.nodeColor[j-1]-1];
                            //ds.robotY = ds.nodeY[ds.nodeColor[j-1]-1];
                            //ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid-1]-1];
                            //ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid-1]-1];
                            
                            for(int k = 1; k <= (int)Length; k++ )
                            {
                                Thread.sleep(t);
                                ds.robotX = ds.robotX - 1;  
                                cui.repaint();
                            }
                        }
                    }*/
                    //^^SMOOTH KÖRNING^^
                    
                    while(ds.orderid != 1000)
                    {                   
                        Thread.sleep(1);
                        ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid]-1];
                        ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid]-1];
                        
                        
                        cui.repaint();
                        //ds.robotX = ds.nodeX[ds.robotIRL[ds.orderid-1]-1];
                        //ds.robotY = ds.nodeY[ds.robotIRL[ds.orderid-1]-1];
                        //System.out.println(ds.orderid);
                        /*if(ds.riktning[j] == ds.riktning[j-1])
                        {                          
                            cui.appendStatus("Kör FRAMÅT vid nod #"+(ds.nodeColor[j-1]));
                            //ds.kommando = "'SOH'CO2|forward:"+ds.nodeColor[j-1]+"'\\n'";
                            Thread.sleep(t2);
                        } */  
                        //cui.repaint();
                        
                        /*if((turn_counter %2)==0)
                        {
                            pavement=9.5;
                        }
                        else
                        {
                            pavement=6.5;
                        }
                        //Ruttplanering á la Perra
                        for (int x=0;x<ds.arcs; x++)
                        {
                            if (ds.arcStart[x]==ds.nodeColor[j-1] && ds.arcEnd[x]==ds.nodeColor[j])
                            {
                                if(ds.arcLength[x]!=23)
                                {
                                    cui.appendStatus((char)0x01+"C01|"+ds.arcLength[x]+":"+pavement+":"+id+"\\n");
                                    route_plan.add("Kör framåt "+ds.arcLength[x]+" cm.");
                                    id++;
                                    for(int k=0;k<ds.arcs;k++)
                                    {
                                        if(ds.arcStart[k]==ds.nodeColor[j] && ds.arcEnd[k]==ds.nodeColor[j+1])
                                        {
                                            if(ds.arcLength[k]!=23)
                                            {
                                                cui.appendStatus((char)0x01+"C02|right:"+id+"\\n");
                                                route_plan.add("Svänger höger i korsning.");
                                                Thread.sleep(t2);
                                                turn_counter++;
                                                id++;
                                            }
                                            else
                                            {
                                                for(int q=0;q<ds.arcs;q++)
                                                {
                                                    if(ds.arcStart[q]==ds.nodeColor[j+1] && ds.arcEnd[q]==ds.nodeColor[j+2])
                                                    {
                                                        if(ds.arcLength[q]!=23)
                                                        {
                                                            cui.appendStatus((char)0x01+"C02|forward:"+id+"\\n");
                                                            route_plan.add("Kör rakt fram i korsning.");
                                                            Thread.sleep(t2);
                                                            id++;
                                                        }
                                                        else
                                                        {
                                                            for(int w=0;w<ds.arcs;w++)
                                                            {
                                                                if(ds.arcStart[w]==ds.nodeColor[j+2] && ds.arcEnd[w]==ds.nodeColor[j+3])
                                                                {
                                                                    if(ds.arcLength[w]!=23)
                                                                    {
                                                                        cui.appendStatus((char)0x01+"C02|left:"+id+"\\n");
                                                                        route_plan.add("Svänger vänster i korsning.");
                                                                        Thread.sleep(t2);
                                                                        turn_counter++;
                                                                        id++;
                                                                    }
                                                                    else
                                                                    {
                                                                        for(int y=0;y<ds.arcs;y++)
                                                                        {
                                                                            if(ds.arcStart[y]==ds.nodeColor[j+3] && ds.arcEnd[y]==ds.nodeColor[j+4])
                                                                            {
                                                                                if(ds.arcLength[y]!=23)
                                                                                {
                                                                                    cui.appendStatus((char)0x01+"C02|backward:"+id+"\\n");
                                                                                    Thread.sleep(t2);
                                                                                    route_plan.add("Gör en u-sväng i korsning.");
                                                                                    id++;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }                                    
                                    }
                                }                               
                            }
                        }
                      //^perras*/  
                        //Thread.sleep(1);
                        /*if((ds.riktning[j] == 3 && ds.riktning[j+1] == 4) || (ds.riktning[j] == 1 && ds.riktning[j+1] == 2) || (ds.riktning[j] == 2 && ds.riktning[j+1] == 3) || (ds.riktning[j] == 4 && ds.riktning[j+1] == 1))
                        {
                            //cui.appendStatus("Kör VÄNSTER vid nod #"+(ds.nodeColor[j-1]));    
                            //ds.kommando = "'SOH'CO2|left:"+ds.nodeColor[j-1]+"'\\n'";
                            Thread.sleep(turn);
                            cui.repaint();
                        }                       
                        if((ds.riktning[j] == 3 && ds.riktning[j+1] == 2) || (ds.riktning[j] == 1 && ds.riktning[j+1] == 4) || (ds.riktning[j] == 2 && ds.riktning[j+1] == 1) || (ds.riktning[j] == 4 && ds.riktning[j+1] == 3))
                        {                           
                            //cui.appendStatus("Kör HÖGER vid nod #"+(ds.nodeColor[j-1]));
                            //ds.kommando = "'SOH'CO2|right:"+ds.nodeColor[j-1]+"'\\n'";
                            Thread.sleep(turn);
                            cui.repaint();
                        }*/
                        cui.repaint();
                    }              
                    //System.out.println(ds.route_plan);
                    cui.repaint();
                //} 
                    }
                cui.repaint();
            }
            
        }
        catch (InterruptedException exception) {} 
    }             
}
