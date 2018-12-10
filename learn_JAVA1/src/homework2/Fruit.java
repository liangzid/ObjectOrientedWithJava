package homework2;

public abstract class Fruit {
    private double weight;

    public abstract double get_weishengsu();
    public abstract double get_tang();

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
