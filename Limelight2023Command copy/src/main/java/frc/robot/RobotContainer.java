// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Drive;
// import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj.XboxController.Button; 
// import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.commands.AngleAlignment;
import frc.robot.commands.DistAlignment;
import frc.robot.subsystems.LimelightSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private static final LimelightSubsystem limelight = new LimelightSubsystem();
  private static final ChassisSubsystem chassis = new ChassisSubsystem();

  public static final AngleAlignment angleAlignment = new AngleAlignment(limelight);
  public static final DistAlignment  distAlignment = new DistAlignment(limelight);

  private final Joystick leftJoystick = new Joystick(0);
  private final Joystick rightJoystick = new Joystick(1);
  
    

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  //Align Button for Limelight
  private final JoystickButton angleAlignButton = 
      new JoystickButton(leftJoystick, 9); //second value of constructor should be button number

  private final JoystickButton distAlignButton = 
      new JoystickButton(leftJoystick, 8); //second value of constructor should be button number


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    //put setDefaultCommand in constructor, not in configure bindings
    chassis.setDefaultCommand(new Drive(chassis, rightJoystick.getY(), leftJoystick.getY()));
    

  }
  
  /** 
  * assigns commands to button on controller
  */
  private void configureBindings() { 

    angleAlignButton.whileTrue(new Drive(chassis, angleAlignment.getAngleLeftCommand(), angleAlignment.getAngleRightCommand()));
    distAlignButton.whileTrue(new Drive(chassis, distAlignment.getDistLeftCommand(), distAlignment.getDistRightCommand()));

    /** 
    * Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    * cancelling on release.
    */
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    
    //cannot set another setDefaultCommand -> use whileTrue() function 
    //chassis.setDefaultCommand(new AngleAlignment(m_driverController.rightTrigger(0.5), limelight, chassis));
  }

  /**
   * Allows access of subystems in other subsystems 
   */
  // public static ChassisSubsystem getChassis() { 
  //   return chassis; 
  // }

  // public static LimelightSubsystem getLimelight() { 
  //   return limelight; 
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
