package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShootSpeaker;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

public class ShootAuto extends SequentialCommandGroup {

    public ShootAuto(Drivetrain drivetrain, Shooter shooter, Intake intake){
        // private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
        // public DriveCommand(Drivetrain D, int d, double s, double a)

        addCommands(
            (Command) new DriveCommand(drivetrain,400, 0.7), 
            //(Command) new DriveCommand(drivetrain, 0, 0.5, -90),
            (Command) new DriveCommand(drivetrain, 200, 0.7), 
            //(Command) new DriveCommand(drivetrain, 0, 0.5, -90),
            new ShootSpeaker(shooter, intake)

        );
    }
    
}
