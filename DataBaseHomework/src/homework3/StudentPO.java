package homework3;

public class StudentPO {
    private String studentID;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String institute;
    private String major;



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInstitute() {
        return institute;
    }

    public String getMajor() {
        return major;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public String getStudentID() {
        return studentID;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String toString() {
        return studentID+"   "+name+"   "+sex+"   "+institute+"   "+major+"   "+
                phone+"   "+email+"   ";
    }
}
