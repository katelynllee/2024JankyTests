// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
//import frc.robot.Constants.OperatorConstants;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new Limelight. */
  private NetworkTable limelightTable;
  private double xOffset, yOffset, leftCommand, rightCommand;

  public LimelightSubsystem() {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void configDashboard(ShuffleboardTab tab){
    tab.addCamera("Limelight Camera", "m_limelight", "http://10.19.67.11:5800/");
    tab.addDouble("Limelight xOffset", () -> limelightTable.getEntry("tx").getDouble(0.0));
    tab.addDouble("Limelight yOffset", () -> limelightTable.getEntry("ty").getDouble(0.0));
  }

  public void setVisionMode(boolean isVision){
    if (isVision){
       limelightTable.getEntry("pipeline").setNumber(0);
    } else {
        limelightTable.getEntry("pipeline").setNumber(1);
    }
  }


  public void updateValues(){
    xOffset = limelightTable.getEntry("tx").getDouble(0.0);
    yOffset = limelightTable.getEntry("ty").getDouble(0.0);
  }

  public void alignAngle(){
    updateValues();
          
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

  public void adjustDistance(){
    updateValues();
    double distanceAdjust = Constants.Vision.kP_AIMING * yOffset;
    leftCommand = 0.0;
    rightCommand = 0.0; 

    leftCommand -= distanceAdjust;
    rightCommand -= distanceAdjust; 
  }
    
  /**
  * Get value of left command
  * @return value of leftCommand, double
  */
  public double getLeftCommand(){
    return leftCommand;
  }

    /**
    * Get value of right command
    * @return value of rightCommand, double
    */
  public double getRightCommand(){
      return rightCommand;
  }


@Override
public void periodic() {
  // This method will be called once per scheduler run
  
}
}
