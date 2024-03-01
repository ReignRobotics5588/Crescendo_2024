package frc.robot.Autos;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class Shoot extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 

  public Shoot(Shooter s, Intake i) {
    m_shoot = s;
    m_intake = i;
    addRequirements(m_shoot);
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_shoot.run(-.65, -.65);
    m_intake.run(0.7);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    m_shoot.run(0.0, 0.0);
    m_intake.run(0.0); 
  }

}
