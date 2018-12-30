import java.security.PublicKey;
import java.util.ArrayList;

public class Threads extends ArrayList<NewThread> {
    private Stackk stack;
    private int num_of_thread=0;

    public void NewUseToPush(Integer x){
        NewThread thread=new NewThread("thread 1") {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    System.out.println("已经将下列数字压入堆栈"+x);
                    stack.push(x);
                }
            }
        };
        this.add(thread);
    }
    public void NewUseToPop(){
        NewThread thread=new NewThread("thread "+num_of_thread) {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                System.out.println("已经弹出"+ stack.pop());
            }}
        };
        num_of_thread++;
         this.add(thread);
    }

    public void TongBuNewUseToPush(Integer x){
        NewThread thread=new NewThread("thread "+num_of_thread) {
            @Override
            public void run() {

                synchronized (stack){
                    for(int i=0;i<10;i++){
                        System.out.println("已经将下列数字压入堆栈"+x);
                    stack.push(x);
                    }
                }
            }
        };
        num_of_thread++;
        this.add(thread);
    }
    public void TongBuNewUseToPop(){
        NewThread thread=new NewThread("thread "+num_of_thread) {
            @Override
            public void run() {
                synchronized (stack) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("已弹出" + stack.pop());
                    }
                }
            }
        };
        num_of_thread++;
        this.add(thread);
}

    public void setStack(Stackk stack) {
        this.stack = stack;
    }
}
