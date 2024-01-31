
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

import com.pathplanner.lib.auto.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  private final double driveinches;
  private final double drivespeed; 

  /*
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(double driveinches, double drivespeed, Drivetrain m_drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.driveinches = driveinches;
    this.drivespeed = drivespeed; 

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
        

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get ChassisSpeeds arguments and move 
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}