/*package robotcontrol;

public class Nearest_Neighbour 
{
    DataStore ds;
    ControlUI cui;
    int[][] nn_matrix;
    int store_prio;
    
    public Nearest_Neighbour(DataStore ds, ControlUI cui)
    {
        this.ds = ds;
        this.cui =cui;
    }
    
    public int Choose_Maximum(int current_node)
    {
        int counter = 0;
        System.out.println(current_node);
        nn_matrix = new int[2][2];
        for (int i=0; i<ds.arcs; i++)
        {
           //System.out.print("arc start : " + ds.arcStart[74]);
           //System.out.print("\narc prio : " + ds.arcPrio[74]);
           if(ds.arcStart[i]==current_node && counter<2)
           {
                nn_matrix[counter][0]=ds.arcPrio[i];
                nn_matrix[counter][1]=i;
                for (int j=0;j<ds.nodeColor.length-1; j++)
                {
                    if(ds.arcStart[i] == ds.nodeColor[j] && ds.arcEnd[i] == ds.nodeColor[j+1])
                    {
                        store_prio = ds.arcPrio[i];
                        nn_matrix[counter][0] = 0;
                    }
                }
                counter++;
            }
        }
        if(nn_matrix[0][0]+nn_matrix[1][0] == 0)
        {
            nn_matrix[counter-1][0] = store_prio;
        }

        int max = 0;
        int maxid = 0;
        for (int i=0; i<2; i++)
        {
            /*for (int j=0; j<2;j++)
            {
                System.out.print(nn_matrix[i][j]+" ");
            }*/
            /*if (nn_matrix[i][0] > max)
            {
                max = nn_matrix[i][0];
                maxid = nn_matrix[i][1];
            }
        }
        return maxid;
    }
}*/


/*package robotcontrol;

public class Nearest_Neighbour 
{
    DataStore ds;
    ControlUI cui;
    int[][] nn_matrix;
    int[][] two_matrix;
    int store_prio;
    int store_prio_two[];
    int prios;
    //int store_j;
    
    public Nearest_Neighbour(DataStore ds, ControlUI cui)
    {
        this.ds = ds;
        this.cui =cui;
    }
    
    public int Choose_Maximum(int current_node)
    {
        int counter = 0;
        two_matrix = new int[4][3];
        int counter_two = 0;
        store_prio_two = new int[4];
        nn_matrix = new int[2][2];
        for (int i=0; i<ds.arcs; i++)
        {
            if(ds.arcStart[i]==current_node && counter<2)
            {
                nn_matrix[counter][0]=ds.arcPrio[i];
                nn_matrix[counter][1]=i;
                if(ds.arcEnd[i]==74)
                {
                    nn_matrix[counter][0]=0;
                }
                for (int j=0;j<ds.nodeColor.length-1; j++)
                {
                    if(ds.arcStart[i]==ds.nodeColor[j] && ds.arcEnd[i]==ds.nodeColor[j+1])
                    {
                        store_prio=ds.arcPrio[i];
                        //store_j=j;
                        nn_matrix[counter][0]=0;
                    }
                }
                //2
                for (int j=0; j<ds.arcs; j++)
                {
                    if(ds.arcStart[j]==ds.arcEnd[i] && counter_two<4)
                    {
                        two_matrix[counter_two][0]=ds.arcPrio[j]+nn_matrix[counter][0];
                        two_matrix[counter_two][1]=j;
                        two_matrix[counter_two][2]=i;
                        if(ds.arcEnd[j]==74)
                        {
                            two_matrix[counter_two][0]=0;
                        }   
                        for (int k=0;k<ds.nodeColor.length-1; k++)
                        {
                            if(ds.arcStart[j]==ds.nodeColor[k] && ds.arcEnd[j]==ds.nodeColor[k+1])
                            {
                                store_prio_two[counter_two]=ds.arcPrio[i];
                                two_matrix[counter_two][0]=nn_matrix[counter][0];;
                            }
                        }
                        counter_two++;
                    }
                }
                counter++;
            }
        }
    
        int sum_two=0;
        for (int i=0; i<4;i++)
        {
            sum_two=sum_two+two_matrix[i][0];
        }
        int max_two=0;
        int max_two_id=0;
        if(sum_two==0)
        {
            for (int i=0; i<4;i++)
            {
                if(store_prio_two[i]>max_two)
                {
                    max_two=store_prio_two[i];
                    max_two_id=i;
                }
            }
            two_matrix[max_two_id][0]=max_two;
        }
        /*
        int max = 0;
        int maxid = 0;
        for (int i=0; i<2; i++)
        {
            if (nn_matrix[i][0]>max)
            {
                max = nn_matrix[i][0];
                maxid=nn_matrix[i][1];
            }
        }
        */
        /*int maxi=0;
        int max_id_two=0;
        for (int i=0; i<4; i++)
        {
            if(two_matrix[i][0]>maxi)
            {
                maxi=two_matrix[i][0];
                max_id_two=two_matrix[i][2];
            }
        }
        return max_id_two;
    }
}*/
/*package robotcontrol;

public class Nearest_Neighbour 
{
    DataStore ds;
    ControlUI cui;
    int[][] nn_matrix;
    int[][] two_matrix;
    int[][] three_matrix;
    int[][] four_matrix;
    //int[][] five_matrix;
    int store_prio;
    int store_prio_two[];
    int store_prio_three[];
    int store_prio_four[];
    //int store_prio_five[];
    int prios;
    //int store_j;
    
    public Nearest_Neighbour(DataStore ds, ControlUI cui)
    {
        this.ds = ds;
        this.cui =cui;
    }
    
    public int Choose_Maximum(int current_node)
    {
        int counter = 0;
        two_matrix = new int[4][3];
        three_matrix = new int[8][4];
        four_matrix = new int[16][5];
        //five_matrix = new int[32][6];
        int counter_two = 0;
        int counter_three = 0;
        int counter_four = 0;
        //int counter_five = 0;
        store_prio_two = new int[4];
        store_prio_three = new int[8];
        store_prio_four = new int[16];
        //store_prio_five = new int[32];
        nn_matrix = new int[2][2];
        for (int i=0; i<ds.arcs; i++)
        {
            if(ds.arcStart[i]==current_node && counter<2)
            {
                nn_matrix[counter][0]=ds.arcPrio[i];
                nn_matrix[counter][1]=i;
                if(ds.arcEnd[i]==74)
                {
                    nn_matrix[counter][0]=0;
                }
                for (int j=0;j<ds.nodeColor.length-1; j++)
                {
                    if(ds.arcStart[i]==ds.nodeColor[j] && ds.arcEnd[i]==ds.nodeColor[j+1])
                    {
                        store_prio=ds.arcPrio[i];
                        //store_j=j;
                        nn_matrix[counter][0]=0;
                    }
                }
                //2
                for (int j=0; j<ds.arcs; j++)
                {
                    if(ds.arcStart[j]==ds.arcEnd[i] && counter_two<4)
                    {
                        two_matrix[counter_two][0]=ds.arcPrio[j]+nn_matrix[counter][0];
                        two_matrix[counter_two][1]=j;
                        two_matrix[counter_two][2]=i;
                        if(ds.arcEnd[j]==74)
                        {
                            two_matrix[counter_two][0]=0;
                        }   
                        for (int k=0;k<ds.nodeColor.length-1; k++)
                        {
                            if(ds.arcStart[j]==ds.nodeColor[k] && ds.arcEnd[j]==ds.nodeColor[k+1])
                            {
                                store_prio_two[counter_two]=ds.arcPrio[j];
                                two_matrix[counter_two][0]=nn_matrix[counter][0];;
                            }
                        }
                        
                        //3
                        for (int k=0; k<ds.arcs; k++)
                        {
                        if(ds.arcStart[k]==ds.arcEnd[j] && counter_three<8)
                        {
                        three_matrix[counter_three][0] =ds.arcPrio[k]+two_matrix[counter_two][0];
                        three_matrix[counter_three][1] = k;
                        three_matrix[counter_three][2] = j;
                        three_matrix[counter_three][3] = i;
                       
                        if (ds.arcEnd[k]==74)
                        {
                        three_matrix[counter_three][0]=0;
                        }
                       
                        for (int g=0; g<ds.nodeColor.length-1; g++)
                        {
                        if(ds.arcStart[k]==ds.nodeColor[g] && ds.arcEnd[k]==ds.nodeColor[g+1])
                        {
                        store_prio_three[counter_three]=ds.arcPrio[k];
                        three_matrix[counter_three][0]=two_matrix[counter_two][0];
                        }
                        }
                       
                       
                        //4
                        for (int g=0; g<ds.arcs; g++)
                        {
                        if (ds.arcStart[g]==ds.arcEnd[k] && counter_four<16)
                        {
                        four_matrix[counter_four][0]=ds.arcPrio[g]+three_matrix[counter_three][0];
                        four_matrix[counter_four][1]=g;
                        four_matrix[counter_four][2]=k;
                        four_matrix[counter_four][3]=j;
                        four_matrix[counter_four][4]=i;
                       
                        if (ds.arcEnd[g]==74)
                        {
                        four_matrix[counter_four][0]=0;
                        }
                       
                        for (int n=0; n<ds.nodeColor.length; n++)
                        {
                        if(ds.arcStart[g]==ds.nodeColor[n] && ds.arcEnd[g]==ds.nodeColor[n+1])
                        {
                        store_prio_four[counter_four]=ds.arcPrio[g];
                        four_matrix[counter_four][0]=three_matrix[counter_three][0];
                        }
                        }
                        counter_four++;
                        }
                        }
                       
                        counter_three++;
                        }
                        }
                        
                        
                        counter_two++;
                    }
                }
                counter++;
            }
        }
        
        /*
        int max = 0;
        int maxid = 0;
        for (int i=0; i<2; i++)
        {
            if (nn_matrix[i][0]>max)
            {
                max = nn_matrix[i][0];
                maxid=nn_matrix[i][1];
            }
        }
        */
    /*
        int sum_two=0;
        for (int i=0; i<4;i++)
        {
            sum_two=sum_two+two_matrix[i][0];
        }
        int max_two=0;
        int max_two_id=0;
        if(sum_two==0)
        {
            for (int i=0; i<4;i++)
            {
                if(store_prio_two[i]>max_two)
                {
                    max_two=store_prio_two[i];
                    max_two_id=i;
                }
            }
            two_matrix[max_two_id][0]=max_two;
        }
        
        int maxi=0;
        int max_id_two=0;
        for (int i=0; i<4; i++)
        {
            if(two_matrix[i][0]>maxi)
            {
                maxi=two_matrix[i][0];
                max_id_two=two_matrix[i][2];
            }
        }*/
        
        /*
        int sum_three=0;
        for(int i=0; i<8;i++)
        {
        sum_three=sum_three+three_matrix[i][0];
        }
        
         int max_three=0;
         int max_three_id=0;
         
         if(sum_three==0)
         {
        for (int i=0; i<8; i++)
        {
        if(store_prio_three[i]>max_three)
        max_three=store_prio_three[i];
        max_three_id = i;
        }
        three_matrix[max_three_id][0]=max_three;
         }
         
         max_three=0;
         max_three_id=0;
         
         for(int i=0; i<8; i++)
         {
        if(three_matrix[i][0]>max_three)
        {
        max_three=three_matrix[i][0];
        max_three_id=three_matrix[i][3];
        }
         }*/
         
         /*int sum_four=0;
         for(int i=0; i<16;i++)
         {
         	sum_four=sum_four+four_matrix[i][0];
         }


          
          int max_four=0;
          int max_four_id=0;
          int max_length=0;
          
          for(int i=0; i<16; i++)
          {
         	if(four_matrix[i][0]>max_four)
         	{
         	max_four=four_matrix[i][0];
         	max_four_id=four_matrix[i][4];
         	max_length=ds.arcLength[four_matrix[i][1]]+ds.arcLength[four_matrix[i][2]]+ds.arcLength[four_matrix[i][3]]+ds.arcLength[four_matrix[i][4]];
         	}
         	 
         	else if(four_matrix[i][0]==max_four)
         	{
         	if((ds.arcLength[four_matrix[i][1]]+ds.arcLength[four_matrix[i][2]]+ds.arcLength[four_matrix[i][3]]+ds.arcLength[four_matrix[i][4]])<max_length)
         	{
         	max_four=four_matrix[i][0];
            max_four_id=four_matrix[i][4];
            max_length=ds.arcLength[four_matrix[i][1]]+ds.arcLength[four_matrix[i][2]]+ds.arcLength[four_matrix[i][3]]+ds.arcLength[four_matrix[i][4]];
         	}
         	}
          }
          

          
          if(sum_four==0)
          {
         max_four_id=0;
         	//for (int i=0; i<16; i++)
         	// {
         	/* for (int j=0; j<ds.arcs; j++)
         	{
         	if(ds.arcColor[j]==0 && ds.arcEnd[j]!=74 && ds.arcPrio[j]>find_max)
         	{
         	find_max=ds.arcPrio[j];
         	max_node=ds.arcStart[j];
         	}
         	}*/
         	 
         	//Travel to the link with highest benefit that hasn't been traveled
         	 
         	 
         	/*
         	if(store_prio_four[i]>max_four)
         	max_four=store_prio_four[i];
         	max_four_id = i;*/
         	//}
         	//four_matrix[max_four_id][0]=max_four;
          /*}
    
        
        return max_four_id;
    }
}*/

package robotcontrol;

public class Nearest_Neighbour 
{
    DataStore ds;
    ControlUI cui;
    int[][] nn_matrix;
    int[][] two_matrix;
    int[][] three_matrix;
    int[][] four_matrix;
    int[][] five_matrix;
    int[][] six_matrix;
    int[][] seven_matrix;
    int[][] eight_matrix;
    int[][] nine_matrix;
    int[][] ten_matrix;
    int[][] eleven_matrix;
    int[][] twelwe_matrix;
    int[][] thirteen_matrix;
    int[][] fourteen_matrix;
    
    int sum=0;
    int next_link;
    int max;
    int max_id=0;
    //int store_j;
    
    public Nearest_Neighbour(DataStore ds, ControlUI cui)
    {
        this.ds = ds;
        this.cui =cui;
    }
    
    public int Choose_Maximum(int current_node)
    {
        int counter = 0;
        int counter_two = 0;
        int counter_three = 0;
        int counter_four = 0;
        int counter_five = 0;
        int counter_six = 0;
        int counter_seven = 0;
        int counter_eight = 0;
        int counter_nine = 0;
        int counter_ten = 0;
        int counter_eleven = 0;
        int counter_twelwe = 0;
        int counter_thirteen = 0;
        int counter_fourteen = 0;
        
        nn_matrix = new int[2][2];
        two_matrix = new int[4][3];
        three_matrix = new int[8][4];
        four_matrix = new int[16][5];
        five_matrix = new int[32][6];
        six_matrix = new int[64][7];
        seven_matrix = new int[128][8];
        eight_matrix = new int[256][2];
        nine_matrix = new int[512][2];
        ten_matrix = new int[1024][2];
        eleven_matrix = new int[2048][2];
        twelwe_matrix = new int[4096][2];
        thirteen_matrix = new int[8192][2];
        fourteen_matrix = new int[16000][2];
        
        for (int i=0; i<ds.arcs; i++)
        {
            if(ds.arcStart[i]==current_node)
            {
                nn_matrix[counter][0]=ds.arcPrio[i];
                nn_matrix[counter][1]=i;
                if(ds.arcEnd[i]==74)
                {
                    nn_matrix[counter][0]=0;
                }
                
                //2
                for (int j=0; j<ds.arcs; j++)
                {
                    if(ds.arcStart[j]==ds.arcEnd[i])
                    {
                        two_matrix[counter_two][0]=ds.arcPrio[j]+nn_matrix[counter][0];
                        two_matrix[counter_two][1]=i;
                        two_matrix[counter_two][2]=j;
                        if(ds.arcEnd[j]==74)
                        {
                            two_matrix[counter_two][0]=nn_matrix[counter][0];
                        }   
                        
                        
                        //3
                        
                        for (int k=0; k<ds.arcs; k++)
                        {
                            if(ds.arcStart[k]==ds.arcEnd[j] && ds.arcEnd[k]!=ds.nodeColor[ds.nodeColor.length-3])
                            {
                                three_matrix[counter_three][0]=ds.arcPrio[k]+two_matrix[counter_two][0];
                                three_matrix[counter_three][1]=i;
                                three_matrix[counter_three][2]=j;
                                three_matrix[counter_three][3]=k;
                                if(ds.arcEnd[k]==74)
                                {
                                	three_matrix[counter_three][0]=two_matrix[counter_two][0];
                                }
                               
                                
                                //4
                                for (int g=0; g<ds.arcs; g++)
                                {
                                    if(ds.arcStart[g]==ds.arcEnd[k])
                                    {
                                        four_matrix[counter_four][0]=ds.arcPrio[g]+three_matrix[counter_three][0];
                                        four_matrix[counter_four][1]=i;
                                        four_matrix[counter_four][2]=j;
                                        four_matrix[counter_four][3]=k;
                                        four_matrix[counter_four][4]=g;
                                        if(ds.arcEnd[g]==74)
                                        {
                                        	four_matrix[counter_four][0]=three_matrix[counter_three][0];
                                        } 
                                        
                                        //5
                                        for (int q=0; q<ds.arcs; q++)
                                        {
                                            if(ds.arcStart[q]==ds.arcEnd[g])
                                            {
                                                five_matrix[counter_five][0]=ds.arcPrio[q]+four_matrix[counter_four][0];
                                                five_matrix[counter_five][1]=i;
                                                five_matrix[counter_five][2]=j;
                                                five_matrix[counter_five][3]=k;
                                                five_matrix[counter_five][4]=g;
                                                five_matrix[counter_five][5]=q;
                                                if(ds.arcEnd[q]==74)
                                                {
                                                	five_matrix[counter_five][0]=four_matrix[counter_four][0];
                                                } 
                                              
                                                
                                                //6
                                                for (int w=0; w<ds.arcs; w++)
                                                {
                                                    if(ds.arcStart[w]==ds.arcEnd[q])
                                                    {
                                                        six_matrix[counter_six][0]=ds.arcPrio[w]+five_matrix[counter_five][0];
                                                        six_matrix[counter_six][1]=i;
                                                        six_matrix[counter_six][2]=j;
                                                        six_matrix[counter_six][3]=k;
                                                        six_matrix[counter_six][4]=g;
                                                        six_matrix[counter_six][5]=q;
                                                        six_matrix[counter_six][6]=w;
                                                        if(ds.arcEnd[w]==74)
                                                        {
                                                        	six_matrix[counter_six][0]=five_matrix[counter_five][0];
                                                        } 
                                                        
                                                        //7
                                                        for (int p=0; p<ds.arcs; p++)
                                                        {
                                                            if(ds.arcStart[p]==ds.arcEnd[w])
                                                            {
                                                                seven_matrix[counter_seven][0]=ds.arcPrio[p]+six_matrix[counter_six][0];
                                                                seven_matrix[counter_seven][1]=i;
                                                                seven_matrix[counter_seven][2]=j;
                                                                seven_matrix[counter_seven][3]=k;
                                                                seven_matrix[counter_seven][4]=g;
                                                                seven_matrix[counter_seven][5]=q;
                                                                seven_matrix[counter_seven][6]=w;
                                                                seven_matrix[counter_seven][7]=p;
                                                                if(ds.arcEnd[p]==74)
                                                                {
                                                                	seven_matrix[counter_seven][0]=six_matrix[counter_six][0];
                                                                } 
                                                                for (int y=0; y<ds.arcs; y++)
                                                                {
                                                                    if(ds.arcStart[y]==ds.arcEnd[p])
                                                                    {
                                                                        eight_matrix[counter_eight][0]=ds.arcPrio[y]+seven_matrix[counter_seven][0];
                                                                        eight_matrix[counter_eight][1]=i;
                                                                        
                                                                        if(ds.arcEnd[y]==74)
                                                                        {
                                                                        	eight_matrix[counter_eight][0]=seven_matrix[counter_seven][0];
                                                                        } 
                                                                        for (int o=0; o<ds.arcs; o++)
                                                                        {
                                                                            if(ds.arcStart[o]==ds.arcEnd[y])
                                                                            {
                                                                                nine_matrix[counter_nine][0]=ds.arcPrio[o]+eight_matrix[counter_eight][0];
                                                                                nine_matrix[counter_nine][1]=i;
                                                                                
                                                                                if(ds.arcEnd[o]==74)
                                                                                {
                                                                                	nine_matrix[counter_nine][0]=eight_matrix[counter_eight][0];
                                                                                } 
                                                                                for (int l=0; l<ds.arcs; l++)
                                                                                {
                                                                                    if(ds.arcStart[l]==ds.arcEnd[o])
                                                                                    {
                                                                                        ten_matrix[counter_ten][0]=ds.arcPrio[l]+nine_matrix[counter_nine][0];
                                                                                        ten_matrix[counter_ten][1]=i;
                                                                                        
                                                                                        if(ds.arcEnd[l]==74)
                                                                                        {
                                                                                        	ten_matrix[counter_ten][0]=nine_matrix[counter_nine][0];
                                                                                        } 
                                                                                        for (int aa=0; aa<ds.arcs; aa++)
                                                                                        {
                                                                                            if(ds.arcStart[aa]==ds.arcEnd[l])
                                                                                            {
                                                                                                eleven_matrix[counter_eleven][0]=ds.arcPrio[aa]+ten_matrix[counter_ten][0];
                                                                                                eleven_matrix[counter_eleven][1]=i;
                                                                                                
                                                                                                if(ds.arcEnd[aa]==74)
                                                                                                {
                                                                                                	eleven_matrix[counter_eleven][0]=ten_matrix[counter_ten][0];
                                                                                                } 
                                                                                                for (int ab=0; ab<ds.arcs; ab++)
                                                                                                {
                                                                                                    if(ds.arcStart[ab]==ds.arcEnd[aa])
                                                                                                    {
                                                                                                        twelwe_matrix[counter_twelwe][0]=ds.arcPrio[ab]+eleven_matrix[counter_eleven][0];
                                                                                                        twelwe_matrix[counter_twelwe][1]=i;
                                                                                                        
                                                                                                        if(ds.arcEnd[ab]==74)
                                                                                                        {
                                                                                                        	twelwe_matrix[counter_twelwe][0]=eleven_matrix[counter_eleven][0];
                                                                                                        } 
                                                                                                        for (int ac=0; ac<ds.arcs; ac++)
                                                                                                        {
                                                                                                            if(ds.arcStart[ac]==ds.arcEnd[ab])
                                                                                                            {
                                                                                                                thirteen_matrix[counter_thirteen][0]=ds.arcPrio[ac]+twelwe_matrix[counter_twelwe][0];
                                                                                                                thirteen_matrix[counter_thirteen][1]=i;
                                                                                                                
                                                                                                                if(ds.arcEnd[ac]==74)
                                                                                                                {
                                                                                                                	thirteen_matrix[counter_thirteen][0]=twelwe_matrix[counter_twelwe][0];
                                                                                                                } 
                                                                                                                for (int ad=0; ad<ds.arcs; ad++)
                                                                                                                {
                                                                                                                    if(ds.arcStart[ad]==ds.arcEnd[ac])
                                                                                                                    {
                                                                                                                        fourteen_matrix[counter_fourteen][0]=ds.arcPrio[ad]+thirteen_matrix[counter_thirteen][0];
                                                                                                                        fourteen_matrix[counter_fourteen][1]=i;
                                                                                                                        
                                                                                                                        if(ds.arcEnd[ad]==74)
                                                                                                                        {
                                                                                                                        	fourteen_matrix[counter_fourteen][0]=thirteen_matrix[counter_thirteen][0];
                                                                                                                        } 
                                                                                                                       
                                                                                                                        counter_fourteen++;
                                                                                                                    }
                                                                                                                }
                                                                                                                counter_thirteen++;
                                                                                                            }
                                                                                                        }
                                                                                                        counter_twelwe++;
                                                                                                    }
                                                                                                }
                                                                                                counter_eleven++;
                                                                                            }
                                                                                        }
                                                                                        counter_ten++;
                                                                                    }
                                                                                }
                                                                               
                                                                                counter_nine++;
                                                                            }
                                                                        }
                                                                        counter_eight++;
                                                                    }
                                                                }
                                                                counter_seven++;
                                                            }
                                                        }
                                                        
                                                        counter_six++;
                                                    }
                                                }
                                                
                                                counter_five++;
                                            }
                                        }
                                        
                                        counter_four++;
                                    }
                                }
                                
                                counter_three++;
                            }
                        }
                        counter_two++;
                    }
                }
                counter++;
            }
        }
    
        
        for (int i=0 ; i < 8192; i++){
        	
        	sum = sum + thirteen_matrix[i][0];
        	
        	//System.out.println("Rad kol 0 "+ i + " " +ten_matrix[i][0]);
        	//System.out.println("Rad kol 1 "+ i + " " +seven_matrix[i][1]);
        	
        }
        max=0;
        if(sum==0){
        	
        	next_link=0;
        	
        }
        
        else	
        {
        
        	for (int i=0 ; i < 8192; i++){
        		if(thirteen_matrix[i][0]>max)
        		{
        			max=thirteen_matrix[i][0];
        			max_id=thirteen_matrix[i][1];
        		}
        		//System.out.println("maxid "+ max_id);
        		
        	}
        	next_link=max_id;	
        }
        
        //System.out.println("next link:" +next_link);
         
    return next_link;    
    }
}