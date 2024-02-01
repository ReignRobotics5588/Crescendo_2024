/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
*limelight
*/
package frc.robot.subsystems;

//import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;


public class Drivetrain extends SubsystemBase {

  public static DifferentialDrive m_drive;

  private CANSparkMax frontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotorPort, MotorType.kBrushless);
  private CANSparkMax backLeftMotor = new CANSparkMax(DriveConstants.kBackLeftMotorPort, MotorType.kBrushless);

  private CANSparkMax frontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotorPort, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(DriveConstants.kBackRightMotorPort, MotorType.kBrushless);



  private RelativeEncoder m_frontLeftEncoder = frontLeftMotor.getEncoder();
  private RelativeEncoder m_frontRightEncoder = frontRightMotor.getEncoder();
  private RelativeEncoder m_backRightEncoder = backRightMotor.getEncoder();
  private RelativeEncoder m_backLeftEncoder = backLeftMotor.getEncoder();

  AHRS m_ahrs = new AHRS(SPI.Port.kMXP);
  DifferentialDriveOdometry m_odometry;
  //may need to reset here

  Pose2d m_pose;

  public Drivetrain() {
    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);
    backLeftMotor.setInverted(true);
    backRightMotor.setInverted(false);
  
    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);

    // ^ FIX: Making sure none of the motors are inverted, change when we figure out
    // WTH is up with the motors lol

    frontLeftMotor.setSmartCurrentLimit(80);
    frontRightMotor.setSmartCurrentLimit(80);
    backLeftMotor.setSmartCurrentLimit(80);
    backRightMotor.setSmartCurrentLimit(80);

    backLeftMotor.follow(frontLeftMotor);
    backRightMotor.follow(frontRightMotor);
   
    frontLeftMotor.burnFlash();
    frontRightMotor.burnFlash();
    backLeftMotor.burnFlash();
    backRightMotor.burnFlash();
    
    this.resetEncoders();

    // ???? Configure encoders here

    m_drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    Rotation2d rotation2D = new Rotation2d((double)m_ahrs.getYaw());
    m_pose = new Pose2d();
    m_odometry = new DifferentialDriveOdometry(rotation2D, getLeftEncoderDistance(), getRighttEncoderDistance(), m_pose);
    // ((Object) m_drive).setRightSideInverted(false);
   
  }

  public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);

  }

  @Override
  public void periodic() {
    m_drive.feedWatchdog(); // check this
    Rotation2d currentRotation = new Rotation2d(m_ahrs.getYaw());
    m_pose = m_odometry.update(currentRotation,
    getLeftEncoderDistance(),
    getRighttEncoderDistance());

    SmartDashboard.putNumber("Odometry X : ", m_pose.getX()); // 
    SmartDashboard.putNumber("Odometry Y : ", m_pose.getY()); // 


  }

  public void resetEncoders() {
    m_frontLeftEncoder.setPosition(0.0);
    m_frontRightEncoder.setPosition(0.0);
    m_backLeftEncoder.setPosition(0.0);
    m_backRightEncoder.setPosition(0.0);
  }

  public double getMeanEncoderDistance() {
    // currently report leaders only
    return (getLeftEncoderDistance() + getRighttEncoderDistance()) / 2.0;
  }

  public double getLeftEncoderDistance() {

    // currently report leader only
    return m_frontLeftEncoder.getPosition();
  }

  public double getRighttEncoderDistance() {
    // currently report leader only
    return m_frontRightEncoder.getPosition();
  }

  // May want to try this rather than multiplying by constant scale everywhere
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
        
}
