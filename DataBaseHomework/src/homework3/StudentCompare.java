package homework3;

import java.util.*;

public class StudentCompare implements Comparator {
    public int compare(Object x, Object y )
    {
        StudentPO xx=(StudentPO) y;
        StudentPO yy=(StudentPO) x;
        return xx.getStudentID().compareTo(yy.getStudentID());
        //比较的是两个字符串，如果小于的话返回负数（-1？），大于的话返回正数，等于返回0
    }

}
