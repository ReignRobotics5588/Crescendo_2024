package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

/** An example command that uses an example subsystem. */
public class DriveAndIntake extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;
  private final Intake m_Intake; 
  private double m_speed; 
  private double m_rotation; 
  
  /*
   * Creates a new ExampleCommand.
   * d(distance) is inches
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveAndIntake(Drivetrain dd, Intake i, double s, double r) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = dd;
    m_Intake = i; 
    m_speed = s; 
    m_rotation = r; 
    
    addRequirements(m_drivetrain);
    addRequirements(m_Intake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get ChassisSpeeds arguments and move
    m_Intake.runMotors(IntakeConstants.intakeBeltSpeed);

    m_drivetrain.arcadeDrive(m_speed, m_rotation);
  }

    // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_Intake.sense();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
    m_Intake.runMotors(0);
  }

}