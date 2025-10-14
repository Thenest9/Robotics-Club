package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcontroller.external.samples.RobotAutoDriveByGyro_Linear.HEADING_THRESHOLD;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "Auton", group = "Autonomous")
public class Auton extends LinearOpMode
{

    DcMotor FrontLeft,FrontRight,RearLeft,RearRight;
    DcMotorEx outputRight, outputLeft;

    CRServo carousel;

    @Override
    public void runOpMode()
    {

        // Initialize drive motors
        FrontLeft = hardwareMap.get(DcMotor.class,"FrontLeft");// MOTOR 0
        FrontRight = hardwareMap.get(DcMotor.class,"FrontRight");// MOTOR 3
        RearLeft = hardwareMap.get(DcMotor.class,"RearLeft");// MOTOR 1
        RearRight = hardwareMap.get(DcMotor.class,"RearRight");// MOTOR 2

        outputRight = hardwareMap.get(DcMotorEx.class, "RightOutput");
        outputLeft = hardwareMap.get(DcMotorEx.class, "LeftOutput");

        carousel = hardwareMap.get(CRServo.class, "Carousel");

        outputRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//
        outputLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outputRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive())
        {
            driveBackward(0.5, 3000);     // Drive backward at 50% power for 3 seconds
            shoot(2000, 3000);               // Shoot at 2000 velocity for 3 seconds
        }
    }
    public void setMotorsPower(double fLSpeed,double fRSpeed,double rLSpeed,double rRSpeed)//function to set all motors to the same speed
    {
        //set the powers to drive the robot
        FrontLeft.setPower(fLSpeed);
        FrontRight.setPower(fRSpeed);
        RearLeft.setPower(rLSpeed);
        RearRight.setPower(rRSpeed);
    }
    // Helper to set power to all drive motors
    private void driveBackward(double power, int timeMillis)
    {
        setMotorsPower(power,-power,power,-power); // Positive power = backward
        sleep(timeMillis);//wait that much time
        setMotorsPower(0,0,0,0);//set the speed hack to 0
    }

    private void shoot(int velocity, int durationMillis)
    {
        outputRight.setVelocity(velocity);//the right output speed is set to 2000
        outputLeft.setVelocity(velocity);//the right output speed is set to 2000

        telemetry.addData("Shooter", "Firing at velocity: %d", velocity);
        telemetry.update();

        sleep(durationMillis);

        outputRight.setPower(0);
        outputLeft.setPower(0);

        telemetry.addData("Shooter", "Stopped");
        telemetry.update();

    }

}
