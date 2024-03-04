package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

public class ShootAutoFirst extends SequentialCommandGroup{
    //shoots first, drives across the baseline
    // start against side, drive at angle of certain distance so lined up with game distances and shot first
    // then pick up game piece and 
    public ShootAutoFirst(DriveSubsystem drivetrain1, Shooter shooter1, Intake intake1){

        addCommands(
            // drives first across baseline
            // 41.75 is baseline 
            new Shoot(shooter1, intake1),
            (Command) new DriveCommand(drivetrain1, 144, 0.7),
            (Command) new DriveCommand(drivetrain1, 0, 0.5, -90),
            (Command) new DriveCommand(drivetrain1, 144, 0.7),
            new Shoot(shooter1, intake1)       
            
        ); 
    }

    
}
