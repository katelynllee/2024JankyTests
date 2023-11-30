// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.Constants;

public class DistAlignment extends CommandBase {
  /** Creates a new DistAlignment. */
  LimelightSubsystem limelight = new LimelightSubsystem();
  private double leftCommand;
  private double rightCommand;

  public DistAlignment(LimelightSubsystem limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
    addRequirements(limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  /**
  * updates distance commands
  */
  public void adjustDistance(){
    double yOffset = limelight.getYOffset();
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
  public double getDistLeftCommand(){
    return leftCommand;
  }

  /**
  * Get value of right command
  * @return value of rightCommand, double
  */

  public double getDistRightCommand(){
    return rightCommand;
  }

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    adjustDistance();
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
