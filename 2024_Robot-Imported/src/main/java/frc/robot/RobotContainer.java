// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Autonomous;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();

  private final SendableChooser<Command> autoChooser; // added


  private final XboxController m_joystick = new XboxController(0);

  private final Command m_autonomousCommand =
      new Autonomous(m_drivetrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard

    SmartDashboard.putData(
        "Deliver Soda", new Autonomous(m_drivetrain));

    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new TankDrive(() -> -m_joystick.getLeftY(), () -> -m_joystick.getRightY(), m_drivetrain));

    // Show what command your subsystem is running on the SmartDashboard
    SmartDashboard.putData(m_drivetrain);

    // Configure the button bindings
    configureButtonBindings();
    
    // change name of auto
    autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    final JoystickButton dpadUp = new JoystickButton(m_joystick, 5);
    final JoystickButton dpadRight = new JoystickButton(m_joystick, 6);
    final JoystickButton dpadDown = new JoystickButton(m_joystick, 7);
    final JoystickButton dpadLeft = new JoystickButton(m_joystick, 8);
    final JoystickButton l2 = new JoystickButton(m_joystick, 9);
    final JoystickButton r2 = new JoystickButton(m_joystick, 10);
    final JoystickButton l1 = new JoystickButton(m_joystick, 11);
    final JoystickButton r1 = new JoystickButton(m_joystick, 12);
    }

    // Connect the buttons to commands
    // ex. dpadUp.onTrue(new SetElevatorSetpoint(0.25, m_elevator));

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
