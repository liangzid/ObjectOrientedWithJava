package homework1;


public class Apple implements Atio{
    private double weight;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double get_tang(){
        return this.weight*APPLE_TANG;
    }
    public double get_weishengsu(){
        return this.weight*APPLE_WEISHENGSU;
    }
}
