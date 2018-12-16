package homework3;

import java.util.Iterator;
import java.util.TreeSet;

public class StudentSet extends TreeSet<StudentPO> {
    public StudentSet(){
        super(new StudentCompare());
    }

    public String[][] get_list(){
        String alist[][]=new String[this.size()][7];
        Iterator<StudentPO> iter=this.iterator();
        int i=0;
        while(iter.hasNext())
        {
            alist[i][0]=iter.next().getStudentID();
            alist[i][1]=iter.next().getName();
            alist[i][2]=iter.next().getSex();
            alist[i][3]=iter.next().getPhone();
            alist[i][4]=iter.next().getEmail();
            alist[i][5]=iter.next().getInstitute();
            alist[i][6]=iter.next().getMajor();

            i++;
        }
        return alist;


    }









}
