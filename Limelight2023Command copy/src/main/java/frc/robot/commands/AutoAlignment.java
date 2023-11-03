// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.LimelightSubsystem;
//import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;



public class AutoAlignment extends CommandBase {
  ChassisSubsystem chassis;
  LimelightSubsystem limelight;
  BooleanSupplier alignVal;
  boolean alignBool;

  double leftCommand, rightCommand;
  /** Creates a new AutoAlignment. */
  public AutoAlignment(BooleanSupplier alignVal, LimelightSubsystem limelight, ChassisSubsystem chassis) {
    this.alignVal = alignVal;
    alignBool = alignVal.getAsBoolean();
    this.limelight = limelight;
    this.chassis = chassis;
   
    addRequirements(limelight);
    addRequirements(chassis);
  }


   // Called when the command is initially scheduled.
   @Override
   public void initialize() {}
 
   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
     //use limelight values in chassis
    if (alignBool = true){
      leftCommand =  limelight.getLeftCommand();
      rightCommand = limelight.getRightCommand();
      
      chassis.Drive(leftCommand, rightCommand);
    }

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
