package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends SubsystemBase{

        private final CANSparkFlex m_shooterLeft = new CANSparkFlex(27, MotorType.kBrushless);
        private final CANSparkFlex m_shooterRight = new CANSparkFlex(7, MotorType.kBrushless);
        private final RelativeEncoder m_leftencoder = m_shooterLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        private final RelativeEncoder m_rightencoder = m_shooterRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

        public final SparkPIDController m_leftPIDController = m_shooterLeft.getPIDController();
        
        
        // PID coefficients
        public double kP = 0.1; 
        public double kI = 1e-4;
        public double kD = 1; 
        public double kIz = 0; 
        public double kFF = 0; 
        public double kMaxOutput = 1; 
        public double kMinOutput = -1;
        
        public void display(){
            SmartDashboard.putNumber("P Gain", kP);
            SmartDashboard.putNumber("I Gain", kI);
            SmartDashboard.putNumber("D Gain", kD);
            SmartDashboard.putNumber("I Zone", kIz);
            SmartDashboard.putNumber("Feed Forward", kFF);
            SmartDashboard.putNumber("Max Output", kMaxOutput);
            SmartDashboard.putNumber("Min Output", kMinOutput);
        }

     public Shooter () {
            super(); 
            setPIDval(kP, kI, kD, kIz, kFF, kMinOutput, kMaxOutput);  
            display();
        
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

    public void setPIDval(double kP, double kI, double kD, double kIz, double kFF, double kMinOutput, double kMaxOutput) {
        m_leftPIDController.setP(kP);
        m_leftPIDController.setI(kI);
        m_leftPIDController.setD(kD);
        m_leftPIDController.setIZone(kIz);
        m_leftPIDController.setFF(kFF);
        m_leftPIDController.setOutputRange(kMinOutput, kMaxOutput);
    }
    


    
    
    
      
}
    

