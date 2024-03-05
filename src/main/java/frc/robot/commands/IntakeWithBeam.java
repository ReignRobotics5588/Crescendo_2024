package frc.robot.commands;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;


public class IntakeWithBeam extends Command {
  // The subsystem the command runs on
  private final Shooter m_shoot;
  private final Intake m_intake; 
  private final double speed; 

  public IntakeWithBeam(Shooter s, Intake i, double ss) {
    m_shoot = s;
    m_intake = i;
    speed = ss; 
    addRequirements(m_shoot);
    addRequirements(m_intake);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
    // check 
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
    m_intake.runMotors(0.0); 
  }

}
