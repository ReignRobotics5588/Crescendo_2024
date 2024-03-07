package frc.robot.commands;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


public class IntakeWithBeam extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 

  public IntakeWithBeam(Shooter s, Intake i) {
    m_shoot = s;
    m_intake = i;
    addRequirements(m_shoot);
    addRequirements(m_intake);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
    // check 
    
        m_intake.runMotors(IntakeConstants.intakeBeltSpeed);

  }

  @Override
  public boolean isFinished() {
    return m_intake.sense();
  }

  @Override
  public void end(boolean interrupted) {
    m_shoot.run(0.0, 0.0);
    m_intake.runMotors(0.0);
     
  }

}
