package robotcontrol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStore 
{
    DataStore ds;
    ControlUI cui;
    OptPlan op;
    BluetoothTransceiver bt;
    
    int nodes;
    int arcs;
    int start;
    int stop;
    int orderid;
    int[] arcStart;
    int[] arcEnd;
    int[] arcPrio;
    int[] arcPrio2;
    int[] arcPrio3;
    int[] arcLength;
    int[] arcColor;
    int[] nodeColor;
    int[] robotIRL;
    double[] arcTime;   
    int[] riktning; 
    double[] nodeX;
    double[] nodeY;
    double robotX;
    double robotY;
    double tid;
    boolean networkRead;
    boolean updateUIflag;
    boolean bluetoothstart;
    boolean indikator; 
    boolean isRunning;
    boolean redBackground;
    String kommando;
    String fileName = null;
    List<String> route_plan = new ArrayList<String>();
    
    public DataStore() 
    {
        //-----Initialize the datastore with fixed size arrays for storing the network data-----//
        nodes = 0;
        arcs = 0;
        //start = 72;
        //stop = 70;
        nodeX = new double[1000];
        nodeY = new double[1000];
        arcStart = new int[1000];
        arcEnd = new int[1000];
        arcPrio = new int[1000];
        arcPrio2 = new int[1000];
        arcPrio3 = new int[1000];
        arcColor = new int[1000];
        nodeColor = new int[1000];
        arcLength = new int[1000];
        arcTime = new double[1000];        
        riktning = new int[1000];        
        networkRead = false;
        updateUIflag = false;
        bluetoothstart = false;
        indikator = false; 
        isRunning = true;
        redBackground = false;      
        kommando = new String(); 
        orderid = 0;
        
        robotIRL = new int[1000];
        
    }

    public void setFileName(String newFileName) 
    {
        this.fileName = newFileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void readNet() 
    {
        String line;

        if (fileName == null) 
        {
            System.err.println("No file name set. Data read aborted.");
            return;
        }
        try 
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file, "iso-8859-1");
            String[] sline;

            //-----Read number of nodes-----//
            line = (scanner.nextLine());
            nodes = Integer.parseInt(line.trim());
            line = scanner.nextLine();
            arcs = Integer.parseInt(line.trim());
            
            //-----Read nodes as number, x, y-----//
            for (int i=0; i < nodes; i++)
            {
                line = scanner.nextLine();               
                //-----Split space separated data on line-----//
                sline = line.split(" ");
                nodeX[i] = Double.parseDouble(sline[1].trim());
                nodeY[i] = Double.parseDouble(sline[2].trim());
            }

            //-----Read arc list as start node number, end node number-----//
            for (int i=0; i < arcs; i++)
            {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                arcStart[i] = Integer.parseInt(sline[0].trim());
                arcEnd[i] = Integer.parseInt(sline[1].trim());
                arcPrio[i] = Integer.parseInt(sline[2].trim());
                arcPrio2[i] = Integer.parseInt(sline[2].trim());
                arcPrio3[i] = Integer.parseInt(sline[2].trim());
                arcLength[i] = 10*(Math.abs((int) (nodeX[arcStart[i] - 1] + nodeY[arcStart[i] - 1] - nodeX[arcEnd[i] - 1] - nodeY[arcEnd[i] - 1])));
                arcTime[i] = (1.0*arcLength[i])/300;
            }
            
            //-----Indicate that all network data is in place in the DataStore-----//
            networkRead = true;              
        } catch (Exception e) {e.printStackTrace();}
        robotX = nodeX[72]; 
        robotY = nodeY[72];
    }
}
