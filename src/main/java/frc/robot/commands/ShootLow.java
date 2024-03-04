package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


public class ShootLow extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 
  private Flapper m_flapper = null; 

  public ShootLow(Shooter s, Intake i) {
    m_shoot = s;
    m_intake = i;
    addRequirements(m_shoot);
    addRequirements(m_intake);
  }

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
    m_shoot.run(-.65, -.65);
    if (!m_flapper.equals(null)){
      m_flapper.run(0); //change
    }
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
    if (!m_flapper.equals(null)){
      m_flapper.run(0); //change
    }
  }

}
