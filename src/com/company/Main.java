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
            //while(stuff[0].equals(i + ""))
            //{
                String [] stuff = data.get(1).split("\t");
                ArrayList<String> stuffs = new ArrayList();
                for(String a: stuff)
                {
                    if(!(a.equals("")))
                    {
                        stuffs.add(a);
                    }
                }
                /*for(String b: stuffs)
                {
                    System.out.println(b);
                }*/
                String [] q1 = stuffs.get(1).split("\\+");
                double [] q1points = new double[q1.length-1];
                for(int p = 1; p < q1.length; p++)
                {
                    String [] q1parts = q1[p].split(" ");
                    String num = q1parts[1];
                    q1points[p-1] = Double.parseDouble(num);
                }
                double q1score = 0;
                for(int d = 0; d < q1points.length; d++)
                {
                    q1score += q1points[d];
                }
                if(stuffs.size()-1 == 2)
                {
                    double q1synoff = Integer.parseInt(stuffs.get(2)) * 0.25;
                    q1score -= q1synoff;
                }
                if(q1score < 0)
                {
                    q1score = 0;
                }
                System.out.println(q1score);

                int q2start = 3;
                if(!(stuffs.get(3).substring(0,1).equals("\"")))
                {
                    q2start = 4;
                }
                String [] q2 = stuffs.get(q2start).split("\\+");
                double [] q2points = new double[q2.length-1];
                for(int p = 1; p < q2.length; p++)
                {
                    String [] q2parts = q2[p].split(" ");
                    String num = q2parts[1];
                    q2points[p-1] = Double.parseDouble(num);
                }
                double q2score = 0;
                for(int d = 0; d < q2points.length; d++)
                {
                    q2score += q2points[d];
                }
                double q2synoff = 0;
                if(!(stuffs.size()-1 < q2start+1))
                {
                    q2synoff = Integer.parseInt(stuffs.get(q2start+1)) * 0.25;
                }
                q2score -= q2synoff;
                if(q2score < 0)
                {
                    q2score = 0;
                }
                System.out.println(q2score);
                //x++;

            //}
        //}

    }
}
