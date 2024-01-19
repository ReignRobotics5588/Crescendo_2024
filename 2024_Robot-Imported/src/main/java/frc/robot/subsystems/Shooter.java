package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Shooter extends SubsystemBase{
        private final CANSparkFlex m_shooterLeft = new CANSparkFlex(27, MotorType.kBrushless);
        private final CANSparkFlex m_shooterRight = new CANSparkFlex(7, MotorType.kBrushless);
     public Shooter () {
            super();   
        }
    public void run(double speedLeft, double speedRight){
            m_shooterRight.set(speedRight); 
            m_shooterLeft.set(speedLeft);

    }
          
        
    }
    

