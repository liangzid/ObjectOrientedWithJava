package homework1;

import java.util.ArrayList;

//这个是不使用多态的文件夹！使用多态的请见homework2!!!!!!
public class Main1 {
    public static void main(String args[]){
        ArrayList<Apple> apps=new ArrayList<Apple>();
        ArrayList<Li> lis=new ArrayList<Li>();

        Apple apple1=new Apple();
        apple1.setWeight(1.0);
        Apple apple2=new Apple();
        apple1.setWeight(2.0);
        Apple apple3=new Apple();
        apple3.setWeight(3.0);
        apps.add(apple1);
        apps.add(apple2);
        apps.add(apple3);

        Li li1=new Li();
        Li li2=new Li();
        li1.setWeight(4.0);
        li2.setWeight(5.0);
        lis.add(li1);
        lis.add(li2);

        GuoZhi guozhi1=new GuoZhi();
        guozhi1.setApps(apps);
        guozhi1.setLis(lis);
        System.out.print("这种果汁的维生素含量为");
        System.out.println(guozhi1.get_weishengsu());
        System.out.print("这种果汁的糖分含量为");
        System.out.println(guozhi1.get_tang());

    }




}
