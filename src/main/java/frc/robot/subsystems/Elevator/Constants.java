package frc.robot.subsystems.Elevator;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import frc.robot.pioneersLib.controlConstants.FFConstants;

public final class Constants {
    public static final DCMotor GEARBOX = DCMotor.getKrakenX60(2);
    public static final double GEARING = 9;
    public static final double CARRIAGE_MASS = 28.44;
    public static final double DRUM_RADIUS = 5; // Random value cuz mech is bum
    public static final double MIN_HEIGHT = 1.54305;
    public static final double MAX_HEIGHT = 2.60985;
    public static final boolean SIMULATE_GRAVITY = true;
    public static final double STARTING_HEIGHT = 1.54305;

    public static final PIDConstants PROFILLED_PID_CONSTANTS = new PIDConstants(1, 1, 1);
    public static final Constraints TRAPEZOID_PROFILE_CONSTRAINTS = new TrapezoidProfile.Constraints(1, .5);
    public static final double POSITION_TOLERANCE = .1;
    public static final double VELOCITY_TOLERANCE = .1;
    public static final double I_ZONE = .1;

    public static final FFConstants FF_CONSTANTS = new FFConstants(.1, .1, .1, .1);

}
