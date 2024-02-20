
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  private final Drivetrain m_drivetrain;

  private final int m_distance;
  private final double m_speed;
  private double origin;
  private double target;


  /*
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(Drivetrain DriveSubsystem, int d, double s) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = DriveSubsystem;
    m_distance = d;
    m_speed = s;


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