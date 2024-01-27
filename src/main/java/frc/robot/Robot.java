// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import frc.robot.subsystems.Shooter;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  private SparkPIDController m_LeftShooterPID;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    SmartDashboard.putNumber("Left RPM", m_robotContainer.m_Shooter.getLeftRPM());
    SmartDashboard.putNumber("Right RPM",m_robotContainer.m_Shooter.getRightRPM());

  // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    if((p != m_robotContainer.m_Shooter.kP)) {m_robotContainer.m_Shooter.m_leftPIDController.setP(p); m_robotContainer.m_Shooter.kP = p; }
    if((i != m_robotContainer.m_Shooter.kI)) {m_robotContainer.m_Shooter.m_leftPIDController.setI(i); m_robotContainer.m_Shooter.kI = i; }
    if((d != m_robotContainer.m_Shooter.kD)) {m_robotContainer.m_Shooter.m_leftPIDController.setD(d); m_robotContainer.m_Shooter.kD = d; }
    if((iz != m_robotContainer.m_Shooter.kIz)) {m_robotContainer.m_Shooter.m_leftPIDController.setIZone(iz); m_robotContainer.m_Shooter.kIz = iz; }
    if((ff != m_robotContainer.m_Shooter.kFF)) {m_robotContainer.m_Shooter.m_leftPIDController.setFF(ff); m_robotContainer.m_Shooter.kFF = ff; }
    if((max != m_robotContainer.m_Shooter.kMaxOutput) || (min != m_robotContainer.m_Shooter.kMinOutput)) {
      m_robotContainer.m_Shooter.m_leftPIDController.setOutputRange(min, max);
       m_robotContainer.m_Shooter.kMaxOutput = max; 
       m_robotContainer.m_Shooter.kMinOutput = min;
       }

    double setPoint = ShooterConstants.shooterTargetRPM;
    m_robotContainer.m_Shooter.m_leftPIDController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);

    SmartDashboard.putNumber("setPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_robotContainer.m_Shooter.getLeftRPM());


    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_LeftShooterPID.setP(p); kP = p; }
    if((i != kI)) { m_LeftShooterPID.setI(i); kI = i; }
    if((d != kD)) { m_LeftShooterPID.setD(d); kD = d; }
    if((iz != kIz)) { m_LeftShooterPID.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_LeftShooterPID.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      m_LeftShooterPID.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 


    m_LeftShooterPID.setReference(rotations, CANSparkMax.ControlType.kPosition);
    SmartDashboard.putNumber("SetPoint", rotations);
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

