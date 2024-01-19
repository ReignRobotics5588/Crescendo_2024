// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Intake m_Intake = new Intake();

  private final XboxController m_driverController = new XboxController(0);

  private final Command m_autonomousCommand =
      new Autonomous(m_drivetrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard

    SmartDashboard.putData(
        "Deliver Soda", new Autonomous(m_drivetrain));

    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new TankDrive(() -> -m_driverController.getLeftY(), () -> -m_driverController.getRightY(), m_drivetrain));

    // Show what command your subsystem is running on the SmartDashboard
    SmartDashboard.putData(m_drivetrain);

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
    final JoystickButton A_BUTTON_XBOX = new JoystickButton(m_driverController, 1);
    final JoystickButton B_BUTTON_XBOX = new JoystickButton(m_driverController, 2);
    final JoystickButton X_BUTTON_XBOX = new JoystickButton(m_driverController, 3);
    final JoystickButton Y_BUTTON_XBOX = new JoystickButton(m_driverController, 4);
    final JoystickButton lBumper = new JoystickButton(m_driverController, 5);
    final JoystickButton rBumper = new JoystickButton(m_driverController, 6);
    final JoystickButton backArrow = new JoystickButton(m_driverController, 7);
    final JoystickButton startArrow = new JoystickButton(m_driverController, 8);
    final JoystickButton joystickLeftClick = new JoystickButton(m_driverController, 9);
    final JoystickButton joystickRightClick = new JoystickButton(m_driverController, 10);
    
  A_BUTTON_XBOX.whileTrue(Commands.startEnd(()-> m_Intake.run(IntakeConstants.intakeBeltSpeed), ()->m_Intake.run(0),m_Intake)); 
    
    }

    // Connect the buttons to commands
    // ex. dpadUp.onTrue(new SetElevatorSetpoint(0.25, m_elevator));

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}
