

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

/** An example command that uses an example subsystem. */
public class TurnDrivetrain extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  private final Drivetrain m_drivetrain;
  private final double m_speed;
  private final double rotation; 
  private double origin;
  private double target;


  /*
   * Creates a new ExampleCommand.
   * Speed can be negative but distance must be magnitude in inches
   * @param subsystem The subsystem used by this command.
   */
  public TurnDrivetrain(Drivetrain DriveSubsystem, double s, double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = DriveSubsystem;
    m_speed = s;
    rotation = angle; 

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    origin = m_drivetrain.getAngle();
    target = origin + rotation;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get ChassisSpeeds arguments and move 
    if (rotation < 0){
        m_drivetrain.tankDrive(0, m_speed);
    }
    else {
        m_drivetrain.tankDrive(m_speed, 0);
    }
    
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
  
    if (m_speed > 0) {
      return current >= target;
    } else {
      return current <= target;
    }
    
  }

}