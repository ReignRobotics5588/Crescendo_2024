package frc.robot.commands;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


public class ShootSpeaker extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 

  public ShootSpeaker(Shooter s, Intake i) {
    m_shoot = s;
    m_intake = i;
    addRequirements(m_shoot);
    addRequirements(m_intake);
  }
  @Override
    public void initialize(){
     m_intake.runMotors(0); 
    }


  @Override
  public void execute(){
    if (m_intake.sense()){
       while (m_shoot.getLeftRPM() < IntakeConstants.minRPM && m_shoot.getRightRPM() < IntakeConstants.minRPM) {
      m_shoot.run(-.65,-.65);
    }
    m_intake.runMotors(IntakeConstants.intakeBeltSpeed);
    }
   
  
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    m_shoot.run(0.0, 0.0);
  }

}
