package cg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Marlon
 * LISTA = PESSOA(VETOR(boolean,x,y,tempo)))
 * POSICAO 0 = BOOLEAN
 * POSICAO 1 = X
 * POSICAO 2 = Y
 * POSICAO 3 = TEMPO
 */


public class CG1 {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<ArrayList<ArrayList<Double>>> list = new ArrayList();
        String path = "C:\\Users\\Marlon\\Desktop\\CG\\dados\\Paths_D.txt";
        double m = reader(path,list);
        System.out.println(distanceNspeed(m,list));
        System.out.println(loneliest(m,list));
    }
    
    // the loneliest person along the frames
    public static String loneliest(double m, ArrayList<ArrayList<ArrayList<Double>>> list){
        String loneliest = "";
        double loneliestNum = 0;
        int size = list.size();
        String ret = "";
        for(int i = 0; i < size; i++){ // for each person
            double dist = 0;
            double x1,x2,y1,y2,time1,time2;
            int size2 = list.get(i).size();
            for(int i2 = 0; i2 < size2; i2++){ // for each vector
                x1 = list.get(i).get(i2).get(1);
                y1 = list.get(i).get(i2).get(2);
                time1 = list.get(i).get(i2).get(3);
                for(int j = 0; j < size; j++){ // for each other person
                    if(j == i) continue; // different person..
                    int size3 = list.get(j).size();
                    for(int j2 = 0; j2 < size3; j2++){ // for each vector
                        time2 = list.get(j).get(j2).get(3);
                        if(time1 != time2) continue;
                        x2 = list.get(j).get(j2).get(1);
                        y2 = list.get(j).get(j2).get(2);
                        dist += Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                    }
                }
            }
            dist /= m;
            double round = Math.round(dist);
            ret += "Person "+(i+1)+": Total Distance from Others Persons: "+round+" meters (real: "+dist+" meters)\n";
            if(dist > loneliestNum){
                loneliestNum = dist;
                loneliest = "Loneliest Person Is "+"Person "+(i+1)+": Total Distance from Others Persons: "+round+" meters (real: "+dist+" meters)\n";;
            }
        }
        return ret+"\n"+loneliest;
    }
    
    public static String distanceNspeed(double m, ArrayList<ArrayList<ArrayList<Double>>> list){
        int size = list.size();
        String ret = "";
        for(int j = 0; j < size; j++){
            double dist = 0;
            double x1,x2,y1,y2;
            x1 = list.get(j).get(0).get(1);
            y1 = list.get(j).get(0).get(2);
            x2 = list.get(j).get(1).get(1);
            y2 = list.get(j).get(1).get(2);
            dist = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
            int i = 2;
            int size2 = list.get(j).size();
            while(size2 > i){
                x1 = x2;
                y1 = y2;
                x2 = list.get(j).get(i).get(1);
                y2 = list.get(j).get(i).get(2);
                dist += Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                i++;
            }
            dist /= m;
            double round = Math.round(dist);
            double speed = dist/size2;
            ret += "Distance: Person "+(j+1)+": "+round+" meters (real: "+dist+" meters) | Speed: "+speed+" meters/frames\n";
        }
        return ret;
    }
    
    public static double reader(String path, ArrayList<ArrayList<ArrayList<Double>>> list) throws FileNotFoundException, IOException{
        File file = new File(path);
        Scanner scan = new Scanner(file);
        scan.useDelimiter("\\D|\\n|\\t");
        double m = nextNum(scan);
        while(scan.hasNext()){
            ArrayList<ArrayList<Double>> person = new ArrayList();
            for(double i = nextNum(scan); i > 0; i--){
                ArrayList<Double> v = new ArrayList(4);
                v.add((double) 0);
                v.add(nextNum(scan)); // X
                v.add(nextNum(scan)); // Y
                v.add(nextNum(scan)); // TEMPO
                person.add(v);
            }
            if(!person.isEmpty()) list.add(person);
        }
        scan.close();
        return m;
        }
    
    public static double nextNum(Scanner scan){
        while(scan.hasNext()){
            if (scan.hasNextInt()) return scan.nextInt();
            else scan.next();
        }
        return 0;
    }
    
}
