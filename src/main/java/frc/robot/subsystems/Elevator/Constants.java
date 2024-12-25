package frc.robot.subsystems.Elevator;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import frc.robot.pioneersLib.controlConstants.FFConstants;

public final class Constants {
    
    public static final double POSITION_TOLERANCE = 0;
    public static final double VELOCITY_TOLERANCE = 0;
    public static final Constraints TRAPEZOID_PROFILE_CONSTRAINTS = new TrapezoidProfile.Constraints(1, .5);
    
    //more random values
    public static final double HIGH_POSITION_HEIGHT = 1.4;
    public static final double MID_POSITION_HEIGHT = .5;
    public static final double IDLE_POSITION_HEIGHT = 0;

    public static class Sim {
        public static final DCMotor GEARBOX = DCMotor.getKrakenX60(2);
        public static final double GEARING = 9;
        public static final double CARRIAGE_MASS = 28.44;
        public static final double DRUM_RADIUS = 5; // Random value cuz mech is bum
        public static final double MIN_HEIGHT = 1.54305;
        public static final double MAX_HEIGHT = 2.60985;
        public static final boolean SIMULATE_GRAVITY = true;
        public static final double STARTING_HEIGHT = 1.54305;
        //idk these values
        public static final PIDConstants PROFILLED_PID_CONSTANTS = new PIDConstants(1, 1, 1, 0);
    
        public static final FFConstants FF_CONSTANTS = new FFConstants(.1, .1, .1, .1);
    
    }

    public static class Real {
            public static final int LEFT_MOTOR_CANID = 12;
            public static final int RIGHT_MOTOR_CANID = 13;

            public static final boolean LEFT_INVERTED = false;
            public static final NeutralModeValue LEFT_NEUTRAL_MODE = NeutralModeValue.Coast;
            public static final boolean LEFT_STRATOR_CURRENT_LIMIT_ENABLED = true;
            public static final double LEFT_STRATOR_CURRENT_LIMIT = 30;

            public static final boolean RIGHT_INVERTED = false;
            public static final NeutralModeValue RIGHT_NEUTRAL_MODE = NeutralModeValue.Coast;
            public static final boolean RIGHT_STRATOR_CURRENT_LIMIT_ENABLED = true;
            public static final double RIGHT_STRATOR_CURRENT_LIMIT = 30;

            public static final PIDConstants PROFILLED_PID_CONSTANTS = new PIDConstants(0, 0, 0, 0);
            public static final FFConstants FF_CONSTANTS = new FFConstants(0, 0, 0, 0);
    }       
}
