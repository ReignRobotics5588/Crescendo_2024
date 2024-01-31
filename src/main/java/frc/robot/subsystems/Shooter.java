package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Shooter extends SubsystemBase{

        private final CANSparkFlex m_shooterLeft = new CANSparkFlex(24, MotorType.kBrushless);
        private final CANSparkFlex m_shooterRight = new CANSparkFlex(7, MotorType.kBrushless);
        private final RelativeEncoder m_leftencoder = m_shooterLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        private final RelativeEncoder m_rightencoder = m_shooterRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

     public Shooter () {
            super();   
        }
    public void run(double speedLeft, double speedRight){
            m_shooterRight.set(speedRight); 
            m_shooterLeft.set(-speedLeft);

    }

    public double getLeftRPM(){
        return m_leftencoder.getVelocity();
    }

     public double getRightRPM(){
        return m_rightencoder.getVelocity();
    }

    public CANSparkFlex getRightMotor(){
        return m_shooterRight;
    }

    public CANSparkFlex getLeftMotor(){
        return m_shooterLeft;
    }
          
        
}
    

