package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.educationmanager.Manager;

public class Staff {
    private int cursId;
    Manager manager;

    public int getCursId() {
        return cursId;
    }

    public void setCursId(int cursId) {
        this.cursId = cursId;
    }

    private void setCursId() {
        cursId = super.hashCode();
    }

    Staff(){
        setCursId();
    }
}
