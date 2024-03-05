package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeWithBeam;
import frc.robot.commands.ShootLow;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Flapper;
import frc.robot.Constants.FlapperConstants;

public class AmpAuto extends SequentialCommandGroup {

    public AmpAuto(DriveSubsystem drivetrain, Shooter shooter, Intake intake, Flapper flapper){
        // private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
        // public DriveCommand(Drivetrain D, int d, double s, double a)

        addCommands(
            (Command) new DriveCommand(drivetrain,400, 0.7), 
            (Command) new DriveCommand(drivetrain, 0, 0.5, -90),
            (Command) new DriveCommand(drivetrain, 200, 0.7), 
            (Command) new ShootLow(shooter, intake, flapper),
            (Command) new DriveCommand(drivetrain, 0, 0.5, -90),
            (Command) new IntakeWithBeam(shooter, intake),
            (Command) new ShootLow(shooter, intake, flapper)

        );
    }
    
}
