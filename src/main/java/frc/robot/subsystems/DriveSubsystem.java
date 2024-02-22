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
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.geometry.Pose2d; 
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

public class DriveSubsystem extends SubsystemBase {

  public static DifferentialDrive m_drive;

  private CANSparkMax frontLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax frontRightMotor = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax backLeftMotor = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(6, MotorType.kBrushless);

  private RelativeEncoder m_frontLeftEncoder = frontLeftMotor.getEncoder();
  private RelativeEncoder m_frontRightEncoder = frontRightMotor.getEncoder();
  private RelativeEncoder m_backRightEncoder = backRightMotor.getEncoder();
  private RelativeEncoder m_backLeftEncoder = backLeftMotor.getEncoder();

  
  public AHRS m_ahrs = new AHRS(SPI.Port.kMXP); // navx 

  public ChassisSpeeds m_speeds = new ChassisSpeeds(); // uhhhh 
  public DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(m_ahrs.getRotation2d(), getLeftEncoderDistance(), getRighttEncoderDistance());
  public Pose2d m_pose = new Pose2d(); 
  


  public DriveSubsystem() {

    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);
    backLeftMotor.setInverted(true);
    backRightMotor.setInverted(false);
    m_backLeftEncoder.setPositionConversionFactor(1.9);
    m_backRightEncoder.setPositionConversionFactor(1.9);
    m_frontLeftEncoder.setPositionConversionFactor(1.9);
    m_frontRightEncoder.setPositionConversionFactor(1.9);

    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);

    // ^ FIX: Making sure none of the motors are inverted, change when we figure out
    // WTH is up with the motors lol

    frontLeftMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    frontRightMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    backLeftMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    backRightMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);

    backLeftMotor.follow(frontLeftMotor);
    backRightMotor.follow(frontRightMotor);

    this.resetEncoders();

    // ???? Configure encoders here

    m_drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    // ((Object) m_drive).setRightSideInverted(false);
    m_drive.setMaxOutput(.80);
  }

  public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(speed * Constants.k, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    // May need invert left
    m_drive.tankDrive(leftSpeed * Constants.k, rightSpeed * Constants.k);

  }

  @Override
  public void periodic() {
    m_drive.feedWatchdog(); // check this

    // navx stuff
    SmartDashboard.putNumber("Yaw: ", m_ahrs.getYaw());
    SmartDashboard.putNumber("Roll: ", m_ahrs.getRoll());
    SmartDashboard.putNumber("Pitch: ", m_ahrs.getPitch());

    // pose stuff
    m_pose = m_odometry.update(m_ahrs.getRotation2d(), getLeftEncoderDistance(), getRighttEncoderDistance()); 

    

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

  public double getGyroAngle(){
    return m_ahrs.getAngle();
  }

  public double getGyroPitch(){
    return m_ahrs.getPitch();
  }

  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d p){
    m_odometry.resetPosition(m_ahrs.getRotation2d(), getLeftEncoderDistance(), getRighttEncoderDistance(), p);
  }

  // Configure AutoBuilder last
  /** 
  AutoBuilder.configureRamsete(
    this::getPose,
    this::resetEncoders, 
    this::getCurrentSpeeds, 
    this::Drive, 
    new ReplanningConfig(), // Default path replanning config. See the API for the options here
      () -> {
              // Boolean supplier that controls when the path will be mirrored for the red alliance
              // This will flip the path being followed to the red side of the field.
              // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                  return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
          },
      this // Reference to this subsystem to set requirements

  );
  */
}