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

    public static final int kEncoderCPR = 64; // update
    public static final double kWheelDiameterInches = 8;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;

    public static final double kTurnP = 1;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;

    public static final double kMaxTurnRateDegPerS = 100;
    public static final double kMaxTurnAccelerationDegPerSSquared = 300;

    public static final double kTurnToleranceDeg = 5;
    public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

    public static final boolean kGyroReversed = false;
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
     public static final int sensorPortOne = 0;
    public static final int sensorPortTwo = 1;
    public static final int lightPort = 7;
    public static final double intakeBeltSpeed = 0.7; 
    public static final int minRPM = 3000; 
  }

  public static final class FlapperConstants {
    public static final int kFlapperMotorPort = 6;
    public static final double kFlapperSpeed = 1;
  }



}
