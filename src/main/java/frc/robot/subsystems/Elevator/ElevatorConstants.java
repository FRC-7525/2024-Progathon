package frc.robot.subsystems.Elevator;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Kilogram;
import static edu.wpi.first.units.Units.Meters;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Mass;
import frc.robot.pioneersLib.controlConstants.FFConstants;

public final class ElevatorConstants {
    
    public static final double POSITION_TOLERANCE = 0;
    public static final double VELOCITY_TOLERANCE = 0;
    public static final Constraints TRAPEZOID_PROFILE_CONSTRAINTS = new TrapezoidProfile.Constraints(1, .5);
    
    //more random values
    public static final Distance HIGH_POSITION_HEIGHT = Meters.of(1.4);
    public static final Distance MID_POSITION_HEIGHT = Meters.of(.5);
    public static final Distance IDLE_POSITION_HEIGHT = Meters.of(0);

    public static class Sim {
        public static final DCMotor GEARBOX = DCMotor.getKrakenX60(2);
        public static final double GEARING = 9;
        public static final Mass CARRIAGE_MASS = Kilogram.of(28.44);
        public static final Distance DRUM_RADIUS = Meters.of(.5); // Random value cuz mech is bum
        public static final Distance MIN_HEIGHT = Meters.of(1.54305);
        public static final Distance MAX_HEIGHT = Meters.of(2.60985);
        public static final boolean SIMULATE_GRAVITY = true;
        public static final Distance STARTING_HEIGHT = Meters.of(1.54305);
        //idk these values
        public static final PIDConstants PROFILLED_PID_CONSTANTS = new PIDConstants(0, 0, 0, 0);
    
        public static final FFConstants FF_CONSTANTS = new FFConstants(0, 0, 0, 0);
    
    }

    public static class Real {
            public static final int LEFT_MOTOR_CANID = 12;
            public static final int RIGHT_MOTOR_CANID = 13;

            public static final boolean LEFT_INVERTED = false;
            public static final NeutralModeValue LEFT_NEUTRAL_MODE = NeutralModeValue.Coast;
            public static final boolean LEFT_STRATOR_CURRENT_LIMIT_ENABLED = true;
            public static final Current LEFT_STRATOR_CURRENT_LIMIT = Amps.of(30);

            public static final boolean RIGHT_INVERTED = false;
            public static final NeutralModeValue RIGHT_NEUTRAL_MODE = NeutralModeValue.Coast;
            public static final boolean RIGHT_STRATOR_CURRENT_LIMIT_ENABLED = true;
            public static final Current RIGHT_STRATOR_CURRENT_LIMIT = Amps.of(30);

            public static final PIDConstants PROFILLED_PID_CONSTANTS = new PIDConstants(0, 0, 0, 0);
            public static final FFConstants FF_CONSTANTS = new FFConstants(0, 0, 0, 0);
    }       
}