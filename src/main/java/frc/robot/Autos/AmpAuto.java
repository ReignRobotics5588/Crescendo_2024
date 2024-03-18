package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.DriveAndIntake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TurnDrivetrain;
import frc.robot.commands.ShootLow;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Flapper;
import frc.robot.Constants.FlapperConstants;

public class AmpAuto extends SequentialCommandGroup {

    public AmpAuto(Drivetrain drivetrain, Shooter shooter, Intake intake, Flapper flapper){
        // private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
        // public DriveCommand(Drivetrain D, int d, double s, double a)

        addCommands(

            (Command) new TurnDrivetrain(drivetrain, 0.5, 90),
            (Command) new ShootLow(shooter, intake, flapper),
            (Command) new TurnDrivetrain(drivetrain, .65, 15),
            //(Command) new DriveCommand(drivetrain, 0, 6, -90),
            (Command) new DriveAndIntake(drivetrain, intake, -0.5, 0),
            (Command) new DriveCommand(drivetrain, 100, 0.0), // check measurement
            (Command) new TurnDrivetrain(drivetrain, .65, -15),
            (Command) new ShootLow(shooter, intake, flapper)

        );
    }
    
}
