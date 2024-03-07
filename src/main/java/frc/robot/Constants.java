// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static final int SMART_LIMIT = 80;

  public static final double MAX_SPEED = 0.25;
  public static final double K_TURN = 0.25;

  public static final class DriveConstants {

    public static final int kFrontLeftMotorPort = 1;
    public static final int kBackLeftMotorPort = 3;

    public static final int kFrontRightMotorPort = 2;
    public static final int kBackRightMotorPort = 4;

    public static final int kEncoderCPR = 1024; // update
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

  public static final class ClimberConstants{

    public static final int kClimberLeft = 22; 
    public static final int kClimberRight = 21;
    public static double kClimberSpeedLimit =0.35; 

  }

  public static final class ShooterConstants{
    public static final int kLeftMotorPort = 16;
    public static final int kRightMotorPort = 17;

  }

  public static final class AutoConstants {

    public static final double autoDistance = 0.0; // inches 
    public static final double autoSpeed = 0.0; 

  }

  public static final class IntakeConstants {
    public static final int kRightMotorPort = 12;
    public static final int kLeftMotorPort = 11;
    public static final int sensorPort = 0;
    public static final int sensorPort2 = 1;
    public static final double intakeBeltSpeed = 1.2; 
    public static final int minRPM = 3000; 
  }

  public static final class FlapperConstants {
    public static final int kFlapperMotorPort = 6;
    public static final double kFlapperSpeed = 1;
  }



}
