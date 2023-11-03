// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ChassisSubsystem extends SubsystemBase {
  private WPI_TalonSRX rightLeader, leftLeader, rightFollower, leftFollower;
  //private MotorControllerGroup rightSide, leftSide;
  private DifferentialDrive differentialDrive; 
  Joystick leftJoystick, rightJoystick; 
  
  public ChassisSubsystem() {
    this.rightLeader = new WPI_TalonSRX(0);
    this.leftLeader = new WPI_TalonSRX(1);
    this.rightFollower = new WPI_TalonSRX(2);
    this.leftFollower = new WPI_TalonSRX(3);
        
    rightLeader.setNeutralMode(NeutralMode.Coast);
    leftLeader.setNeutralMode(NeutralMode.Coast);
    rightFollower.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);

    leftLeader.setInverted(true);
    rightFollower.setInverted(true); //don't know which side it's inverted

    MotorControllerGroup rightSide = new MotorControllerGroup(rightLeader, rightFollower);
    MotorControllerGroup leftSide = new MotorControllerGroup(leftLeader, leftFollower);

    differentialDrive = new DifferentialDrive(rightSide, leftSide);
  }

  public void Drive(double leftSpeed, double rightSpeed){
    differentialDrive.tankDrive(Math.pow(leftSpeed,1), Math.pow(rightSpeed, 1));
  }



  // public void useLimelight(double leftSpeed, double rightSpeed){
  //   differentialDrive.tankDrive(Math.pow(leftSpeed,1), Math.pow(rightSpeed, 1));
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}