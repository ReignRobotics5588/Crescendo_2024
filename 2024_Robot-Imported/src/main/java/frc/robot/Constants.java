// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static final int SMART_LIMIT = 80;
  public static final double k = .6;
  public static final class DriveConstants {
    public static final int kLeftMotorPort1 = 0;
    public static final int kLeftMotorPort2 = 1;

    public static final int kRightMotorPort1 = 2;
    public static final int kRightMotorPort2 = 3;

    public static final int[] kLeftEncoderPorts = {0, 1};
    public static final int[] kRightEncoderPorts = {2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = false;

    public static final int kRangeFinderPort = 6;
    public static final int kAnalogGyroPort = 1;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

  public static final class AutoConstants {
    public static final double kDistToBox1 = 0.10;
    public static final double kDistToBox2 = 0.60;

    public static final double kWristSetpoint = -45.0;
  }

  public static final class DriveStraightConstants {
    // these pid constants are not real, and will need to be tuned
    public static final double kP = 4.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
  }

}
