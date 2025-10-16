package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "VvhsTeleop")
public class VvhsTeleop extends LinearOpMode
{


    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor RearLeft;
    DcMotor RearRight;
    DcMotorEx outputRight;
    // Hasn't been tested yet
    DcMotorEx outputLeft;
    // Hasn't been tested yet.
    CRServo leftIntake;


    CRServo rightIntake;


    CRServo carousel;


    //left clockwise
    //right counter


    double motorSpeed = 0.2;
    double outputMotorVelocity = 2000;


    @Override
    public void runOpMode()//initalizes all Motors and servos.
    {
        telemetry.addData("Initialize", "called");
        FrontLeft = hardwareMap.get(DcMotor.class,"FrontLeft");// MOTOR 0
        FrontRight = hardwareMap.get(DcMotor.class,"FrontRight");// MOTOR 3
        RearLeft = hardwareMap.get(DcMotor.class,"RearLeft");// MOTOR 1
        RearRight = hardwareMap.get(DcMotor.class,"RearRight");// MOTOR 2


        outputRight = hardwareMap.get(DcMotorEx.class, "RightOutput");
        outputLeft = hardwareMap.get(DcMotorEx.class, "LeftOutput");


        rightIntake = hardwareMap.get(CRServo.class, "IntakeRight");
        leftIntake = hardwareMap.get(CRServo.class, "IntakeLeft");


        carousel = hardwareMap.get(CRServo.class, "Carousel");
        // doesn't have a class yet


        outputRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//
        outputLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        outputRight.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()) {
            ChangeMotorPowerSpeed();
            ForwardMovement();
            BackwardMovement();
            StrafeLeft();
            StrafeRight();
            ServoMovement();
            TurnRight();
            TurnLeft();
            LaunchMotors();
            ChangeOutputMotorSpeed();
        }
    }

    public void ChangeMotorPowerSpeed()//changes MOTOR MOVEMENT Speed using M1 and M@
    {
        telemetry.addData("setPowerSpeed", "called");
        if(gamepad1.dpadUpWasPressed())//M1
        {
            telemetry.addData("dpad_up", "called");
            //If motor speed is less then 1 then increase by .1
            motorSpeed += motorSpeed < 0.9 ? 0.1 : 0;
        }
        if(gamepad1.dpadDownWasPressed())//M2
        {
            telemetry.addData("dpad_down", "called");
            //If motor speed is greater then -1 then decrease by .1
            motorSpeed += motorSpeed > 0.2 ? -0.1 : 0;
        }


        telemetry.addData("motor_speed", motorSpeed);
    }
    public void setMotorsPower(double fLSpeed,double fRSpeed,double rLSpeed,double rRSpeed)//function to set all motors to the same speed
    {
        FrontLeft.setPower(fLSpeed);
        FrontRight.setPower(fRSpeed);
        RearLeft.setPower(rLSpeed);
        RearRight.setPower(rRSpeed);
    }
    public void ForwardMovement()
    {
        if (gamepad1.left_stick_y < 0 && (gamepad1.left_stick_x>-0.4 && gamepad1.left_stick_x<0.4))// if gamepad1 left joystick is pushed up
        {
            telemetry.addData("Left Joy Stick Y", gamepad1.left_stick_y);


            //Move the robot in the forward direction
            setMotorsPower(-motorSpeed, motorSpeed, -motorSpeed, motorSpeed);
            //                 fL,    FR,             RL           Rr
        }
//        if (gamepad1.left_stick_y > 0.0 && gamepad1.left_stick_x < 0.0)//forward left diagonal
//        {
//            setMotorsPower(0.0, -motorSpeed, motorSpeed, 0.0);
//        }
//
//        if (gamepad1.left_stick_y > 0.0 && gamepad1.left_stick_x > 0.0)//forward right diagonal
//        {
//            setMotorsPower(motorSpeed, 0.0, 0.0, -motorSpeed);
//        }
        else
        {
            FrontLeft.setPower(0.0);
            FrontRight.setPower(0.0);
            RearLeft.setPower(0.0);
            RearRight.setPower(0.0);
        }
    }
    public void BackwardMovement()
    {
        if(gamepad1.left_stick_y > 0)// straight backward
        {
            telemetry.addData("Left Joy Stick -Y", "called");


            //Move the robot in the reverse direction
            setMotorsPower(motorSpeed,-motorSpeed,motorSpeed,-motorSpeed);
        }
//        if (gamepad1.left_stick_y < 0.0 && gamepad1.left_stick_x < 0.0)//Back left diagonal
//        {
//            setMotorsPower(-motorSpeed, 0.0, 0.0, motorSpeed);
//        }
//
//        if (gamepad1.left_stick_y < 0.0 && gamepad1.left_stick_x > 0)//Back right diagonal
//        {
//            setMotorsPower(0.0, motorSpeed, -motorSpeed, 0.0);
        //}// I love typing documentation
        else
        {
            FrontLeft.setPower(0.0);
            FrontRight.setPower(0.0);
            RearLeft.setPower(0.0);
            RearRight.setPower(0.0);
        }
    }
    public void StrafeLeft()//Commments are pog
    {
        //will turn left
        //turn left code


        telemetry.addData("joystick X:", gamepad1.left_stick_x);
        if(gamepad1.left_stick_x<0 && (gamepad1.left_stick_y<0.5 && gamepad1.left_stick_y>-0.5))
        {




            setMotorsPower(motorSpeed,motorSpeed,-motorSpeed,-motorSpeed);
        }
        else
        {
            FrontLeft.setPower(0.0);
            FrontRight.setPower(0.0);
            RearLeft.setPower(0.0);
            RearRight.setPower(0.0);


        }//This is a comment
    }
    public void StrafeRight()
    {


        //will move right
        if(gamepad1.left_stick_x>0)
        {
            telemetry.addData("joystick X:", gamepad1.left_stick_x);
            setMotorsPower(-motorSpeed,-motorSpeed,motorSpeed,motorSpeed);
//            setMotorsPower(-motorSpeed,-motorSpeed,motorSpeed,-motorSpeed); ROTATE RIGHT????
        }
        else {
            FrontLeft.setPower(0.0);
            FrontRight.setPower(0.0);
            RearLeft.setPower(0.0);
            RearRight.setPower(0.0);


        }
    }
    public void TurnRight()
    {
        if(gamepad1.right_stick_x>0)
        {
            telemetry.addData("joystick X:", gamepad1.right_stick_x);
            setMotorsPower(-motorSpeed, -motorSpeed, -motorSpeed, -motorSpeed);
        }
    }
    public void TurnLeft()
    {
        if(gamepad1.right_stick_x<0)
        {
            telemetry.addData("joystick X:", gamepad1.right_stick_x);
            setMotorsPower(motorSpeed, motorSpeed, motorSpeed, motorSpeed);
        }
    }


    public void ServoMovement()
    {
        // To do: Needs more power
//        if(gamepad1.triangle)//intake
//        {
//            // move to 0 degrees.
//            leftIntake.setPower(-100);
//            rightIntake.setPower(100);
//        }
//        if(gamepad1.square)//intake
//        {
//            // move to 0 degrees.
//            leftIntake.setPower(100);
//            rightIntake.setPower(-100);
//        }
        if(gamepad2.left_trigger>0.1)
        {
            carousel.setPower(-0.67);
        }
        else
        {
            leftIntake.setPower(0.0);
            rightIntake.setPower(0.0);
        }
    }


    public void LaunchMotors()
    {
        //outputRight.setVelocity(2800);
        if(gamepad2.right_trigger>0.4)
        {
            telemetry.addData("Shooting velo", outputRight.getVelocity());
            telemetry.addData("Shooting Motor Speed", outputRight.getPower());
            outputRight.setVelocity(outputMotorVelocity);
            outputLeft.setVelocity(outputMotorVelocity);
        }
        else
        {
            outputRight.setPower(0.0);
            outputLeft.setPower(0.0);
        }
    }


    public void ChangeOutputMotorSpeed()
    {
        if (gamepad1.right_bumper)
        {
            if (outputMotorVelocity < 2800)
            {
                outputMotorVelocity +=100;
                outputRight.setVelocity(outputMotorVelocity);
            }
            else
            {
                outputMotorVelocity = 2800;
            }
        }
        else if (gamepad1.left_bumper)
        {
            if (outputMotorVelocity > 0)
            {
                outputMotorVelocity = outputMotorVelocity - 100;
                outputRight.setVelocity(outputMotorVelocity);
            }
            else
            {
                outputMotorVelocity = 2800;
            }
        }
        else
        {
            outputMotorVelocity = 0.0;
        }
    }

    /* Checks if the current postion of the controller is inside the specified range according to the buffer
       posX: The position at which the x value of the controller should be
       posY: The position at which the y value of the controller should be
       bufferSize: The amount at which the controller position can be off
       Return Type: Boolean
    */
    public boolean checkingBounds(double posX,double posY,double bufferSize)
    {
        //Assigning variables for the x & y positions of the analog stick
        double gameStickX = gamepad1.right_stick_x;
        double gameStickY = gamepad1.right_stick_y;

        //Checks if the analog stick position is in the same quadrant as the targets positon
        if (Math.signum(gameStickX ) != Math.signum(posX) || Math.signum(gameStickY) != Math.signum(posY))
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
