public abstract class NewThread extends Thread{

    NewThread(String name){
        super();
        System.out.println("New A Child thread:"+name);
        NewThreadStart();
    }

    public void NewThreadStart(){
        start();
    }


    public Stackk YiBu_push(Stackk stack, Integer x){
        stack.push(x);
        return stack;
    }
    public Stackk YiBu_pop(Stackk stack){
        stack.pop();
        return stack;
    }


    public Stackk TongBu_push(Stackk stack, Integer x){
        synchronized(stack){
            stack.push(x);
            return stack;
        }
    }
    public Stackk TongBu_pop(Stackk stack){
        synchronized (stack){
            stack.pop();
            return stack;
        }

    }

    public abstract void run();
}
