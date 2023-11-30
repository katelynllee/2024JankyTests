// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.LimelightSubsystem;
//import frc.robot.RobotContainer;
import frc.robot.Constants;



public class AngleAlignment extends CommandBase {
  private LimelightSubsystem limelight;
  // private BooleanSupplier alignVal;
  // private boolean alignBool;

  private double leftCommand, rightCommand;
  /** Creates a new AutoAlignment. */
  public AngleAlignment(LimelightSubsystem limelight) {
    // this.alignVal = alignVal;
    // alignBool = alignVal.getAsBoolean();
    this.limelight = limelight;
    //this.chassis = chassis;
   
    addRequirements(limelight);
    //addRequirements(chassis);
    
  }

  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

   /**
  * If outside of error range, offset values to angle
  */
  public void alignAngle(){
    double xOffset = limelight.getXOffset();
          
    double steeringAdjust = Constants.Vision.kP_AIMING * xOffset;
    leftCommand = 0.0;
    rightCommand = 0.0; 
    if (xOffset < -Constants.Vision.DEGREE_ERROR){ //if heading error is larger than allowed and is negative
      //need to turn right: left goes forward and right goes backward
      steeringAdjust -= Constants.Vision.MIN_COMMAND;
      leftCommand -= steeringAdjust;
      rightCommand += steeringAdjust;
          
    } else if (xOffset > Constants.Vision.DEGREE_ERROR){  //if heading error is larger than allowed and is positive
      //need to turn left: left goes backward and right goes forward
        steeringAdjust += Constants.Vision.MIN_COMMAND;
        leftCommand -= steeringAdjust;
        rightCommand += steeringAdjust;
    }
  }

  /**
  * Get value of left command
  * @return value of leftCommand, double
  */
  public double getAngleLeftCommand(){
    alignAngle();
    return leftCommand;
  }

  /**
  * Get value of right command
  * @return value of rightCommand, double
  */

  public double getAngleRightCommand(){
    alignAngle();
    return rightCommand;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  //alignAngle() //commented out because it doesn't make sesne to getAngleRightCommand before getting correct left/right command
  
  //use limelight values in chassis
  // if (alignBool = true){
  //   leftCommand =  limelight.getLeftCommand();
  //   rightCommand = limelight.getRightCommand();
      
  //   chassis.Drive(leftCommand, rightCommand);
  // }
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   return false;
  }


}
