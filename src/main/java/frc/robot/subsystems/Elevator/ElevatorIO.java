package frc.robot.subsystems.Elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {

    @AutoLog
    public static class ElevatorIOInputs {
        public double currentElevatorHeight;
        public double elevatorHeightSetpoint;
        public double elevatorHeightGoalpoint;
        public double elevatorVelocity;
        public double elevatorVelocitySetpoint;
        public double elevatorVelocityGoalpoint;
        public double leftMotorVoltInput;
        public double rightMotorVoltInput;
    }

    public void setHeightGoalpoint(double height);

    public void updateInputs(ElevatorIOInputs inputs);

    public void runElevator();
    
    public boolean nearTarget();
}
