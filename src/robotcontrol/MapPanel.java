package robotcontrol;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class MapPanel extends JPanel
{    
    DataStore ds;
    BluetoothTransceiver bt;
    OptPlan op;
    ControlUI cui;    

    MapPanel(DataStore ds) 
    {
        this.ds = ds;
    }
    
    protected void paintComponent(Graphics g) 
    {       
        super.paintComponent(g);
        final Color LIGHT_COLOR = new Color(150, 150, 150);
        final Color DARK_COLOR = new Color(0, 0, 0);
        final Color RED_COLOR = new Color(255, 0, 0);        
        int x, y;
        int x1, y1;
        int x2, y2;
        int px = 0; 
        int py = 0;
        int px2 = 0;
        int py2 = 0;       
        Image robot,red;
        robot = Toolkit.getDefaultToolkit().getImage("C:/Plugg/TNK111 - Kandidatkusren, robot/wall-e.png");
        red = Toolkit.getDefaultToolkit().getImage("C:/Plugg/TNK111 - Kandidatkusren, robot/red.jpg");                      
        final int circlesize = 12;
        final int ysize = 350;
        final int xsize = 700;        
        //-----Only try to plot if the data has been properly read from the file-----//
        if (ds.networkRead == true) 
        {       
            //-----Compute scale factor in order to keep the map in proportion when the window is resized-----//
            int height = getHeight();
            int width = getWidth();
            double xscale = 1.0 * width / xsize;
            double yscale = 1.0 * height / ysize;
            Graphics2D g2 = (Graphics2D) g;
            setBackground(Color.LIGHT_GRAY);
            
            if(ds.redBackground == true)
            {
                setBackground(Color.RED);
            }
            
            for (int i = 0; i < ds.arcs; i++) 
            {                
                float dash[] = {8f};
                x1 = (int) (ds.nodeX[ds.arcStart[i] - 1] * xscale);
                y1 = (int) (ds.nodeY[ds.arcStart[i] - 1] * yscale);
                x2 = (int) (ds.nodeX[ds.arcEnd[i] - 1] * xscale);
                y2 = (int) (ds.nodeY[ds.arcEnd[i] - 1] * yscale);                
                //-----Draw the arcs-----//               
                g2.setStroke(new BasicStroke(12f));
                g.setColor(DARK_COLOR);
                g.drawLine(x1, height - y1, x2, height - y2); 
                g2.setStroke(new BasicStroke(8f));
                g.setColor(LIGHT_COLOR);
                g.drawLine(x1, height - y1, x2, height - y2);    
                /*g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));
                g.setColor(Color.BLACK);
                g.drawLine(x1, height - y1, x2, height - y2);*/
                
                if (ds.arcColor[i] == 1)
                {    
                    g2.setStroke(new BasicStroke(12f));                  
                    g.setColor(Color.BLACK);
                    g.drawLine(x1, height - y1, x2, height - y2); 
                    /*g2.setStroke(new BasicStroke(8f));                  
                    g.setColor(Color.DARK_GRAY);
                    g.drawLine(x1, height - y1, x2, height - y2); */   
                    g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));
                    g.setColor(Color.WHITE);
                    g.drawLine(x1, height - y1, x2, height - y2);                    
                }                                    
                
                //-----Display the arc's priorities and the arrows-----//                             
                if (Math.abs(y2-y1) < 1)
                {
                    px = x1 + (x2-x1)/2;
                    if(x2-x1 > 1)
                    {
                        py = y1-9;
                      	py2 = y1 - 14;
                    }
                    else
                    {
                        py = y1-1;
                        py2 = y1 + 5;
                    }
                }                
                if(Math.abs(x2-x1) < 1)
                {
                    py = y1 + (y2-y1)/2;
                    if(y2-y1>1)
                    {
                        px = x1-1;
                        px2 = x1 + 7;
                    }
                    else
                    {
                        px = x1-10;
                	px2 = x1 - 17;
                    }                        
                }
                
                if(ds.arcLength[i] > 230 && Math.abs(y2-y1) < 1 && x2 > x1)
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 14));
                    g.drawString(">", (int) px, (int)height - (int)py-4);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Magneto", Font.BOLD, 12));
                    g.drawString(""+ds.arcPrio3[i], (int) px, (int)height - (int)py2);                  
                }
                else if(ds.arcLength[i] > 230 && Math.abs(y2-y1) < 1 && x1 > x2)
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 14));
                    g.drawString("<", (int) px, (int)height - (int)py+4);   
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Magneto", Font.BOLD, 12));
                    g.drawString(""+ds.arcPrio3[i], (int) px, (int)height - (int)py2);                   
                }
                else if((ds.arcLength[i] > 230) && (Math.abs(x2-x1)) < 1 && (y2 > y1))
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    g.drawString("^", (int) px-5, (int)height - (int)py+8);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Magneto", Font.BOLD, 12));
                    g.drawString(""+ds.arcPrio3[i], (int) px2, (int)height - (int)py);   
                }
                else if((ds.arcLength[i] > 230) && (Math.abs(x2-x1)) < 1 && (y1 > y2))
                {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 14));
                    g.drawString("v", (int) px+5, (int)height - (int)py);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Magneto", Font.BOLD, 12));
                    g.drawString(""+ds.arcPrio3[i], (int) px2-4, (int)height - (int)py); 
                }
                
                //-----Draw the buildings-----//
                if(i == 57 || i == 61 || i == 65 || i == 37 || i == 49 || i == 25 || i == 29)
                {
                    g2.setStroke(new BasicStroke(35));
                    g.setColor(Color.BLACK);
                    g2.drawLine((int)(ds.nodeX[i]*xscale)+40, height - y1 + 35, (int)(ds.nodeX[i+3]*xscale)-40, height - y1 + 35); 
                    g2.setStroke(new BasicStroke(31));
                    g.setColor(Color.DARK_GRAY);
                    g2.drawLine((int)(ds.nodeX[i]*xscale)+40, height - y1 + 35, (int)(ds.nodeX[i+3]*xscale)-40, height - y1 + 35);   
                }
                if(i == 17 || i == 41)
                {
                    g2.setStroke(new BasicStroke(35));
                    g.setColor(Color.BLACK);
                    g2.drawLine((int)(ds.nodeX[i]*xscale)+40, height - y1 + 35, (int)(ds.nodeX[i+7]*xscale)-40, height - y1 + 35);  
                    g2.setStroke(new BasicStroke(31));
                    g.setColor(Color.DARK_GRAY);
                    g2.drawLine((int)(ds.nodeX[i]*xscale)+40, height - y1 + 35, (int)(ds.nodeX[i+7]*xscale)-40, height - y1 + 35);   
                }                             
            }
            
            //-----Draw the nodes and display their number-----//
            for (int i = 0; i < ds.nodes; i++) 
            {           	
                x = (int) (ds.nodeX[i] * xscale);
                y = (int) (ds.nodeY[i] * yscale);                 
                if (i == 72 || i == 73)
                {                    
                    g.setColor(Color.BLACK);  	//Start- och slutpunkt
                    g.fillRect(x - ((circlesize+4)/2), height - y - (circlesize+4)/2, circlesize+4, circlesize+4);                                        
                }
                else
                {                   
                    g.setColor(Color.BLACK);     //Resten av punkterna 
                    g.fillRect(x - (circlesize / 2), height - y - circlesize / 2, circlesize, circlesize);
                }
                /*g.setColor(Color.WHITE);               
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString(""+(i+1), x - (circlesize / 2), height - y - circlesize / 2);*/              
            } 

            //-----Draw the robot-----//
            x = (int) (ds.robotX * xscale);
            y = (int) (ds.robotY * yscale);          
            //g.setColor(Color.GRAY);
            //g.fillRect(x - (circlesize-2), height - y - (circlesize-2), 2*(circlesize-2), 2*(circlesize-2));
            g.drawImage(robot, x - ((circlesize+22) / 2), height - y - (circlesize+22) / 2, circlesize+22, circlesize+22, this);            
            if(ds.indikator == true)
            {
                g.drawImage(red, x - ((circlesize+22) / 2), height - y - (circlesize+30) / 2, circlesize+22, circlesize+30, this);                
            }       
        }    
    }    
}

