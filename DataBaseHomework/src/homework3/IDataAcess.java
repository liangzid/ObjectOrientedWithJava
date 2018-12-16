package homework3;

import java.io.File;

public interface IDataAcess {
    public Object openOrCreate();
    public void closeOrNothingToDo();
    public StudentSet transDataToStudent();
    public void transStudentsToData(StudentSet sts);

}
