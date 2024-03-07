package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


public class ShootLow extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 
  private final Flapper m_flapper; 

  public ShootLow(Shooter s, Intake i, Flapper f) {
    m_shoot = s;
    m_intake = i;
    m_flapper = f; 
    addRequirements(m_shoot);
    addRequirements(m_intake);
    addRequirements(m_flapper);
  }

  @Override
  public void initialize() {
    m_shoot.run(.65, .65);
    m_flapper.run(0); //change
    m_intake.runMotors(0.7);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    m_shoot.run(0.0, 0.0);
    m_intake.runMotors(0.0); 
    m_flapper.run(0); //change
  }

}
