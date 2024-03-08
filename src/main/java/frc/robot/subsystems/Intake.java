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
    private final DigitalInput m_intakeSensorOne = new DigitalInput(IntakeConstants.sensorPortOne);
    private final DigitalInput m_intakeSensorTwo = new DigitalInput(IntakeConstants.sensorPortTwo);
    
    public Intake () {
        super();
    } 
    
    public boolean sense() {
        return !m_intakeSensorOne.get() || !m_intakeSensorTwo.get(); 
    }
    // if break -> stop intake unless flywheel is running

    public void runMotors(double speed, Shooter shooter) {
        m_leftIntakeMotor.set(-speed);
        m_rightIntakeMotor.set(-speed);

        
    }
}
