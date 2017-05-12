package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the text file name (including the .txt extension):");
        String filename = input.nextLine();
        System.out.println("Is the file separated by tabs or spaces? (enter \"tabs\" or \"spaces\")");
        String tors = input.nextLine();
        boolean spaces = false;
        if(tors.equalsIgnoreCase("spaces"))
        {
            spaces = true;
        }

        Scanner sf = new Scanner(new File("rawDataU6.txt"));

        ArrayList<String> data = new ArrayList();
        while(sf.hasNextLine())
        {
            data.add(sf.nextLine());
        }
        sf.close();

        double [] q1scoreavg = new double[28];
        double [] q2scoreavg = new double[28];

        int x = 1;
        for(int i = 1; i < 29; i++)
        {
            ArrayList<Double> q1scores = new ArrayList();
            ArrayList<Double> q2scores = new ArrayList();
            while(data.get(x).substring(0,1).equals(i + "") || data.get(x).substring(0,2).equals(i + ""))
            {
                String [] stuff = data.get(x).split("\t|\\s+\\s+\\s");
                ArrayList<String> stuffs = new ArrayList();
                for(String a: stuff)
                {
                    if(!(a.equals("")))
                    {
                        stuffs.add(a);
                    }
                }
                for(int f = 0; f < stuffs.size(); f++)
                {
                    if(stuffs.get(f).length() > 3)
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
                for(int f = 0; f < stuffs.size(); f++)
                {
                    if(stuffs.get(f).substring(0,1).equals("+"))
                    {
                        stuffs.set(f, "\"" + stuffs.get(f));
                    }
                }
                for(int f = 0; f < stuffs.size(); f++)
                {
                    if(stuffs.get(f).substring(0,1).equals("\""))
                    {
                        if(stuffs.get(f).substring(1,2).equals("+"))
                        {
                            for(int s = 1; s < stuffs.size()-f; s++)
                            {
                                if(stuffs.get(f+s).substring(0,1).equals("\"") && !(stuffs.get(f+s).substring(0,1).equals("0")))
                                {
                                    if(stuffs.get(f+s).substring(1,2).equals("+"))
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
                    double q1synoff = Double.parseDouble(stuffs.get(2)) * 0.25;
                    q1score -= q1synoff;
                }
                if(q1score < 0)
                {
                    q1score = 0;
                }
                q1scores.add(q1score);

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
                    q2synoff = Double.parseDouble(stuffs.get(q2start+1)) * 0.25;
                }
                q2score -= q2synoff;
                if(q2score < 0)
                {
                    q2score = 0;
                }
                q2scores.add(q2score);

                x++;
            }
            double q1avg = 0;
            for(double f: q1scores)
            {
                q1avg += f;
            }
            q1avg /= q1scores.size();
            q1scoreavg[i-1] = q1avg;

            double q2avg = 0;
            for(double f: q2scores)
            {
                q2avg += f;
            }
            q2avg /= q2scores.size();
            q2scoreavg[i-1] = q2avg;
        }

        Scanner sf2 = new Scanner(new File(filename));

        ArrayList<String> data2 = new ArrayList();
        while(sf2.hasNextLine())
        {
            data2.add(sf2.nextLine());
        }
        sf2.close();
        Collections.sort(data2);

        ArrayList<String> names = new ArrayList();
        ArrayList<String> secnums = new ArrayList();
        String delimiter = "\t";
        if(spaces)
        {
            delimiter = "\\s";
        }
        for(String a: data2)
        {
            String [] stuff = a.split(delimiter);
            names.add(stuff[0]);
            secnums.add(stuff[1]);
        }

        System.out.println("Name    Secret Number   Avg Total Grade FR Q1 Grade FR Q2 Grade");

        for(int i = 0; i < names.size(); i++)
        {
            String q1 = q1scoreavg[Integer.parseInt(secnums.get(i))-1] + "";
            if(q1.length() < 5)
            {
                q1 += "0";
            }
            String tabs = "\t";
            if(names.get(i).length() < 4)
            {
                tabs+="\t";
            }
            double avgtotal = q1scoreavg[Integer.parseInt(secnums.get(i))-1] + q2scoreavg[Integer.parseInt(secnums.get(i))-1];
            System.out.println(names.get(i) + tabs + secnums.get(i) + "\t\t\t\t" + avgtotal + " / 19" + "\t\t" + q1 + "\t\t" + q2scoreavg[Integer.parseInt(secnums.get(i))-1]);
        }
    }
}