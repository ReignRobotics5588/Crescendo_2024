package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveAndIntake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeWithBeam;
import frc.robot.commands.ShootSpeaker;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Commands;

public class Shoot2CenterNote extends SequentialCommandGroup {

    public Shoot2CenterNote(Drivetrain drivetrain, Shooter shooter, Intake intake){
        // private final Command m_autonomousCommand = new DriveCommand(m_Drivetrain, 60, 0.7);
        // public DriveCommand(Drivetrain D, int d, double s, double a)

        // is this for Blue or Red - unsure
       /*  addCommands(
            new ShootSpeaker(shooter, intake), 
            (Command) new DriveCommand(drivetrain,-59, 0.3),
            (Command) new DriveAndIntake(drivetrain, intake, 0.5, 0), 
            (Command) new DriveCommand(drivetrain, -74, 0.3, 0), 
            new ShootSpeaker(shooter, intake),
            // back 74 inches, then shoot
            // why does it turn 90 degrees before it shoots; 
            (Command) new DriveCommand(drivetrain,59, 0.3, 90)

        ); */


        
        addCommands(
            new ShootSpeaker(shooter, intake), 
            (Command) new DriveCommand(drivetrain,66, -0.5), 
            (Command) new DriveAndIntake(drivetrain, intake, -0.5, 0),
            (Command) new DriveCommand(drivetrain, 115, 0.5), 
            new ShootSpeaker(shooter, intake)
            // back 74 inches, then shoot
        );

    }
        
    }
    

