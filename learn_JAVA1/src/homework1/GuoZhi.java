package homework1;

import java.util.ArrayList;

public class GuoZhi {
    private ArrayList<Apple> apps;
    private ArrayList<Li> lis;

    public ArrayList<Apple> getApps() {
        return apps;
    }

    public ArrayList<Li> getLis() {
        return lis;
    }

    public void setApps(ArrayList<Apple> apps) {
        this.apps = apps;
    }

    public void setLis(ArrayList<Li> lis) {
        this.lis = lis;
    }

    public double get_weishengsu(){
        double weishengsu1=0;
        double weishengsu2=0;
        for(Apple apple:this.apps){
            weishengsu1=weishengsu1+apple.get_weishengsu();
        }
        for(Li li:this.lis){
            weishengsu2 =weishengsu2+li.get_weishengsu();
        }
        return (weishengsu1+weishengsu2);
    }
    public double get_tang() {
        double tang1 = 0;
        double tang2 = 0;
        for (Apple apple : this.apps) {
            tang1 = tang1 + apple.get_tang();
        }
        for (Li li : this.lis) {
            tang2 = tang2 + li.get_tang();
        }
        return (tang1+tang2);
    }
}
