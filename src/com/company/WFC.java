package com.company;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
public class WFC {
    static int[] Intersect(int[]m1,int[]m2){
        int k=0;
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2.length; j++) {
                if(m1[i]==m2[j]){
                    m1[k]=m1[i];
                    k++;
                }
            }
        }
        int[] m=new int[k];
        for (int i = 0; i < k; i++) {
            m[i]=m1[i];
        }
        return m;
    }
    /*private static int[] Combine(int[]m1,int[]m2){
        ArrayList<Integer>temp=new ArrayList<Integer>();
        for (int i = 0; i < m1.length; i++) {
            temp.add(m1[i]);
        }
        for (int i = 0; i < m2.length; i++) {
            temp.add(m2[i]);
        }
        int[]m=new int[temp.size()];
        for (int i = 0; i < m.length; i++) {
            m[i]=temp.get(i);
        }
        return m;
    }*/
    public static BufferedImage Transform(BufferedImage img){

        ArrayList<int[][]>filters=new ArrayList<int[][]>();
        ArrayList<Integer>palettet=new ArrayList<Integer>();
        for (int i = 0; i < img.getWidth()-1; i++) {
            for (int j = 0; j < img.getHeight()-1; j++) {
                if(!palettet.contains(img.getRGB(i,j))){ palettet.add(img.getRGB(i,j));}
                if(!filters.contains(new int[][]{{img.getRGB(i,j),img.getRGB(i+1,j)},{img.getRGB(i,j+1),img.getRGB(i+1,j+1)}})){filters.add(new int[][]{{img.getRGB(i,j),img.getRGB(i+1,j)},{img.getRGB(i,j+1),img.getRGB(i+1,j+1)}});}
                if(i+2==img.getWidth()){
                    if(!palettet.contains(img.getRGB(i+1,j))){ palettet.add(img.getRGB(i+1,j));}
                }
                if(j+2==img.getHeight()){
                    if(!palettet.contains(img.getRGB(i,j+1))){ palettet.add(img.getRGB(i,j+1));}
                }
                if(i+2==img.getWidth()&&j+2==img.getHeight()){
                    if(!palettet.contains(img.getRGB(i+1,j+1))){ palettet.add(img.getRGB(i+1,j+1));}
                }
            }
        }
        int[]palette=new int[palettet.size()];
        for (int i=0;i<palettet.size();i++){
            palette[i]=palettet.get(i);
        }
        int[][][]wfm=new int [img.getWidth()][img.getHeight()][];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                wfm[i][j]=palette.clone();
            }
        }
        Random r=new Random();
        for (int i = 0; i < (img.getHeight())*(img.getWidth()); i++) {
            int sx=r.nextInt(img.getWidth());
            int sy=r.nextInt(img.getHeight());
            if(wfm[sx][sy].length==1){
                i--;
            }else{
                wfm[sx][sy]=new int[]{wfm[sx][sy][r.nextInt(wfm[sx][sy].length)]};
                ArrayList<Integer>temp=new ArrayList<Integer>();
                for (int[][] j:filters) {
                    if(j[0][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][1])){temp.add(j[0][1]);}
                    }
                    if(j[1][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][1])){temp.add(j[1][1]);}
                    }
                }
                int[]tempr=new int[temp.size()];
                for (int j = 0; j < tempr.length; j++) {
                    tempr[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[0][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][0])){temp.add(j[0][0]);}
                    }
                    if(j[1][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][0])){temp.add(j[1][0]);}
                    }
                }
                int[]templ=new int[temp.size()];
                for (int j = 0; j < templ.length; j++) {
                    templ[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[1][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][0])){temp.add(j[0][0]);}
                    }
                    if(j[1][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][1])){temp.add(j[0][1]);}
                    }
                }
                int[]tempu=new int[temp.size()];
                for (int j = 0; j < tempu.length; j++) {
                    tempu[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[0][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][0])){temp.add(j[1][0]);}
                    }
                    if(j[0][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][1])){temp.add(j[1][1]);}
                    }
                }
                int[]tempd=new int[temp.size()];
                for (int j = 0; j < tempd.length; j++) {
                    tempd[j] = temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[0][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][0])){temp.add(j[1][0]);}
                    }
                }
                int[]tempur=new int[temp.size()];
                for (int j = 0; j < tempur.length; j++) {
                    tempur[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[1][1]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][0])){temp.add(j[0][0]);}
                    }
                }
                int[]tempul=new int[temp.size()];
                for (int j = 0; j < tempul.length; j++) {
                    tempul[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[0][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[1][1])){temp.add(j[1][1]);}
                    }
                }
                int[]tempdr=new int[temp.size()];
                for (int j = 0; j < tempdr.length; j++) {
                    tempdr[j]=temp.get(j);
                }
                temp.removeAll(temp);
                for (int[][] j:filters) {
                    if(j[1][0]==wfm[sx][sy][0]){
                        if(!temp.contains(j[0][1])){temp.add(j[0][1]);}
                    }
                }
                int[]tempdl=new int[temp.size()];
                for (int j = 0; j < tempdl.length; j++) {
                    tempdl[j]=temp.get(j);
                }
                if(sx<img.getWidth()-1){wfm[sx+1][sy]=Intersect(wfm[sx+1][sy],tempr);}
                if(sx>0){wfm[sx-1][sy]=Intersect(wfm[sx-1][sy],templ);}
                if(sy<img.getHeight()-1){wfm[sx][sy+1]=Intersect(wfm[sx][sy+1],tempd);}
                if(sy>0){wfm[sx][sy-1]=Intersect(wfm[sx][sy-1],tempu);}
                if(sx<img.getWidth()-1&&sy<img.getHeight()-1){wfm[sx+1][sy+1]=Intersect(wfm[sx+1][sy+1],tempdr);}
                if(sx<img.getWidth()-1&&sy>0){wfm[sx+1][sy-1]=Intersect(wfm[sx+1][sy-1],tempur);}
                if(sx>0&&sy<img.getHeight()-1){wfm[sx-1][sy+1]=Intersect(wfm[sx-1][sy+1],tempdl);}
                if(sx>0&&sy>0){wfm[sx-1][sy-1]=Intersect(wfm[sx-1][sy-1],tempul);}
            }
        }
        BufferedImage res=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                res.setRGB(i,j,wfm[i][j][0]);
            }
        }
        return res;
    }
}
