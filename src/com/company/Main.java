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
            int x = 17;
            //while(stuff[0].equals(i + ""))
            //{
                String [] stuff = data.get(x).split("\t");
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
                for(int f = 0; f < stuffs.size(); f++)
                {
                    if(stuffs.get(f).length() > 2)
                    {
                        if(!(stuffs.get(f).substring(1,2).equals("+")))
                        {
                            if(!(stuffs.get(f).substring(0,1).equals("+")))
                            {
                                stuffs.remove(f);
                                f--;
                            }
                        }
                    }
                }
                /*for(String b: stuffs)
                {
                    System.out.println(b);
                }*/
                for(int f = 0; f < stuffs.size(); f++)
                {
                    if(stuffs.get(f).substring(0,1).equals("\"") || stuffs.get(f).substring(0,1).equals("+"))
                    {
                        if(stuffs.get(f).substring(0,2).equals("\"+") || stuffs.get(f).substring(0,1).equals("+"))
                        {
                            for(int s = 1; s < stuffs.size()-f; s++)
                            {
                                if(stuffs.get(f+s).substring(0,1).equals("\"") || stuffs.get(f).substring(0,1).equals("+"))
                                {
                                    if(stuffs.get(f+s).substring(0,2).equals("\"+") || stuffs.get(f).substring(0,1).equals("+"))
                                    {
                                        stuffs.set(f, stuffs.get(f).substring(0,stuffs.get(f).length()-1) + ", " + stuffs.remove(f+s).substring(1));
                                        s--;
                                    }
                                }
                                else
                                {
                                    break;
                                }
                            }
                        }
                    }
                }
                for(String b: stuffs)
                {
                    System.out.println(b);
                }
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
                if(!(stuffs.get(2).substring(0,1).equals("\"")))
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
                if(stuffs.get(2).substring(0,1).equals("\""))
                {
                    q2start = 2;
                }
                String [] q2 = stuffs.get(q2start).split(", \\+");
                double [] q2points = new double[q2.length];
                for(int p = 0; p < q2.length; p++)
                {
                    String [] q2parts = q2[p].split(" ");
                    String num = q2parts[1];
                    q2points[p] = Double.parseDouble(num);
                }
                double q2score = 0;
                for(int d = 0; d < q2points.length; d++)
                {
                    q2score += q2points[d];
                }
                double q2synoff = 0;
                if(stuffs.size() == q2start+2)
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
