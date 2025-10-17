package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ValuesTesting")
public class ValuesTesting extends OpMode
{

    public void init()
    {

    }
    @Override
    public void loop()
    {
        telemetry.addData("Right trigger:", gamepad1.right_trigger);
        telemetry.addData("Left Joystick:", gamepad1.left_stick_x+" , "+ gamepad1.left_stick_y);
        telemetry.addData("Right Joystick:", gamepad1.right_stick_x+" , "+ gamepad1.right_stick_x);
        //possibly add code to test velo

        telemetry.addData("checkingBounds:", checkingBounds(0.0,-1.0, 0.1));
        telemetry.update();


    }
    public boolean checkingBounds(double posX,double posY,double bufferSize)
    {
        //Assigning variables for the x & y positions of the analog stick
        float gameStickX = gamepad1.left_stick_x;
        float gameStickY = gamepad1.left_stick_y;
        telemetry.addData("checkingBounds vals", Math.signum(gameStickX) + " " + Math.signum(posX) + " " + Math.signum(gameStickY) + " " + Math.signum(posY));

        //Checks if the analog stick position is in the same quadrant as the targets positon
        if (Math.signum(gameStickX) != Math.signum(posX) || Math.signum(gameStickY) != Math.signum(posY))
        {
            return false;
        }

//Distance between the targets position and the analog sticks positon
        double dx = gameStickX - posX;
        double dy = gameStickY - posY;

//Use Pythagoras’ theorem to calculate the squared distance between the positions
//It allows you to find the disctance between the target position and the controller position
        double distanceSquared = dx * dx + dy * dy;

//Returns a boolean value if the analog positon is inside the targets positons ( including the buffer :) )
        return distanceSquared <= bufferSize * bufferSize;
    }
}