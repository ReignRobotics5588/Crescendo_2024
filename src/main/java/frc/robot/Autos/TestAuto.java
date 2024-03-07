package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeWithBeam;
import frc.robot.commands.ShootSpeaker;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

public class TestAuto extends SequentialCommandGroup {

    public TestAuto(Drivetrain drivetrain, Shooter shooter, Intake intake){
        // private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
        // public DriveCommand(Drivetrain D, int d, double s, double a)

        addCommands(
            new ShootSpeaker(shooter, intake), 
            (Command) new DriveCommand(drivetrain,-59, 0.3),
            new IntakeWithBeam(shooter, intake), 
            (Command) new DriveCommand(drivetrain,59, 0.3, 90)

        );
    }
    
}
