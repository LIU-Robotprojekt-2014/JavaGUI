/*package robotcontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptPlan extends javax.swing.JFrame
{
    private List<Vertex> nodes; 
    private List<Edge> edges; 
    private DataStore ds;
    private ControlUI cui;      
    //int start = 72; //Vart vill du starta?
    //int stop = 46;  //Vart vill du sluta?  

    public OptPlan(DataStore ds, ControlUI cui) 
    { 
        this.ds = ds; 
        this.cui = cui;

    }        
    public void createPlan()
    {  
        nodes = new ArrayList<Vertex>(); 
        edges = new ArrayList<Edge>();         
        // Set up network 
        for (int i = 0; i < ds.nodes; i++) 
        { 
            Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
            nodes.add(location); 
        } 
        for (int i = 0; i < ds.arcs; i++) 
        { 
            Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
            edges.add(lane); 
        } 
        Graph graph = new Graph(nodes, edges); 
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph); 
       
        // Compute shortest path 
        dijkstra.execute(nodes.get(ds.start));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(ds.stop));
        
        // Get shortest path 
        for (int i = 0; i < path.size(); i++)
        {                                  
            ds.nodeColor[i] = Integer.parseInt(path.get(i).getId());
        }
        for (int i = 0; i < path.size()-1; i++)
        { 
            for (int j = 0; j < ds.arcs; j++)
            {                 
                if(ds.arcStart[j] == Integer.parseInt(path.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path.get(i+1).getId()))
                {                     
                    ds.arcColor[j] = 1;
                }                   
            } 
        }
    }   
}*/
//^^^Första verisionen^^^


/*package robotcontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptPlan extends javax.swing.JFrame implements Runnable
{
    private List<Vertex> nodes; 
    private List<Edge> edges; 
    private DataStore ds;
    private ControlUI cui;  
    private Nearest_Neighbour nn;
    
    double pavement;
    int turn_counter;
    int id;
    List<String> route_plan = new ArrayList<String>();
    
    int start = 73; //Vart vill du starta? 
    int stop = 74;  //Vart vill du sluta?  
    double travelled_time = 0;
    double total_time = 0;//Double.parseDouble(cui.text4); //900 är optimalt, inga SUBTOURS
    double dijkstra_time = 0;
    int replication_counter = 1;

    public OptPlan(DataStore ds, ControlUI cui, Nearest_Neighbour nn) 
    { 
        this.ds = ds;
        this.cui = cui;
        this.nn = nn;
    }        
    
    @Override
    public void run()//createPlan()
    {
        try
        {
            Thread.sleep(1);
            total_time = Double.parseDouble(cui.text4);
            while(start != 74 && total_time != 0)
            {
                ds.nodeColor[0] = start;
                if((travelled_time+dijkstra_time) < total_time)
                {
                    do
                    {
                    int arc_no = nn.Choose_Maximum(start);
                    //System.out.println("Time: "+ds.arcTime[0]);
                    travelled_time = travelled_time + ds.arcTime[arc_no];
                    //ds.tid = total_time - dijkstra_time;
                    ds.arcColor[arc_no] = 1;
                    //System.out.println(ds.arcEnd[0]);
                    start = ds.arcEnd[arc_no];
                    ds.nodeColor[replication_counter] = start;  
                    //cui.appendStatus("Node: "+start+", Time:"+travelled_time);
                    
                    //Calculate dijkstra time
                    dijkstra_time = 0;
                    nodes = new ArrayList<Vertex>(); 
                    edges = new ArrayList<Edge>();         
                    // Set up network 
                    
                    for (int i = 0; i < ds.nodes; i++) 
                    { 
                        Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                        nodes.add(location); 
                    } 
                    for (int i = 0; i < ds.arcs; i++) 
                    { 
                        Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                        edges.add(lane); 
                    } 
                    Graph graph = new Graph(nodes, edges); 
                    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph); 
                    
                    // Compute shortest path 
                    dijkstra.execute(nodes.get(start-1));
                    LinkedList<Vertex> path = dijkstra.getPath(nodes.get(stop-1));
   
                    for (int i = 0; i < path.size()-1; i++)
                    { 
                        for (int j = 0; j < ds.arcs; j++)
                        {                 
                            if(ds.arcStart[j] == Integer.parseInt(path.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path.get(i+1).getId()))
                            {                     
                                dijkstra_time = dijkstra_time + ds.arcTime[j];                            
                            }                   
                        } 
                    }
                    System.out.println("Dijkstra time: "+dijkstra_time);
                    replication_counter++;
   
                } while((travelled_time+dijkstra_time)<total_time);
            }
            else
            {
                nodes = new ArrayList<Vertex>(); 
                edges = new ArrayList<Edge>();  
                
                // Set up network 
                for (int i = 0; i < ds.nodes; i++) 
                { 
                    Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                    nodes.add(location); 
                } 
                for (int i = 0; i < ds.arcs; i++) 
                { 
                    Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                    edges.add(lane); 
                } 
                Graph graph = new Graph(nodes, edges); 
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph); 
           
                // Compute shortest path 
                dijkstra.execute(nodes.get(start-1));
                LinkedList<Vertex> path = dijkstra.getPath(nodes.get(stop-1));  
            
                // Get shortest path 
                for (int i = 0; i < path.size(); i++)
                {   
                    //System.out.println(replication_counter);
                    ds.nodeColor[i+replication_counter] = Integer.parseInt(path.get(i).getId());
                }
                for (int i = 0; i < path.size()-1; i++)
                { 
                    for (int j = 0; j < ds.arcs; j++)
                    {                 
                        if(ds.arcStart[j] == Integer.parseInt(path.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path.get(i+1).getId()))
                        {                     
                            ds.arcColor[j] = 1;
                        }                   
                    } 
                }
                
            }

        }
        
        }
        catch(InterruptedException exception) {}
    
    }
    
    
    public void CreateList(){
       
        id=1;
        turn_counter=1;
       

        for(int j = 1; j <= ds.nodeColor.length; j++)
        {
            if((turn_counter %2)==0)
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
                        //cui.appendStatus((char)0x01+"C01|"+ds.arcLength[x]+":"+pavement+":"+id+"\\n");
                        route_plan.add("Kör framåt "+ds.arcLength[x]+" cm.");
                        id++;
                        //Vänta på ACK innan detta utförs
                        for(int k=0;k<ds.arcs;k++)
                        {
                            if(ds.arcStart[k]==ds.nodeColor[j] && ds.arcEnd[k]==ds.nodeColor[j+1])
                            {
                                if(ds.arcLength[k]!=23)
                                {
                                    //cui.appendStatus((char)0x01+"C02|right:"+id+"\\n");
                                    route_plan.add("Svänger höger i korsning.");
                                   
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
                                                //cui.appendStatus((char)0x01+"C02|forward:"+id+"\\n");
                                                route_plan.add("Kör rakt fram i korsning.");
                                               
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
                                                            //cui.appendStatus((char)0x01+"C02|left:"+id+"\\n");
                                                            route_plan.add("Svänger vänster i korsning.");
                                                           
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
                                                                        //cui.appendStatus((char)0x01+"C02|backward:"+id+"\\n");
                                                                       
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
          //^perras
            cui.appendStatus(""+route_plan);
        }             
           
    } 


}*/
//^^^Andra verisionen^^^

/*package robotcontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptPlan extends javax.swing.JFrame implements Runnable
{
    private List<Vertex> nodes; 
    private List<Edge> edges; 
    private DataStore ds;
    private ControlUI cui;  
    private Nearest_Neighbour nn;
    
    double pavement;
    int turn_counter;
    int id;
    
    List<String> route_plan = new ArrayList<String>();
    
    int start = 73; //Vart vill du starta?
    int stop = 74;  //Vart vill du sluta?  
    double travelled_time = 0;
    double total_time = 0;
    double dijkstra_time = 0;
    int replication_counter = 1;
    int arc_no;
    int l=0;

    public OptPlan(DataStore ds, ControlUI cui, Nearest_Neighbour nn) 
    { 
        this.ds = ds;
        this.cui = cui;
        this.nn = nn;
    }      
              
    
    @Override
    public void run()//createPlan()
    {  
        try
        {
    	//int l=0;
    	ds.nodeColor[0] = start;
    	
    	Thread.sleep(1);
        total_time = Double.parseDouble(cui.text4);
        while(travelled_time + dijkstra_time< total_time && total_time != 0)
        {	
        	//System.out.println("L:" + l);
        	System.out.println("start:" +start);
        	
        	int arc_no = nn.Choose_Maximum(start);
        	System.out.println("Next node: " + ds.arcEnd[arc_no]);
        	//System.out.println("ArcPrio:" +ds.arcPrio[arc_no]);
            //Calculate dijkstra time
            if(ds.arcEnd[arc_no] != stop)
            {
            	dijkstra_time = 0;
                nodes = new ArrayList<Vertex>(); 
                edges = new ArrayList<Edge>();         
                // Set up network 
                
                for (int i = 0; i < ds.nodes; i++) 
                { 
                    Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                    nodes.add(location); 
                } 
                for (int i = 0; i < ds.arcs; i++) 
                { 
                    Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                    edges.add(lane); 
                } 
                Graph graph = new Graph(nodes, edges); 
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph); 
                
                // Compute shortest path 
                dijkstra.execute(nodes.get(ds.arcEnd[arc_no]-1));
                LinkedList<Vertex> path = dijkstra.getPath(nodes.get(stop-1));

                for (int i = 0; i < path.size()-1; i++)
                { 
                    for (int j = 0; j < ds.arcs; j++)
                    {                 
                        if(ds.arcStart[j] == Integer.parseInt(path.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path.get(i+1).getId()))
                        {                     
                            dijkstra_time = dijkstra_time + ds.arcTime[j];                            
                        }                   
                    } 
                }
            	
            }
        	
        	
            
            if((travelled_time+dijkstra_time+ds.arcTime[arc_no]) < total_time)
            {
                
                    travelled_time = travelled_time + ds.arcTime[arc_no];
                    //ds.tid = total_time - dijkstra_time;
                    ds.arcColor[arc_no] = 1;
                    //System.out.println(ds.arcEnd[0]);
                    start = ds.arcEnd[arc_no];
                    ds.nodeColor[replication_counter] = start;  
                    ds.arcPrio[arc_no]=0;
                    //cui.appendStatus("Node: "+start+", Time:"+travelled_time);
                    

                    //System.out.println("Dijkstra time: "+dijkstra_time);
                    replication_counter++;
   
             
            }
            else
            {
                nodes = new ArrayList<Vertex>(); 
                edges = new ArrayList<Edge>();  
                
                // Set up network 
                for (int i = 0; i < ds.nodes; i++) 
                { 
                    Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                    nodes.add(location); 
                } 
                for (int i = 0; i < ds.arcs; i++) 
                { 
                    Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                    edges.add(lane); 
                } 
                Graph graph2 = new Graph(nodes, edges); 
                DijkstraAlgorithm dijkstra2 = new DijkstraAlgorithm(graph2); 
           
                // Compute shortest path 
                dijkstra2.execute(nodes.get(start-1));
                LinkedList<Vertex> path2 = dijkstra2.getPath(nodes.get(stop-1));  
            
                // Get shortest path 
                for (int i=0; i< path2.size(); i++)
                {   
                    //System.out.println(replication_counter);
                    ds.nodeColor[i+replication_counter] = Integer.parseInt(path2.get(i).getId());
                    l=i;
                }
                for (int i = 0; i < path2.size()-1; i++)
                { 
                    for (int j = 0; j < ds.arcs; j++)
                    {                 
                        if(ds.arcStart[j] == Integer.parseInt(path2.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path2.get(i+1).getId()))
                        {                     
                            ds.arcColor[j] = 1;
                            travelled_time=travelled_time+ds.arcTime[j];
                        }                   
                    } 
                }
                
            }
            //System.out.println("hej create 1");   
        }
        System.out.println("TID!!!!!!!"+travelled_time);
        }
        catch(InterruptedException exception){}
        CreateList();  
    }
    
public void CreateList(){
    	
    	id=1;
        turn_counter=1;
        //System.out.println("hej alla");
    	

        for(int j = 1; j <= ds.nodeColor.length; j++)
        { 
        	//System.out.println("Inne 1");
        	if((turn_counter %2)==0)
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
                { //System.out.println("Inne 2");
                    if(ds.arcLength[x]!=23)
                    {
                        cui.appendStatus("Kör framåt " + ds.arcLength[x] + " cm");
                        route_plan.add((char)0x01+"C01|"+ds.arcLength[x]+":"+pavement+":"+id+"\\n");
                        System.out.println("Kör framåt " + ds.arcLength[x] + " cm");
                        id++;
                        //Vänta på ACK innan detta utförs
                        for(int k=0;k<ds.arcs;k++)
                        {
                            if(ds.arcStart[k]==ds.nodeColor[j] && ds.arcEnd[k]==ds.nodeColor[j+1])
                            {
                                if(ds.arcLength[k]!=23)
                                {
                                    cui.appendStatus("Kor hoger i korsning");
                                    route_plan.add((char)0x01+"C02|right:"+id+"\\n");
                                    System.out.println("Kor hoger i korsning");
                                    id++;
                                    
                                    turn_counter++;
                                    
                                }
                                else
                                {
                                    for(int q=0;q<ds.arcs;q++)
                                    {
                                        if(ds.arcStart[q]==ds.nodeColor[j+1] && ds.arcEnd[q]==ds.nodeColor[j+2])
                                        {
                                            if(ds.arcLength[q]!=23)
                                            {
                                                cui.appendStatus("Kor framat i korsning");
                                                route_plan.add((char)0x01+"C02|forward:"+id+"\\n");
                                                System.out.println("Kor framat i korsning");
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
                                                            cui.appendStatus("Kor vanster i korsning");
                                                            route_plan.add((char)0x01+"C02|left:"+id+"\\n");
                                                            System.out.println("Kor vanster i korsning");
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
                                                                        cui.appendStatus("Gor u-svang i korsning");
                                                                        
                                                                        route_plan.add((char)0x01+"C02|backward:"+id+"\\n");
                                                                        System.out.println("Gor u-svang");
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
          //^perras
            
        }              
        System.out.println(route_plan);
        System.out.println(travelled_time);
}
	
}*/
//^^^tredje ver.

package robotcontrol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptPlan extends javax.swing.JFrame implements Runnable
{
    private List<Vertex> nodes; 
    private List<Edge> edges; 
    private DataStore ds;
    private ControlUI cui;  
    private Nearest_Neighbour nn;
    private BluetoothTransceiver bt;
 
    int pavement;
    int turn_counter;
    int id;
    
    //List<String> route_plan = new ArrayList<String>();
    
    int start = 73; //Vart vill du starta?
    int stop = 74;  //Vart vill du sluta?  
    double travelled_time = 0;
    double turn_time = 1.0;
    double total_time = 0;
    double dijkstra_time = 0;
    int replication_counter = 1;
    int arc_no;
    int[] riktning2;
    int[] temp_nodeColor;
    int turns_dijkstra;
    int turns_dijkstra2;

    public OptPlan(DataStore ds, ControlUI cui, Nearest_Neighbour nn, BluetoothTransceiver bt) 
    { 
        this.ds = ds;
        this.cui = cui;
        this.nn = nn;
        this.bt = bt;
        
        riktning2 = new int[100];
    }  
    
    @Override
    public void run()
    {  
        try
        {           
    	int l=0;
    	ds.nodeColor[0] = start; 
        Thread.sleep(1);
    	total_time = Double.parseDouble(cui.text4);
        
        
        while(travelled_time + dijkstra_time < total_time)
        {	
        	System.out.println("L:" + l);
        	System.out.println("start:" +start);
        	
        	int arc_no = nn.Choose_Maximum(start);
        	System.out.println("Next node: " + ds.arcEnd[arc_no]);
        	//System.out.println("ArcPrio:" +ds.arcPrio[arc_no]);
            //Calculate dijkstra time
            if(ds.arcEnd[arc_no] != stop)
            {
            	dijkstra_time = 0;
                nodes = new ArrayList<Vertex>(); 
                edges = new ArrayList<Edge>();         
                // Set up network 
                
                for (int i = 0; i < ds.nodes; i++) 
                { 
                    Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                    nodes.add(location); 
                } 
                for (int i = 0; i < ds.arcs; i++) 
                { 
                    Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                    edges.add(lane); 
                } 
                Graph graph = new Graph(nodes, edges); 
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph); 
                
                // Compute shortest path 
                dijkstra.execute(nodes.get(ds.arcEnd[arc_no]-1));
                LinkedList<Vertex> path = dijkstra.getPath(nodes.get(stop-1));
                
                System.out.println("Hära " + Integer.parseInt(path.get(0).getId()));
                
                for (int i=0; i<path.size()-1; i++)
                {
                    if((ds.nodeX[Integer.parseInt(path.get(i).getId())-1] > ds.nodeX[Integer.parseInt(path.get(i+1).getId())-1]) && (ds.nodeY[Integer.parseInt(path.get(i+1).getId())-1] == ds.nodeY[Integer.parseInt(path.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r vÃ¤nster");
                        riktning2[i] = 4;
                    }
                    else if((ds.nodeX[Integer.parseInt(path.get(i).getId())-1] < ds.nodeX[Integer.parseInt(path.get(i+1).getId())-1]) && (ds.nodeY[Integer.parseInt(path.get(i+1).getId())-1] == ds.nodeY[Integer.parseInt(path.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r hÃ¶ger");
                        riktning2[i] = 2; 
                    }
                    else if((ds.nodeY[Integer.parseInt(path.get(i).getId())-1] < ds.nodeY[Integer.parseInt(path.get(i+1).getId())-1]) && (ds.nodeX[Integer.parseInt(path.get(i+1).getId())-1] == ds.nodeX[Integer.parseInt(path.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r upp");
                        riktning2[i] = 3;
                    }
                    else
                    {
                        //cui.appendStatus("KÃ¶r ner");
                        riktning2[i] = 1;
                    }
                }
                
                turns_dijkstra=0;
                
                for (int i = 0; i < path.size()-1; i++)               
                {
                	if (riktning2[i] != riktning2[i+1] && riktning2[i+1]!=0)
                	{
                		turns_dijkstra++;                		
                	}                		
                }
                System.out.println("turns:" + turns_dijkstra);

                for (int i = 0; i < path.size()-1; i++)
                { 
                    for (int j = 0; j < ds.arcs; j++)
                    {                 
                        if(ds.arcStart[j] == Integer.parseInt(path.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path.get(i+1).getId()))
                        {                     
                            dijkstra_time = dijkstra_time + ds.arcTime[j];                          
                        }                   
                    } 
                }
                dijkstra_time = dijkstra_time + turns_dijkstra*turn_time+path.size()-1;
            	
            }
            else
            {           	
            		dijkstra_time=ds.arcTime[ds.arcEnd[stop]] + turn_time;	            	            	
            }
            System.out.println("Dijkstratime :" + dijkstra_time);
            //System.out.println("Xnod start: " + ds.nodeX[start-1]);
            //System.out.println("Ynod start: " + ds.nodeY[start-1]);
            //System.out.println("Xnod next: " + ds.nodeX[ds.arcEnd[arc_no]-1]);
            //System.out.println("Ynod next: " + ds.nodeY[ds.arcEnd[arc_no]-1]);
            
            ds.riktning[0]=1;
            
            if((ds.nodeX[start-1] > ds.nodeX[ds.arcEnd[arc_no]-1]) && (ds.nodeY[ds.arcEnd[arc_no]-1] == ds.nodeY[start-1]))
            {
                //cui.appendStatus("KÃ¶r vÃ¤nster");
                ds.riktning[replication_counter] = 4;
            }
            else if((ds.nodeX[start-1] < ds.nodeX[ds.arcEnd[arc_no]-1]) && (ds.nodeY[ds.arcEnd[arc_no]-1] == ds.nodeY[start-1]))
            {
                //cui.appendStatus("KÃ¶r hÃ¶ger");
                ds.riktning[replication_counter] = 2; 
            }
            else if((ds.nodeY[start-1] < ds.nodeY[ds.arcEnd[arc_no]-1]) && (ds.nodeX[ds.arcEnd[arc_no]-1] == ds.nodeX[start-1]))
            {
                //cui.appendStatus("KÃ¶r upp");
                ds.riktning[replication_counter] = 3;
            }
            else
            {
                //cui.appendStatus("KÃ¶r ner");
                ds.riktning[replication_counter] = 1;
            }
            

            System.out.println("RIKTNING"+ds.riktning[replication_counter]);
            
            if(ds.riktning[replication_counter]!=ds.riktning[replication_counter-1])
            {
            	System.out.println("Nu svänger jag!");
            	travelled_time=travelled_time+turn_time;
            }
            
            
            if((travelled_time+dijkstra_time+ds.arcTime[arc_no]) < total_time)
            {
                
                    travelled_time = travelled_time + ds.arcTime[arc_no]+1;
                    //ds.tid = total_time - dijkstra_time;
                    //ds.arcColor[arc_no] = 1;
                    //System.out.println(ds.arcEnd[0]);
                    start = ds.arcEnd[arc_no];
                    ds.nodeColor[replication_counter] = start;  
                    ds.arcPrio[arc_no]=0;
                    //cui.appendStatus("Node: "+start+", Time:"+travelled_time);
                    

                    //System.out.println("Dijkstra time: "+dijkstra_time);
                    replication_counter++;
   
             
            }
            else
            {
                if(start != stop)
                {
                nodes = new ArrayList<Vertex>(); 
                edges = new ArrayList<Edge>();  
                
                // Set up network 
                for (int i = 0; i < ds.nodes; i++) 
                { 
                    Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1)); 
                    nodes.add(location); 
                } 
                for (int i = 0; i < ds.arcs; i++) 
                { 
                    Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), ds.arcLength[i]); 
                    edges.add(lane); 
                } 
                Graph graph2 = new Graph(nodes, edges); 
                DijkstraAlgorithm dijkstra2 = new DijkstraAlgorithm(graph2); 
           
                // Compute shortest path 
                dijkstra2.execute(nodes.get(start-1));
                LinkedList<Vertex> path2 = dijkstra2.getPath(nodes.get(stop-1));  
            
                // Get shortest path 
                for (int i=1; i < path2.size(); i++)
                {   
                    //System.out.println(replication_counter);
                    ds.nodeColor[i+replication_counter-1] = Integer.parseInt(path2.get(i).getId());
                    l=i;
                }
 
                for (int i=0; i<path2.size()-1; i++)
                {
                	if((ds.nodeX[Integer.parseInt(path2.get(i).getId())-1] > ds.nodeX[Integer.parseInt(path2.get(i+1).getId())-1]) && (ds.nodeY[Integer.parseInt(path2.get(i+1).getId())-1] == ds.nodeY[Integer.parseInt(path2.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r vÃ¤nster");
                        ds.riktning[replication_counter+i] = 4;
                    }
                    else if((ds.nodeX[Integer.parseInt(path2.get(i).getId())-1] < ds.nodeX[Integer.parseInt(path2.get(i+1).getId())-1]) && (ds.nodeY[Integer.parseInt(path2.get(i+1).getId())-1] == ds.nodeY[Integer.parseInt(path2.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r hÃ¶ger");
                        ds.riktning[replication_counter+i] = 2; 
                    }
                    else if((ds.nodeY[Integer.parseInt(path2.get(i).getId())-1] < ds.nodeY[Integer.parseInt(path2.get(i+1).getId())-1]) && (ds.nodeX[Integer.parseInt(path2.get(i+1).getId())-1] == ds.nodeX[Integer.parseInt(path2.get(i).getId())-1]))
                    {
                        //cui.appendStatus("KÃ¶r upp");
                        ds.riktning[replication_counter+i] = 3;
                    }
                    else
                    {
                        //cui.appendStatus("KÃ¶r ner");
                        ds.riktning[replication_counter+i] = 1;
                    }
                }
                
                turns_dijkstra2=0;
                
                for (int i = 0; i < path2.size()-1; i++)
                
                {
                	if (ds.riktning[replication_counter+i] != ds.riktning[replication_counter+i+1] && ds.riktning[replication_counter+i+1]!=0)
                	{
                		turns_dijkstra2++;
                		
                	}
                		
                }
                
                
                for (int i = 0; i < path2.size()-1; i++)
                { 
                    for (int j = 0; j < ds.arcs; j++)
                    {                 
                        if(ds.arcStart[j] == Integer.parseInt(path2.get(i).getId()) && ds.arcEnd[j] == Integer.parseInt(path2.get(i+1).getId()))
                        {                     
                            //ds.arcColor[j] = 1;
                            travelled_time=travelled_time+ds.arcTime[j];
                        }                   
                    } 
                }
                travelled_time= travelled_time + turns_dijkstra2 * turn_time+path2.size()-1;
                
            }
            //cui.appendStatus(""+ds.nodeColor[replication_counter]);
            //System.out.println("hej create 1");
            }
        }
        System.out.println("TID!! "+travelled_time);
        }
        catch(InterruptedException exception){}
        
        Modifyplan();
        
        for(int i=0; i<ds.nodeColor.length; i++)
        {
        	for (int j=0; j<ds.arcs; j++)
        	{
        		if(ds.arcStart[j]==ds.nodeColor[i] && ds.arcEnd[j]==ds.nodeColor[i+1])
        		{
        			ds.arcColor[j]=1;
        		}
        	}
        }
        
        CreateList();  
    }
    
public void CreateList()
{
		
    	
    	id=1;
        turn_counter=1;
        int print_counter=1;
        int node_counter=1;
        
        //ds.riktning[0]=1;
        
        for(int j = 1; j <= ds.nodeColor.length-1; j++){
        	
        	if(ds.nodeColor[j]!=0)
        	{	
        
        		if((ds.nodeX[ds.nodeColor[j-1]-1] > ds.nodeX[ds.nodeColor[j]-1]) && (ds.nodeY[ds.nodeColor[j]-1] == ds.nodeY[ds.nodeColor[j-1]-1]))
        			{
            //cui.appendStatus("KÃ¶r vÃ¤nster");
        			ds.riktning[j] = 4;
                                
        			}
        		else if((ds.nodeX[ds.nodeColor[j-1]-1] < ds.nodeX[ds.nodeColor[j]-1]) && (ds.nodeY[ds.nodeColor[j]-1] == ds.nodeY[ds.nodeColor[j-1]-1]))
        			{
        			//cui.appendStatus("KÃ¶r hÃ¶ger");
        			ds.riktning[j] = 2; 
                                
        			}
        		else if((ds.nodeY[ds.nodeColor[j-1]-1] < ds.nodeY[ds.nodeColor[j]-1]) && (ds.nodeX[ds.nodeColor[j]-1] == ds.nodeX[ds.nodeColor[j-1]-1]))
        			{
        			//cui.appendStatus("KÃ¶r upp");
        			ds.riktning[j] = 3;
                                
        			}
        		else
        			{
        			//cui.appendStatus("KÃ¶r ner");
        			ds.riktning[j] = 1;
                                
        			}
        		}
        //System.out.println("hej"+ds.riktning[j]);
        	}
        
        ds.robotIRL[0]=ds.nodeColor[0];
        for(int j = 1; j <= ds.nodeColor.length; j++)
        { 
        	//System.out.println("nODEcOLOR = " + ds.nodeColor[j]);
        	if((turn_counter %2)==0)
            {
                pavement=95;
            }
            else
            {
                pavement=65;
            }
                int threesixty=0;
        	for (int i=0; i < ds.arcs; i++)
            {
            	if (ds.arcStart[i]==ds.nodeColor[j-1] && ds.arcEnd[i]==ds.nodeColor[j])
            	{
                    if(ds.arcLength[i]!=360 || j==1)
                    {
            		ds.route_plan.add((char)0x01+"C01|"+ds.arcLength[i]+":"+pavement+":"+id+"\n");
            		id++;
                        ds.robotIRL[print_counter]=ds.nodeColor[j];
                        print_counter++;
                        threesixty=0;
                    }
                    else
                    {
                        
                            for (int k=0;k<ds.arcs;k++)
                            {
                            if(ds.arcStart[k]==ds.nodeColor[j-2] && ds.arcEnd[k]==ds.nodeColor[j-1])
                            {
                                if(ds.arcLength[k]==360)
                                {
                                    ds.route_plan.add((char)0x01+"C01|"+360+":"+pavement+":"+id+"\n");
                                    id++;
                                    ds.robotIRL[print_counter]=ds.nodeColor[j];
                                    print_counter++;
                                }
                                else
                        {
                            ds.route_plan.add((char)0x01+"C01|"+180+":"+pavement+":"+id+"\n");
                        id++;
                        ds.robotIRL[print_counter]=ds.nodeColor[j];
                        print_counter++;
                        ds.route_plan.add((char)0x01+"C01|"+180+":"+pavement+":"+id+"\n");
                        id++;
                        ds.robotIRL[print_counter]=ds.nodeColor[j];
                        print_counter++;
                        //threesixty=3;
                        }
                            }
                        }
                        
                        
                        
                        
                    }
            		
            		if(ds.riktning[j]!=ds.riktning[j+1])
            		{
            			if((ds.riktning[j] == 3 && ds.riktning[j+1] == 4) || (ds.riktning[j] == 1 && ds.riktning[j+1] == 2) || (ds.riktning[j] == 2 && ds.riktning[j+1] == 3) || (ds.riktning[j] == 4 && ds.riktning[j+1] == 1))
            			{
                                        if(ds.nodeColor[j+1]!=0)
                                        {                                          
                                            //cui.appendStatus("Kor vanster vid nod #"+(ds.nodeColor[j-1]));    
                                            //ds.kommando = +ds.nodeColor[j-1]+'\n';
                                            ds.route_plan.add((char)0x01+"C03|left:"+id+"\n");
                                            id++;                                            
                                        }
            				
            			} 
            			else if((ds.riktning[j] == 3 && ds.riktning[j+1] == 2) || (ds.riktning[j] == 1 && ds.riktning[j+1] == 4) || (ds.riktning[j] == 2 && ds.riktning[j+1] == 1) || (ds.riktning[j] == 4 && ds.riktning[j+1] == 3))
            			{
            				//cui.appendStatus("Kor hoger vid nod #"+(ds.nodeColor[j-1]));
            				ds.route_plan.add((char)0x01+"C03|right:"+id+"\n");
            				id++;
            			}
            			turn_counter++;
                                ds.robotIRL[print_counter]=ds.nodeColor[j];
                        print_counter++;
            		}
            		
            		
            		
            	}
            }
            
        }
     /*  for(int i=0; i<ds.route_plan.length; i++)
       {
           if(ds.nodeColor[i+1]==0)
           {
               
           }
       }*/
        
        /*for(int i = 0; i < ds.riktning.length; i++)
        {
            if(ds.riktning[i] != 0)
            {
        System.out.println(ds.riktning[i]);
            }
        }*/
        
        //cui.appendStatus(""+ds.route_plan);
        System.out.println(ds.route_plan);
        System.out.println(travelled_time);
        
                        int bow_no = 0;
                        
                        double TotalLength = 0;
                        double TotalPrio = 0;
                        for(int j = 1; j <= ds.nodeColor.length; j++)
                        {
                            //Thread.sleep(20);
                            double Length = Math.abs(ds.nodeX[ds.nodeColor[j-1]-1]+ds.nodeY[ds.nodeColor[j-1]-1]-ds.nodeX[ds.nodeColor[j]-1]-ds.nodeY[ds.nodeColor[j]-1]);                                       
                            TotalLength = TotalLength + Length;
                            cui.appendlength(""+TotalLength);
                            for (int p = 0; p < ds.arcs; p++)
                            {
                            if (ds.arcStart[p] == ds.nodeColor[j-1] && ds.arcEnd[p] == ds.nodeColor[j])
                                {
                                    bow_no=p;
                                }
                            }
                            TotalPrio = TotalPrio + ds.arcPrio2[bow_no];
                            ds.arcPrio2[bow_no] = 0;
                            cui.appendprio(""+TotalPrio); 
                            
                        }
        
        
        
        
}
	public void Modifyplan()
{
	temp_nodeColor = new int[1000];
	
	for(int i = 0; i < ds.nodeColor.length-4; i++)
	{
		if(ds.nodeColor[i] == ds.nodeColor[i+4])
		{
			for(int j=0; j<i; j++)
			{
				temp_nodeColor[j]=ds.nodeColor[j];
			}
			for(int j=i+4;j<ds.nodeColor.length; j++)
			{
				temp_nodeColor[j-4]=ds.nodeColor[j];
			}
			
			
			ds.nodeColor=temp_nodeColor.clone();	
		}
		
	}	
	
}
}