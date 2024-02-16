package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FlapperConstants;

public class Flapper extends SubsystemBase {
    public final CANSparkMax m_flapperMotor = new CANSparkMax(FlapperConstants.kFlapperMotorPort, MotorType.kBrushed);

    public Flapper () {
        super();
    }

    public void run(double velocity){
        m_flapperMotor.set(velocity);
    }

    
}
