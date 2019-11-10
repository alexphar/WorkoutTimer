package com.example.lab4;

import android.content.Intent;
import android.widget.TextView;

public class PausePart extends WorkOutPartBase {

    public String workOutName;

    public int workOutTime = 0;

    public PausePart(int workOutTime, String workOutName) {
        this.workOutTime = workOutTime;
        this.workOutName = workOutName;
    }

    @Override
    public String getWorkOutName() {
        return "Pause";
    }

    public int getWorkOutTime() {
        return workOutTime;
    }
}
