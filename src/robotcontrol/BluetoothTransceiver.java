/*package robotcontrol;
import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
public class BluetoothTransceiver implements Runnable
{
    private ControlUI cui; 
    private DataStore ds;     
    String meddelande_ut_id;
    String meddelande_in_cut;
    String meddelande_ut;
    String meddelande_in;
    int id = 0;   
    public BluetoothTransceiver(DataStore ds, ControlUI cui) 
    { 
        this.cui = cui; 
        this.ds = ds;       
    }  
    
    @Override
    public void run()           
    {    
        meddelande_ut = "nothing";
        try 
        {    
            cui.appendStatus("Trying to connect...");
            StreamConnection anslutning = (StreamConnection)Connector.open("btspp://001204067186:1"); //001204067186 = rymdo
            PrintStream bluetooth_ut = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in = new BufferedReader(new InputStreamReader(anslutning.openInputStream()));
            BufferedReader tangentbord = new BufferedReader(new InputStreamReader(System.in)); 
            while(true) 
            {                    
                if(meddelande_ut != ds.kommando)
                {               
                    meddelande_ut = ds.kommando;
                    //String meddelande_ut = tangentbord.readLine();
                    if (meddelande_ut == "Disconnect...") 
                    {
                        break;
                    }
                    applyid(meddelande_ut);
                    cui.appendStatus("Skickat meddelande: "+meddelande_ut_id);               
                    bluetooth_ut.print(meddelande_ut_id);
                    meddelande_in = bluetooth_in.readLine();
                    System.out.print("Mottaget: " + meddelande_in);                                
                    cui.appendStatus("Mottaget meddelande: "+meddelande_in);                
                    Thread.sleep(1);
                    id++;
                }
                Thread.sleep(1);
            }            
            anslutning.close(); 
            cui.appendStatus("Disconnected");
            System.out.println("Disconnected");
        } 
        catch (Exception e) { cui.appendStatus(e.toString()); }
    } 
    
    public void Meddelandep(String med)
    {   
        int beginindex = med.indexOf((char)0x01) + 1;
        int endindex = med.lastIndexOf("\\n");
        //String med = (char)0x01 + "hejsan\n tjajaba";   
        System.out.println("med: " + med); 
        if(endindex<52)
        {  
            meddelande_in_cut = med.substring(beginindex, endindex);    
        }
        else 
        {       
            System.out.println("Error message to long");
        }     
        System.out.println("meddelande_in_cut: " + meddelande_in_cut);
    }  */ 

/*public void applyid(String med_ut)
{ 
    //System.out.println("meddelande_ut i applyid: " + med_ut); 
    String idstring = Integer.toString(id);  
    meddelande_ut_id = med_ut.replace("\n", ":" + idstring + "\n");     
    }
}*/


package robotcontrol;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class BluetoothTransceiver implements Runnable
{
    private DataStore ds;
    private OptPlan op;
    private ControlUI cui;	
    String meddelande_ut;
    String meddelande_ut_id;
    String meddelande_in;
    String meddelande_in_sens;
    String meddelande_in_cut;
    String prev_meddelande = "nothing";
    boolean sensordata1=false;
    boolean sensordata2=false;
    boolean orderdata= false;
    boolean start=true;
    int id=0;
	
    public BluetoothTransceiver(ControlUI cui, DataStore ds, OptPlan op)
    {		
	this.ds = ds;
        this.op = op;
        this.cui = cui;	
	//System.out.println("kommando i bluetoothTranc = " + k.kom);
    }
    
    @Override
    public void run() 
    {		
        //System.out.println("kommando i bluetooth = " + k.kom);
		
	try 
        { 
            //Millis p4=new Millis(ds, cui);
            //Thread t4 = new Thread(p4);  
            StreamConnection anslutning = (StreamConnection) Connector.open("btspp://001204067186:1");
            PrintStream bluetooth_ut = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in = new BufferedReader(new InputStreamReader(anslutning.openInputStream()));
            //BufferedReader tangentbord = new BufferedReader(new InputStreamReader(System.in));			
            while(true) 
            { 		
                //cui.appendStatus(""+ds.arcs);
                if(start == true)
		{
                    cui.appendStatus("----Bluetooth Connected!----");
                    //t4.start();
                    //cui.appendStatus("här!");
                    bluetooth_ut.print(ds.route_plan.get(0));
                    //cui.appendStatus("Plow-E: Nu är jag i nod "+ds.robotIRL[ds.orderid]+"!"); 
                    cui.appendStatus("ADM:      Åk till nod "+ds.robotIRL[ds.orderid+1]+"!");
                    //cui.appendStatus("Skickar: " + ds.route_plan.get(0));
                    ds.updateUIflag = true;
                    id=1;
                    start=false;	
		}																				
		meddelande_in = bluetooth_in.readLine(); //(char)0x01+"S01|45:65:43:68\\n"					
		System.out.println("Mottaget-> " + meddelande_in + " <-Mottaget");							
		//bluetooth_ut.print((char)0x01+"C01|115:950:2\n");							
		//System.out.print((char)0x01+"C01|115:9.5:2\n");							
		/*if(meddelande_in == null && prev_meddelande!=meddelande_in)
		{								
                    System.out.println("Skickar: " + k.route_plan.get(0)); //bluetooth_ut.println(k.route_plan.get(0));
                    bluetooth_ut.println((char)0x01+"C01|115:9.5:2\\n");
                    id=1;
                    prev_meddelande = meddelande_in;					
		}*/					
		if(prev_meddelande != meddelande_in)
		{					
                    prev_meddelande=meddelande_in;			
                    sensordata1 = meddelande_in.contains("S01");
                    sensordata2 = meddelande_in.contains("S02");
                    orderdata = meddelande_in.contains("C99");		
                    int endindex = meddelande_in.length();
                    int lineindex = meddelande_in.indexOf("|");					
                    if (sensordata1 == true)
                    {						
                        //skriv ut någonstans i gui						
                        //System.out.println("Sensordata1 " + meddelande_in);						
                        int indexbra = meddelande_in.indexOf(":");
			String sensor1id = meddelande_in.substring(lineindex+1,indexbra);
			int sensor1 = Integer.parseInt(sensor1id);									
			int indexbetter = meddelande_in.indexOf(":",indexbra+1);
			String sensor2id = meddelande_in.substring(indexbra+1, indexbetter);
			int sensor2 = Integer.parseInt(sensor2id);						
			int indexbest = meddelande_in.indexOf(":", indexbetter+1);
			String sensor3id = meddelande_in.substring(indexbetter+1,indexbest);
			int sensor3 = Integer.parseInt(sensor3id);						
			String sensor4id = meddelande_in.substring(indexbest+1, endindex);
			int sensor4 = Integer.parseInt(sensor4id);						
			System.out.println("Sensordata1  " + sensor1 +"  "+ sensor2 +"  "+ sensor3 +"  "+ sensor4);
                        cui.appendsensor1(""+sensor1);
                        cui.appendsensor2(""+sensor2);
                        cui.appendsensor3(""+sensor3);
                        cui.appendsensor4(""+sensor4);
			sensordata1=false;
                    }					
                    else if(sensordata2 == true)
                    {
                        //skriv ut någonstans i gui							
			//System.out.println("Sensordata2 " + meddelande_in);						
			String distanceid = meddelande_in.substring(lineindex+1,endindex);
			int distance = Integer.parseInt(distanceid);
                        cui.appendlength(""+distance);
			sensordata2=false;						
                    }
                    else if(orderdata==true)
                    {	
                         
                        //cui.appendStatus("Mottaget " + meddelande_in);						
			//System.out.println("endindex " + endindex);					
			String stringorderid = meddelande_in.substring(lineindex+1,endindex);						
			//System.out.println("stringorderid " + stringorderid);						
			ds.orderid = Integer.parseInt(stringorderid);
                        cui.appendStatus("Plow-E:  Nu är jag i nod "+ds.robotIRL[ds.orderid]+"!");
                        if(ds.orderid==0)
                        {
                            ds.indikator=true;
                        }
                        
                        else
                        {
                            ds.indikator=false;
                        }
                            
                        System.out.println("Mottaget orderid " + ds.orderid);						
			if(ds.orderid > ds.route_plan.size()-1)
                        {							
                            break;
			}						
			bluetooth_ut.print(ds.route_plan.get(ds.orderid));
                        cui.appendStatus("ADM:      Åk till nod "+ds.robotIRL[ds.orderid+1]+"!"); 
                        //cui.appendStatus("Skickar: " + ds.route_plan.get(ds.orderid));
			System.out.print("Skickar " + ds.route_plan.get(ds.orderid));							
			orderdata=false;										
                    }					
		}							
		/*if(meddelande_ut != k.kom)
                {
                    meddelande_ut = k.kom;						
                    System.out.println("meddelande_ut " + meddelande_ut);					
                    if (meddelande_ut=="close") 
                    {
			break;
                    }								
                    applyid(meddelande_ut);								
                    System.out.println("meddelandeut med id : " + meddelande_ut_id);								
                    m.appendSkickat(meddelande_ut_id);								
                    bluetooth_ut.println(meddelande_ut_id);								
                    meddelande_in = bluetooth_in.readLine();								
                    System.out.println("Mottaget innan cut: " + meddelande_in);								
                    Meddelandep(meddelande_in);
                    m.appendMottaget(meddelande_in);								
                    m.appendMottaget(meddelande_in_cut);							    
                    Thread.sleep(1);								
                    id++;								
		}*/								
		Thread.sleep(100);										
            }								
            //anslutning.close();
	}  
        catch (Exception e) { System.out.print(e.toString()); } 		
    }
	
    public void Meddelandep(String med)
    { 
        int beginindex = med.indexOf((char)0x01) + 1;
        int endindex = med.length();
        //String med = (char)0x01 + "hejsan\n tjajaba"; 
        System.out.println("med: " + med);
        if(endindex<52)
        {	    
            meddelande_in_cut = med.substring(beginindex, endindex);     
        }   
        else 
        {    	
            System.out.println("Error message to long");
        }      
        System.out.println("meddelande_in_cut: " + meddelande_in_cut);
    }
}    
