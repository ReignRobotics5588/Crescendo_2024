
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Drivetrain;

import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  private final DriveSubsystem m_drivetrain;
  private final int m_distance;
  private final double m_speed;
  private double m_rotation = 0; 
  private double origin;
  private double target;


  /*
   * Creates a new ExampleCommand.
   * d(distance) is inches
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem dd, int d, double s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = dd;
    m_distance = d;
    m_speed = s;

    addRequirements(m_drivetrain);
  }

  public DriveCommand(DriveSubsystem dd, int d, double s, double a){
    m_drivetrain = dd;
    m_distance = d;
    m_speed = s;
    m_rotation = a; 

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    origin = m_drivetrain.getMeanEncoderDistance();
    target = Math.signum(m_speed) * m_distance + origin;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get ChassisSpeeds arguments and move 
    // m_drivetrain.tankDrive(m_speed, m_speed);

    if (m_rotation > 0){

      double original = m_drivetrain.m_ahrs.getAngle(); 

      while(Math.abs(m_drivetrain.m_ahrs.getAngle() - original) < Math.abs(original)){
          m_drivetrain.arcadeDrive(m_speed, 0);
      }
      
    }

    m_drivetrain.tankDrive(m_speed, m_speed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double current = m_drivetrain.getMeanEncoderDistance();
    return Math.abs(current) >= target; 
  }
}