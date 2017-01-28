package com.saintsrobotics.corebot.tasks.autonomous;

import com.saintsrobotics.corebot.Robot;
import com.saintsrobotics.corebot.coroutine.RepeatingTask;

public class TurnToFaceVisionTargetTask extends RepeatingTask {

    @Override
    protected void doOnRepeat() {
        double[] centerXArr = Robot.visionTable.getNumberArray("centerX", new double[0]);
        double[] centerYArr = Robot.visionTable.getNumberArray("centerY", new double[0]);
        if (centerXArr.length == 2 && centerYArr.length == 2) {
            // no guarantee these are actually on the right and left
            double leftTargetX = centerXArr[0];
            double leftTargetY = centerYArr[0];
            double rightTargetX = centerXArr[1];
            double rightTargetY = centerYArr[1];

            double liftPositionX = (leftTargetX + rightTargetX)/2;
            double normalizedLiftPositionX = liftPositionX/Robot.cameraWidth;
            double relativeNormalizedLiftPositionX = normalizedLiftPositionX - 0.5;

            double maxMotorPower = 0.4;

            double motorPower = 2 * relativeNormalizedLiftPositionX / maxMotorPower;

            Robot.motors.rightMotors.set(motorPower);
            Robot.motors.leftMotors.set(-motorPower);
        }
    }
}