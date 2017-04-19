package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        Scanner sf = new Scanner(new File("rawDataU6.txt"));

        ArrayList<String> data = new ArrayList();
        while(sf.hasNextLine())
        {
            data.add(sf.nextLine());
        }
        sf.close();

        //for(int i = 0; i<29; i++)
        //{
            int x = 1;
            String [] stuff = data.get(x).split("\t");
            //while(stuff[0].equals(i + ""))
            //{
                String [] q1 = stuff[1].split("\\+");
                double [] q1points = new double[q1.length-1];
                for(int p = 1; p < q1.length; p++)
                {
                    String [] q1parts = q1[p].split(" ");
                    String num = q1parts[p];
                    if(num.equals(".5"))
                    {
                        num = "0.5";
                    }
                    q1points[p-1] = Double.parseDouble(num);
                }
                double q1score = 0;
                for(int d = 0; d < q1points.length; d++)
                {
                    q1score+= q1points[d];
                }
                System.out.println(q1score);
                //x++;

            //}
        //}

    }
}
