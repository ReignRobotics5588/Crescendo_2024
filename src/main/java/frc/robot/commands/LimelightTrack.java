package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.MathUtil;
import frc.robot.subsystems.*;
import frc.robot.Constants;

public class LimelightTrack extends Command{
    private final LimelightSubsystem m_LimelightSubsystem;
    private final Drivetrain m_DriveSubsystem;

    public LimelightTrack(LimelightSubsystem l, Drivetrain d) {
        m_LimelightSubsystem = l;
        m_DriveSubsystem = d;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(l, d);
      }

      @Override
      public void initialize() {}
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
          if(!m_LimelightSubsystem.hasTarget()){
            m_DriveSubsystem.tankDrive(0,0);//uhhhhh ask zach
          }
          else{
            System.out.print(m_LimelightSubsystem.getX());
            double speed = m_LimelightSubsystem.getX()*Constants.K_TURN;
            speed = MathUtil.clamp(speed, -1 * Constants.MAX_SPEED, Constants.MAX_SPEED);//speed, low, high
            m_DriveSubsystem.tankDrive(speed, -speed);//UHHHHHH ASK ZACH

          }
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {}
      
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return (m_LimelightSubsystem.getX() <= 1.5 && m_LimelightSubsystem.getX() >=-1.5);
      }//between -1.5->1.5


}