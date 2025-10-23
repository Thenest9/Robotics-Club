
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Autonomous(name = "AutonWallStartBlue")

public class AutonWallStartBlue extends LinearOpMode
{

    DcMotor FrontLeft,FrontRight,RearLeft,RearRight;
    DcMotorEx outputRight, outputLeft;

    CRServo carousel;

    double motorSpeed = 0.2;

    @Override
    public void runOpMode()
    {

        // Initialize drive motors
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");// MOTOR 0
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");// MOTOR 3
        RearLeft = hardwareMap.get(DcMotor.class, "RearLeft");// MOTOR 1
        RearRight = hardwareMap.get(DcMotor.class, "RearRight");// MOTOR 2

        outputRight = hardwareMap.get(DcMotorEx.class, "RightOutput");
        outputLeft = hardwareMap.get(DcMotorEx.class, "LeftOutput");


        carousel = hardwareMap.get(CRServo.class, "Carousel");

        outputRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//
        outputLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outputRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive())
        {
            Shoot(1750,1500);
            sleep(1000);
            StrafeRight(3500);
        }
    }
    public void StrafeRight(int time)
    {


        //will move right


        telemetry.addData("joystick X:", gamepad1.left_stick_x);
        setMotorsPower(-motorSpeed,-motorSpeed,motorSpeed,motorSpeed);

        sleep(time);


        FrontLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        RearLeft.setPower(0.0);
        RearRight.setPower(0.0);



    }
    public void setMotorsPower(double fLSpeed,double fRSpeed,double rLSpeed,double rRSpeed)//function to set all motors to the same speed
    {
        //set the powers to drive the robot
        FrontLeft.setPower(fLSpeed);
        FrontRight.setPower(fRSpeed);
        RearLeft.setPower(rLSpeed);
        RearRight.setPower(rRSpeed);
    }
    public void StrafeLeft()
    {
        setMotorsPower(motorSpeed,motorSpeed,-motorSpeed,-motorSpeed);
        sleep(1000);
        FrontLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        RearLeft.setPower(0.0);
        RearRight.setPower(0.0);

    }
    public void TurnLeft()
    {
        setMotorsPower(motorSpeed, motorSpeed, motorSpeed, motorSpeed);
        sleep(1000);
        setMotorsPower(0,0,0,0);
        telemetry.update();
    }
    public void Shoot(int velocity, int durationMillis)
    {
        outputRight.setVelocity(velocity);//the right output speed is set to 2000
        outputLeft.setVelocity(velocity);//the left output speed is set to 2000
        sleep(durationMillis + 250); //ideally would check LED sensor

        carousel.setPower(-0.15);//rotate and shoot 1
        sleep(750);
        carousel.setPower(0.0);//stop carousel
        sleep(durationMillis);

        carousel.setPower(-0.15);//rotate and shoot 2
        sleep(1000);
        carousel.setPower(0.0);
        sleep(durationMillis);

        carousel.setPower(-0.15);//final shoot
        sleep(1000);
        carousel.setPower(0.0);//reset motors
        sleep(durationMillis);

        outputRight.setPower(0);
        outputLeft.setPower(0);

        telemetry.addData("Shooter", "Stopped");
    }
}

