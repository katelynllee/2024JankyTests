// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class Drive extends CommandBase {
  ChassisSubsystem chassis;
  double leftJoystick;
  double rightJoystick;
  /** Creates subsystem values for command*/
  public Drive(ChassisSubsystem chassis, double leftJoystick, double rightJoystick) {
    this.chassis = chassis;
    this.leftJoystick = leftJoystick;
    this.rightJoystick = rightJoystick;
    addRequirements(chassis); //lets command know which subsystem it will be using
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassis.driveChassis(leftJoystick, rightJoystick);
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