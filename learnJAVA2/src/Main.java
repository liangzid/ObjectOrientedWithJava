public class Main {
    public static void main(String args[]){
        //将数据压入堆
        Stackk st=new Stackk();
        for(Integer i=0;i<100;i++){
            st.push(i);
            System.out.println("已经压入堆栈"+ i);
        }


        Threads all_threads=new Threads();
        all_threads.setStack(st);
        //all_threads.NewUseToPop();
        //all_threads.NewUseToPop();
        //all_threads.NewUseToPop();
        all_threads.TongBuNewUseToPop();
        all_threads.TongBuNewUseToPop();
        all_threads.TongBuNewUseToPop();


    }
}
