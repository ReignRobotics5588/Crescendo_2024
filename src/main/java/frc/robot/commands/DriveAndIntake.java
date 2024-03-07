
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
  private final double m_distance;
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
  public DriveAndIntake(Drivetrain dd, double d, double s, Intake i) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = dd;
    m_distance = d;
    m_speed = s;
    m_Intake = i;

    addRequirements(m_drivetrain);
    addRequirements(m_Intake);
  }

  public DriveAndIntake(Drivetrain dd, int d, double s, double a, Intake i){
    m_drivetrain = dd;
    m_distance = d;
    m_speed = s;
    m_rotation = a; 
    m_Intake = i;
    addRequirements(m_drivetrain);
    addRequirements(m_Intake);
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
    m_Intake.runMotors(IntakeConstants.intakeBeltSpeed);

    m_drivetrain.tankDrive(m_speed, m_speed);
    
  }

    // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double current = m_drivetrain.getMeanEncoderDistance() - origin;
    return Math.abs(current) >= target; 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
    m_Intake.runMotors(0);
  }

}