package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

public class Drive2Speeds extends Command{
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final double m_distance;
  private final double m_left_speed;
  private final double m_right_speed;
  private double origin;
  private double target; 
  private double target1;
  private double target2; 

  public Drive2Speeds(Drivetrain drivetrain, double distance, double speed1, double speed2) {
    m_drivetrain = drivetrain;
    m_distance = distance;
    m_left_speed = speed1;
    m_right_speed = speed2; 
  }

  @Override
  public void initialize() {
    origin = m_drivetrain.getMeanEncoderDistance();
    target1 = Math.signum(m_left_speed) * m_distance + origin; 
    target2 = Math.signum(m_right_speed) * m_distance + origin; 
    target = (target1 + target2) / 2; 
  }

  @Override
  public void execute() {
    // get ChassisSpeeds arguments and move 
    m_drivetrain.tankDrive(m_left_speed, m_right_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    double current = m_drivetrain.getMeanEncoderDistance();
  
    if (m_left_speed > 0) {
      return current >= target;
    } else {
      return current <= target;
    }
  }
    
}
