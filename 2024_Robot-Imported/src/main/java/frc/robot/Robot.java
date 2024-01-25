// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.Console;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  private Shooter m_shooter; 

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_shooter = new Shooter();
    CANSparkFlex leftmotor= m_shooter.getLeftMotor();
    m_LeftShooterPID = leftmotor.getPIDController();

    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    m_LeftShooterPID.setP(kP);
    m_LeftShooterPID.setI(kI);
    m_LeftShooterPID.setD(kD);
    m_LeftShooterPID.setIZone(kIz);
    m_LeftShooterPID.setFF(kFF);
    m_LeftShooterPID.setOutputRange(kMinOutput, kMaxOutput);
    
    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);


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

