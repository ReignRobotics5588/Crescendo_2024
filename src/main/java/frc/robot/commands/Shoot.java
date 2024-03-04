package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


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
    m_intake.run(0.7, m_shoot);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    m_shoot.run(0.0, 0.0);
    m_intake.run(0.0, m_shoot); 
  }

}
