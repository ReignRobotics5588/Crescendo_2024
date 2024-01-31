// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static final int SMART_LIMIT = 80;
  public static final double k = .6;

  public static final class DriveConstants {

    public static final int kLeftMotorPort1 = 1;
    public static final int kLeftMotorPort2 = 4;
    public static final int kRightMotorPort1 = 2;
    public static final int kRightMotorPort2 = 3;

    public static final int kEncoderCPR = 1024; // update
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

  public static final class ClimberConstants{

    public static final int kClimberLeft = 22; 
    public static final int kClimberRight = 21; 

  }

  public static final class AutoConstants {

    public static final double autoDistance = 0.0; // inches 
    public static final double autoSpeed = 0.0; 

  }

  public static final class IntakeConstants {
    public static final int kMotorPort = 12;
    public static final int sensorPort = 6;
    public static final int lightPort = 7;
    public static final double intakeBeltSpeed = 1.2; 
  }



}
