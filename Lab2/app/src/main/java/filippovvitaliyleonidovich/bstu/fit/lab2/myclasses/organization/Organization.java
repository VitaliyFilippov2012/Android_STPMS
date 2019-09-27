package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization;

public enum Organization {
    BSTU("BSTU","Belarusian state technological university","5",1000),
    BSU("BSU","Belarusian state university","4", 900),
    BSUIR("BSUIR","Belarusian state university of informatics and Radioelectronics","5",1200);
    private String code;
    private String fullName;
    private String rating;
    private int numberOfStudent;

    Organization(String code,String fullName,String rating,int numberOfStudent){
        this.code = code;
        this.fullName = fullName;
        this.rating = rating;
        this.numberOfStudent = numberOfStudent;
    }

    public String getFullName(){
        return fullName;
    }

    public  String getCode(){
        return code;
    }

    public String getRating(){
        return rating;
    }

    public int getNumberOfStudent(){
        return numberOfStudent;
    }
}
