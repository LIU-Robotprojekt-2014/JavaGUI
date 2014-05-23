package robotcontrol;
import java.lang.String.*;
import java.lang.Exception.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
public class ControlUI extends JFrame implements ActionListener
{
    DataStore ds;
    BluetoothTransceiver bt;
    RobotControlOne rc;
    Millis mi;
    Nearest_Neighbour nn;
    OptPlan op;
    JButton starta;
    String text4;
    public ControlUI(DataStore ds, RobotControlOne rc) 
    {
        //initComponents();  // The GUI code, generated by NetBeans is not called 
        this.ds = ds;
        this.rc = rc;
        /*this.mi = mi; 
        this.op = op;
        this.nn = nn;*/
        myinitComponents();
        setTitle("Plow-E (Grupp 3)"); 
    }     
    public void appendStatus(String text)
    {
        jTextArea1.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextArea1.append(text+"\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }
    public void appendtimer(String text)
    {
        jTextField4.setFont(new Font("ARIAL",Font.BOLD,24));
        jTextField4.setText(text+"\n");
        jTextField4.setCaretPosition(jTextField4.getDocument().getLength());
    }
    public void appendlength(String text)
    {
        jTextField5.setFont(new Font("ARIAL",Font.BOLD,24));
        jTextField5.setText(text+"\n");
        jTextField5.setCaretPosition(jTextField5.getDocument().getLength());
    }
    public void appendprio(String text)
    {
        jTextField11.setFont(new Font("ARIAL",Font.BOLD,24));
        jTextField11.setText(text+"\n");
        jTextField11.setCaretPosition(jTextField11.getDocument().getLength());
    }   
    public void appendsensor1(String text)
    {
        jTextField7.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextField7.setText(text+"\n");
        jTextField7.setCaretPosition(jTextField7.getDocument().getLength());
    }
    public void appendsensor2(String text)
    {
        jTextField8.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextField8.setText(text+"\n");
        jTextField8.setCaretPosition(jTextField8.getDocument().getLength());
    }
    public void appendsensor3(String text)
    {
        jTextField9.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextField9.setText(text+"\n");
        jTextField9.setCaretPosition(jTextField9.getDocument().getLength());
    }
    public void appendsensor4(String text)
    {
        jTextField10.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextField10.setText(text+"\n");
        jTextField10.setCaretPosition(jTextField10.getDocument().getLength());
    }
    
    public boolean getButtonState()
    { 
        return jToggleButton1.isSelected(); 
    }   
    public boolean getButtonState3()
    {
        return jToggleButton3.isSelected();
    }     
    public void actionPerformed(ActionEvent e) 
    {             
        /*String text = jTextField1.getText();
        //jTextArea1.append("K_P = "+text + '\n');
        appendStatus("K_P: "+text);
        jTextField1.selectAll();
        
        String text2 = jTextField2.getText();
        //jTextArea1.append("K_I = "+text2 + '\n');
        appendStatus("K_I = "+text2);
        jTextField2.selectAll();
        
        String text3 = jTextField3.getText();
        //jTextArea1.append("K_D = "+text3 + '\n');
        appendStatus("K_D = "+text3);
        jTextField3.selectAll();*/
        if(e.getSource() == jTextField4)
        {
            text4 = jTextField4.getText();       
            jTextField4.selectAll();        
            rc.opt();
        }        
        //if(e.getSource() == jButton5)
        //{
        //    ds.kommando = /*(char)0x01 + "C01|115:950:1\n";*/(char)0x01 + "m01|forward\n"/* + (char)0x0A*/;
        //}
        //if(e.getSource() == jButton6)
        //{
        //    ds.kommando = (char)0x01 + "m01|stop\n"/* + (char)0x0A*/;
       // }
        //if(e.getSource() == jButton7)
        //{
         //   ds.kommando = (char)0x01 + "m01|right\n"/* + (char)0x0A*/;
       // }
       // if(e.getSource() == jButton8)
        //{
         //   ds.kommando = (char)0x01 + "m01|left\n"/* + (char)0x0A*/;
        //} */       
        if(e.getSource() == jButton1)
        {
            ds.kommando = "Disconnect...";
            //appendStatus(""+ds.kommando);
        }
        if(e.getSource() == jButton2)
        {
            System.exit(0);
        }  
        if(e.getSource() == jButton3)
        {           
            rc.startt3();
        }  
        /*if(e.getSource() == jButton4)
        {
            appendStatus("Nu åker vi i "+text4+" sekunder!");
            //rc.opt();           
            //jTextField1.setText("");
            //jTextField2.setText("");
            //jTextField3.setText("");
        }  */      
    }  
    public void showStatus()
    {        
        jTextArea1.setFont(new Font("ARIAL",Font.BOLD,14));
        jTextArea1.append("______Plow-E (Grupp 3)______\n");
        jTextArea1.append("\n    Peter Fredriksson, KTS3   \n");
        jTextArea1.append("    Christoffer Gremlin, ED3   \n");
        jTextArea1.append("       Daniel Hedberg, ED3      \n");
        jTextArea1.append("          Victor Hjort, KTS3\n");
        jTextArea1.append("      Anton Jonasson, KTS3 \n");
        jTextArea1.append("       Simon Tafreshi, KTS3 \n");
        jTextArea1.append("  Karin Tyredal Wahlén, KTS3 \n");
        jTextArea1.append("         Niklas Wigertz, ED3 \n");
        jTextArea1.append("\n______Plow-E (Grupp 3)______\n");
    }   
    private void myinitComponents()
    {
        /* The code in the method is a "copy" of the code in the initComponents method.
         * The reason is that the code in the initComponents method can not be edited
         * since it is generated by the NetBeans GUI tool. Insted the generated code in
         * copied to the "duplicate" method where minor adjustments have been made, like
         * the jPanel1 variable is not of javax.swing.JPanel type, but my own MapPanel class.
         */
        String fonten = "ARIAL";
        //KOM IHÅG
        /*
        //Set fonts
        /*jButton1.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton2.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton3.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton4.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton5.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton6.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton7.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton8.setFont(new Font(""+fonten,Font.BOLD,11));
        jToggleButton1.setFont(new Font(""+fonten,Font.BOLD,48));
        jToggleButton3.setFont(new Font(""+fonten,Font.BOLD,11));
        jLabel5.setFont(new Font(""+fonten,Font.BOLD,14));
        jLabel7.setFont(new Font(""+fonten,Font.BOLD,14));
        //jLabel3.setFont(new Font(""+fonten,Font.BOLD,11));
        jLabel4.setFont(new Font(""+fonten,Font.BOLD,14));
        
        //Set listeners and colors
        jButton1.addActionListener(this);
        jButton1.setBackground(Color.GRAY);
        jButton1.setForeground(Color.BLACK);
        jButton2.addActionListener(this);
        jButton2.setBackground(Color.GRAY);
        jButton2.setForeground(Color.BLACK);
        jButton3.addActionListener(this);
        jButton3.setBackground(Color.GRAY);
        jButton3.setForeground(Color.BLACK);
        /*jButton4.addActionListener(this);
        jButton4.setBackground(Color.GRAY);
        jButton4.setForeground(Color.BLACK);
        jButton5.addActionListener(this);
        jButton5.setBackground(Color.GRAY);
        jButton5.setForeground(Color.BLACK);
        jButton6.addActionListener(this);
        jButton6.setBackground(Color.GRAY);
        jButton6.setForeground(Color.BLACK);
        jButton7.addActionListener(this);
        jButton7.setBackground(Color.GRAY);
        jButton7.setForeground(Color.BLACK);
        jButton8.addActionListener(this);
        jButton8.setBackground(Color.GRAY);
        jButton8.setForeground(Color.BLACK);
        jTextField5.addActionListener(this);
        jTextField7.addActionListener(this);
        jTextField8.addActionListener(this);
        jTextField9.addActionListener(this);
        jTextField10.addActionListener(this);
        jTextField11.addActionListener(this);
        jTextField4.addActionListener(this);
        jToggleButton1.setBackground(Color.GRAY);
        jToggleButton1.setForeground(Color.BLACK);
        jToggleButton3.setBackground(Color.GRAY);
        jToggleButton3.setForeground(Color.BLACK); 
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        jTextArea1.setForeground(Color.BLACK);         
        //Set borders
        jButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        /*jButton4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jToggleButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jToggleButton3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jSeparator1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField10.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField11.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));*/
        //setMinimumSize(new java.awt.Dimension(1366, 730));
        
        jPanel1 = new MapPanel(ds);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        
        jButton1.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton2.setFont(new Font(""+fonten,Font.BOLD,11));
        jButton3.setFont(new Font(""+fonten,Font.BOLD,11));
        jToggleButton1.setFont(new Font(""+fonten,Font.BOLD,48));
        jToggleButton3.setFont(new Font(""+fonten,Font.BOLD,11));
        jLabel5.setFont(new Font(""+fonten,Font.BOLD,14));
        jLabel7.setFont(new Font(""+fonten,Font.BOLD,14));
        //jLabel3.setFont(new Font(""+fonten,Font.BOLD,11));
        jLabel4.setFont(new Font(""+fonten,Font.BOLD,14));
        
        //Set listeners and colors
        jButton1.addActionListener(this);
        jButton1.setBackground(Color.GRAY);
        jButton1.setForeground(Color.BLACK);
        jButton2.addActionListener(this);
        jButton2.setBackground(Color.GRAY);
        jButton2.setForeground(Color.BLACK);
        jButton3.addActionListener(this);
        jButton3.setBackground(Color.GRAY);
        jButton3.setForeground(Color.BLACK);
        jTextField5.addActionListener(this);
        jTextField7.addActionListener(this);
        jTextField8.addActionListener(this);
        jTextField9.addActionListener(this);
        jTextField10.addActionListener(this);
        jTextField11.addActionListener(this);
        jTextField4.addActionListener(this);
        jToggleButton1.setBackground(Color.GRAY);
        jToggleButton1.setForeground(Color.BLACK);
        jToggleButton3.setBackground(Color.GRAY);
        jToggleButton3.setForeground(Color.BLACK); 
        jLabel4.setForeground(Color.BLACK);
        jLabel5.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        jTextArea1.setForeground(Color.BLACK);         
        //Set borders
        jButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jButton3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        
        jToggleButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jToggleButton3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jSeparator1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField10.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField11.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jTextField4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 350));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jToggleButton1.setText("GO!");

        jButton2.setText("Exit");

        jToggleButton3.setText("Error");

        jButton1.setText("D/C");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Connect");

        jLabel4.setText("Tid[s]:");

        jLabel5.setText("Längd[cm]:");

        jLabel7.setText("Priopoäng:");

        jLabel1.setText("<-Sensor->");

        jLabel2.setText("<-Sensor->");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(jTextField7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addComponent(jTextField9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jLabel7)))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
        
    }
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 350));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 350));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jToggleButton1.setText("GO!");

        jButton2.setText("Exit");

        jToggleButton3.setText("Error");

        jButton1.setText("D/C");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Connect");

        jLabel4.setText("Tid:");

        jLabel5.setText("Längd:");

        jLabel7.setText("Priopoäng:");

        jLabel1.setText("<-Sensor->");

        jLabel2.setText("<-Sensor->");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(jTextField7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addComponent(jTextField9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jLabel7)))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables

    private MapPanel map;

}
