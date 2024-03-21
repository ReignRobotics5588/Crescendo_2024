package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveWithTurn;
import frc.robot.commands.ShootSpeaker;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

public class ShootCrossBlue extends SequentialCommandGroup{
    
    public ShootCrossBlue(Drivetrain drivetrain, Shooter shooter, Intake intake){

        addCommands(
            new ShootAuto(drivetrain, shooter, intake), 
            Commands.startEnd(()->drivetrain.tankDrive(0.3, 0.6), ()->drivetrain.tankDrive(0, 0), drivetrain)
        );
    }
    
}
