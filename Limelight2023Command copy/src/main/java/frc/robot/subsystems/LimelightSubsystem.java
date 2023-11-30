// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
//import frc.robot.Constants.OperatorConstants;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new Limelight. */
  private NetworkTable limelightTable;
  private ShuffleboardTab visionTab = Shuffleboard.getTab("Limelight Values");
  private double xOffset, yOffset;

  public LimelightSubsystem() {
    configDashboard(visionTab);
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    updateOffsetValues();
  }

  /**
  * Adds camera stream and offset values to Shuffleboard
  * @param tab - Shuffleboard tab to add values to
  */
  public void configDashboard(ShuffleboardTab tab){
    tab.addCamera("Limelight Camera", "m_limelight", "http://10.19.67.11:5800/");
    tab.addDouble("Limelight xOffset", () -> limelightTable.getEntry("tx").getDouble(0.0));
    tab.addDouble("Limelight yOffset", () -> limelightTable.getEntry("ty").getDouble(0.0));
  }

  /**
  * Change pipeline from vision to driver and vice versa
  * @param isVision - whether pipeline is vision or not, boolean
  */
  public void setVisionMode(boolean isVision){
    if (isVision){
       limelightTable.getEntry("pipeline").setNumber(0);
    } else {
        limelightTable.getEntry("pipeline").setNumber(1);
    }
  }

  /**
  * Updates X and Y offset values from network table
  */
  public void updateOffsetValues(){
    xOffset = limelightTable.getEntry("tx").getDouble(0.0);
    yOffset = limelightTable.getEntry("ty").getDouble(0.0);
  }

  /**
  * Get value of xOffset
  * @return value of xOffset, double
  */
  public double getXOffset(){
    return xOffset;
  }
  
  /**
  * Get value of yOffset
  * @return value of yOffset, double
  */
  public double getYOffset(){
    return yOffset;
  }
  

@Override
public void periodic() {
  // This method will be called once per scheduler run
  
}
}
