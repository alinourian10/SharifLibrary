package model;

import enums.Gender;
import enums.Libraries;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person{
    private Libraries workPlace;
    private List<WeekDays> workingDays;

    public Employee(String fullName, int age,
                    long nationalCode, Gender gender, Libraries workPlace) {
        super(fullName, age, nationalCode, gender);
        this.workPlace = workPlace;
        workingDays = new ArrayList<>();
    }

    public Libraries getWorkPlace() {
        return workPlace;
    }

    public List<WeekDays> getWorkingDays() {
        return workingDays;
    }

    void updateWorkingDays(List<WeekDays> newSchedule) {
        workingDays = newSchedule;
    }
}