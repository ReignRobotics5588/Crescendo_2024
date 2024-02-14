// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Drivetrain m_drivetrain = new Drivetrain();
  private final Intake m_Intake = new Intake();
  public final Shooter m_Shooter = new Shooter();
  private final Drivetrain m_Drivetrain = new Drivetrain();
  private final Climber m_Climber = new Climber(); 


  private final XboxController m_driverController = new XboxController(0);
  private final XboxController m_operatorController = new XboxController(1); 

  private final SendableChooser<Command> m_chooser;

  private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
  private final Command m_shortCommand = new DriveCommand(m_Drivetrain, 10, 0.2);
  private final Command m_longCommand = new DriveCommand(m_Drivetrain, 80, 0.7);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard

    m_chooser = new SendableChooser<>(); 

    m_chooser.setDefaultOption("Default", m_autonomousCommand); 
    m_chooser.addOption("Short", m_shortCommand);
    m_chooser.addOption("Long", m_longCommand);

    SmartDashboard.putData(m_chooser);

    // Assign default commands
    m_Drivetrain.setDefaultCommand(
        new RunCommand(() ->   m_Drivetrain.arcadeDrive( ( m_driverController.getRawAxis(1)*.75), ( m_driverController.getRawAxis(4)*.75)),
            m_Drivetrain)); 

    m_Climber.setDefaultCommand(
        new RunCommand(() -> m_Climber.setSpeed(m_operatorController.getRawAxis(1)*.5),m_Climber)); 
    
      // 0.52 percent power
      // 7 CAN ID forward
      //27 CAN ID backwards


    // Show what command your subsystem is running on the SmartDashboard
    //SmartDashboard.putData(m_drivetrain);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    final JoystickButton DRIVER_A_BUTTON_XBOX = new JoystickButton(m_driverController, 1);
    final JoystickButton DRIVER_B_BUTTON_XBOX = new JoystickButton(m_driverController, 2);
    final JoystickButton DRIVER_X_BUTTON_XBOX = new JoystickButton(m_driverController, 3);
    final JoystickButton DRIVER_Y_BUTTON_XBOX = new JoystickButton(m_driverController, 4);
    final JoystickButton DRIVER_lBumper = new JoystickButton(m_driverController, 5);
    final JoystickButton DRIVER_rBumper = new JoystickButton(m_driverController, 6);
    final JoystickButton DRIVER_backArrow = new JoystickButton(m_driverController, 7);
    final JoystickButton DRIVER_startArrow = new JoystickButton(m_driverController, 8);
    final JoystickButton DRIVER_joystickLeftClick = new JoystickButton(m_driverController, 9);
    final JoystickButton DRIVER_joystickRightClick = new JoystickButton(m_driverController, 10);

    final JoystickButton OPERATOR_A_BUTTON_XBOX = new JoystickButton(m_operatorController, 1);
    final JoystickButton OPERATOR_B_BUTTON_XBOX = new JoystickButton(m_operatorController, 2);
    final JoystickButton OPERATOR_X_BUTTON_XBOX = new JoystickButton(m_operatorController, 3);
    final JoystickButton OPERATOR_Y_BUTTON_XBOX = new JoystickButton(m_operatorController, 4);
    final JoystickButton OPERATOR_lBumper = new JoystickButton(m_operatorController, 5);
    final JoystickButton OPERATOR_rBumper = new JoystickButton(m_operatorController, 6);
    final JoystickButton OPERATOR_backArrow = new JoystickButton(m_operatorController, 7);
    final JoystickButton OPERATOR_startArrow = new JoystickButton(m_operatorController, 8);
    final JoystickButton OPERATOR_joystickLeftClick = new JoystickButton(m_operatorController, 9);
    final JoystickButton OPERATOR_joystickRightClick = new JoystickButton(m_operatorController, 10);
    
    //Intake
    //Shooter Speaker
    OPERATOR_B_BUTTON_XBOX.whileTrue(Commands.startEnd(()-> m_Shooter.run(-.50, -.50), ()->m_Shooter.run(0,0), m_Shooter));

    OPERATOR_Y_BUTTON_XBOX.whileTrue(Commands.startEnd(()-> m_Shooter.run(-.65, -.65), ()->m_Shooter.run(0,0), m_Shooter));
    OPERATOR_rBumper.whileTrue(Commands.startEnd(()-> m_Shooter.run(-.09, -.09), ()->m_Shooter.run(0,0), m_Shooter));
    
    DRIVER_lBumper.whileTrue(Commands.startEnd(()->m_Intake.run(0.7), ()->m_Intake.run(0), m_Intake));
    DRIVER_X_BUTTON_XBOX.whileTrue(Commands.startEnd(()->m_Intake.run(-.7), ()->m_Intake.run(0), m_Intake));
  
  }

    // Connect the buttons to commands
    // ex. dpadUp.onTrue(new SetElevatorSetpoint(0.25, m_elevator));

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  // check smartdashboard for auto here
  public Command getAutonomousCommand() { 
    return m_chooser.getSelected();
  }
};
