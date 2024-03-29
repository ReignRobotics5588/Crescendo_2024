package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.*;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.RobotContainer;

/*
* limit switch, to know when we reached a certain point
* mechanism pulls in and stops the winch
* get a true false reading, signal pin should always be 5 or 0 bolts
* idfk read limit switch documentation
* will forward be extending or pulling?????????????
* run motor in other direction, not same direction anymore
* SparkMax motors connecting directly to the motor controller?????????????
* NEO motors 
* DIO pins
* use NO and CLOSE
*10 and 11
*/

public class Climber extends SubsystemBase {

  public CANSparkMax climberLeft = new CANSparkMax(ClimberConstants.kClimberLeft, MotorType.kBrushless);
  public CANSparkMax climberRight = new CANSparkMax(ClimberConstants.kClimberRight, MotorType.kBrushless);
  public DigitalInput Switch = new DigitalInput(5);
  public RelativeEncoder m_encoder_left = climberLeft.getEncoder();
  public RelativeEncoder m_encoder_right = climberRight.getEncoder();

  public Climber() {
    climberLeft.setIdleMode(IdleMode.kBrake);
    climberRight.setIdleMode(IdleMode.kBrake);
    m_encoder_left.setPosition(0.0);
    m_encoder_right.setPosition(0.0);

    climberLeft.setSmartCurrentLimit(30);
    climberRight.setSmartCurrentLimit(30);
    
    climberLeft.setIdleMode(IdleMode.kBrake); //keeps climber in break mode from the
    // code itself :)
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speedLeft, double speedRight) {

    if ((Math.abs(speedLeft) > 0.25) || (Math.abs(speedRight))  > 0.25) {
      climberLeft.set(speedLeft*ClimberConstants.kClimberSpeedLimit);
      climberRight.set(speedRight*ClimberConstants.kClimberSpeedLimit);
    }
  }

  public boolean getSwitch() {
    return Switch.get();
  }

  public double getLeftEncoderDistance() {
    return (m_encoder_left.getPosition() / 42);// 42 ticks in one rotation, counting how many full rotations there are :)
  }

  public double getRightEncoderDistance() {
    return (m_encoder_right.getPosition() / 42);// 42 ticks in one rotation, counting how many full rotations there are :)
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

// pull encoder from SPARK MAX
// check docs for limit switches
// bottom limit is limit switch
// when climnber retracts itll stop by touching limit switch
// encoder distance is how far high
// set encoder value to 0 when
// 2 conditions always going to be if limit switch is hit or maxencoder distance
// is hit