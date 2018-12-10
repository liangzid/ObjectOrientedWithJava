package homework2;

public class Juzi2 extends Fruit implements Atio2 {
    public double get_weishengsu(){
        return this.getWeight()*JUZI_WEISHENGSU;
    }

    @Override
    public double get_tang() {
        return this.getWeight()*JUZI_TANG;
    }
}
