package com.saintsrobotics.corebot.output;

import java.util.ArrayList;
import java.util.List;

public class MotorGroup {
    
    private Motor[] motors;

    public MotorGroup(Motor... motors) {
        this.motors = motors;
    }

    public MotorGroup(MotorGroup... motorGroups) {
        List<Motor> motorList = new ArrayList<>();
        for (MotorGroup motorGroup : motorGroups) {
            for (Motor motor : motorGroup.motors) {
                if (!motorList.contains(motor)) {
                    motorList.add(motor);
                }
            }
        }
        motors = motorList.toArray(new Motor[0]);
    }
    
    public MotorGroup(String... webDashReferences){
    	List<Motor> motorList = new ArrayList<>();
    	for(String s : webDashReferences){
    		if (!motorList.contains(MotorsWebDashboard.get(s))) {
                motorList.add(MotorsWebDashboard.get(s));
            }
    	}
    	motors = motorList.toArray(new Motor[0]);
    }
    
    public void set(double speed) {
        for (Motor motor : motors) {
            motor.set(speed);
        }
    }
}
