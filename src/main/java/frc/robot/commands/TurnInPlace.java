package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class TurnInPlace extends Command 
{
    private static final double POSITION_PER_REV = (8 * 3.14159) / 42.0;//Depends on PCF
    private final Drivetrain m_driveSubsystem;
    private final double m_rotation;
    private final double m_speed;
    private double origin;
    private double target;

    //Rotation should be signed degrees, positive counter clockwise, speed should be positive
    public TurnInPlace(double rotation, double speed, Drivetrain drive)
    {

        m_rotation = rotation * POSITION_PER_REV;
        m_speed = Math.abs(speed);
        m_driveSubsystem = drive;
        addRequirements(m_driveSubsystem);
    }

    @Override
    public void initialize()
    {

        origin = m_driveSubsystem.getMeanEncoderDistance();
        target = m_rotation + origin;
    }

    @Override
    public void execute()
    {
        SmartDashboard.putNumber("DriveEncoder", m_driveSubsystem.getLeftEncoderDistance());
        m_driveSubsystem.tankDrive(m_speed, -m_speed);
    }

    @Override
    public void end(boolean interrupted)
    {
        m_driveSubsystem.tankDrive(0.0, 0.0);
        System.out.println(interrupted);
    }

    @Override
    public boolean isFinished()
    {
        double current = m_driveSubsystem.getLeftEncoderDistance();
        //System.out.format("%f %f %f", origin, current, target);
        System.out.println("Current: "+current);
        System.out.println("Target: "+target);
        if(m_rotation > 0)
        {
            return current >= target;
        }
        else
        {
            return current <= target;
        }
    }
}