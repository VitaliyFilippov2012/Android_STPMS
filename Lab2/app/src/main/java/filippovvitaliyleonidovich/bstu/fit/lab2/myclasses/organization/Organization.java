package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Organization {

    BSTU("BSTU","Belarusian state technological university",5,1000),
    BSU("BSU","Belarusian state university",4, 900),
    BSUIR("BSUIR","Belarusian state university of informatics and Radioelectronics",4,1200);

    private String name;
    private String fullName;
    private int rating;
    private int numberOfStudent;

    Organization(String code, String fullName, int counStar, int numberOfStudent) {
    }
}
