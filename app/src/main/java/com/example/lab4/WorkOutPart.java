package com.example.lab4;

import android.content.Intent;
import android.widget.EditText;

public class WorkOutPart extends WorkOutPartBase {

    public String workOutName;

    public int workOutTime = 0;

    public WorkOutPart(int workOutTime, String workOutName) {
        this.workOutTime = workOutTime;
        this.workOutName = workOutName;
    }

    @Override
    public String getWorkOutName() {
        return "Workout";
    } //"Workout"

    public int getWorkOutTime() {
        return workOutTime;
    }

}
