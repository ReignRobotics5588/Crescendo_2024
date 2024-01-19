package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.*;
public class Intake extends SubsystemBase {
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.kMotorPort,MotorType.kBrushless);
    private final DigitalInput m_intakeSensor = new DigitalInput(IntakeConstants.sensorPort);
    //To Do: add sensor - find out what hardware is 
    public Intake () {
        super();
    } 
    public void run(double speed) {
        m_intakeMotor.set(speed);
    } 
    
}
