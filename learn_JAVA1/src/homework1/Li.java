package homework1;

public class Li implements Atio {
    private double weight;


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double get_tang(){
        return this.weight*LI_TANG;
    }
    public double get_weishengsu(){
        return this.weight*LI_WEISHENGSU;
    }
}
