package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.*;
import frc.robot.subsystems.Shooter;

public class Intake extends SubsystemBase {

    private final CANSparkMax m_leftIntakeMotor = new CANSparkMax(IntakeConstants.kLeftMotorPort,MotorType.kBrushed);
    private final CANSparkMax m_rightIntakeMotor = new CANSparkMax(IntakeConstants.kRightMotorPort, MotorType.kBrushed);
    private final DigitalInput m_intakeSensor = new DigitalInput(IntakeConstants.sensorPort);
    private final DigitalInput m_intakeSensor2 = new DigitalInput(IntakeConstants.sensorPort2);
    
    public Intake () {
        super();
    } 
    public void run(double speed, Shooter shooter) {
        /*runMotors(speed);
        if (sense()) {
           runMotors(speed);
        } else {
            runMotors(0);
        }*/
        runMotors(speed, shooter);
    } 
    public boolean sense() {
        return !m_intakeSensor.get() || !m_intakeSensor2.get();
    
    }
    // if break -> stop intake unless flywheel is running

    public void runMotors(double speed, Shooter shooter) {
        m_leftIntakeMotor.set(-speed);
        m_rightIntakeMotor.set(-speed);

        
    }
}
