package homework2;

import java.util.ArrayList;

public class FruitList extends ArrayList<Fruit> {
    public double getWeishengdu(){
        double wss=0.0;
        for(Fruit fruit:this){
            wss+=fruit.get_weishengsu();
        }
        return wss;
    }
    public  double getTang(){
        double t=0;
        for(Fruit fruit:this){
            t+=fruit.get_tang();
        }
        return t;
    }
}
