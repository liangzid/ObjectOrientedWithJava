import java.util.ArrayList;

public class Stackk {
    ArrayList <Integer> stackk;

    Stackk(){
        stackk=new ArrayList<Integer>();
    }

    public void push(Integer a){
        stackk.add(a);
    }
    public int pop(){
        Integer a=stackk.get(stackk.size()-1);
        stackk.remove(stackk.size()-1);
        return a;
    }
}
