package homework2;

import java.util.ArrayList;
//这个是含有多态的主函数！！！
public class Main2 {
    public static void main(String args[]){
        Fruit apple1=new Apple2();
        Fruit apple2=new Apple2();
        Fruit apple3=new Apple2();
        apple1.setWeight(1.2);
        apple2.setWeight(2.2);
        apple3.setWeight(3.2);
        Fruit li1=new Li2();
        Fruit li2=new Li2();        li1.setWeight(1.1);
        li2.setWeight(1.3);
        Fruit juzi1=new Juzi2();
        juzi1.setWeight(3.0);


        FruitList basket=new FruitList();
        basket.add(apple1);
        basket.add(apple2);
        basket.add(apple3);
        basket.add(li1);
        basket.add(li2);
        basket.add(juzi1);
        System.out.print("这份果汁的含糖量为");
        System.out.println( basket.getTang());
        System.out.print("这份果汁的维生素含量为");
        System.out.println(basket.getWeishengdu());

    }
}
