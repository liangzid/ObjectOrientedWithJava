package homework2;

public class Apple2 extends Fruit implements Atio2 {

    public double get_weishengsu(){
        return this.getWeight()*APPLE_WEISHENGSU;
    }
    public double get_tang() {
        return this.getWeight()*APPLE_TANG;
    }
}
