package frc.robot.subsystems.Intake;

import com.pathplanner.lib.config.PIDConstants;

public final class Constants {
    public final static double INTAKING_SPEED = 60;
    public final static double OUTTAKING_SPEED = -60;
    public static final double PASSING_SPEED = 30; //might be negative idk
    public final static double IDLE_SPEED = 0;

    public final static double INTAKING_PIVOT = 70;
    public final static double OUTTAKING_PIVOT = 70;
    public final static double PASSING_PIVOT = 0;
    public final static double IDLE_PIVOT = 0;

    public final static int NUM_PIVOT_MOTORS = 1;
    public final static double PIVOT_GEARING = 5;
    public final static double PIVOT_MOI = 0.31654227;
    public final static double PIVOT_ARM_LENGTH = 26.199558;
    public final static double MIN_PIVOT_ANGLE = 0;
    public final static double MAX_PIVOT_ANGLE = 70;
    public final static double STARTING_PIVOT_ANGLE = 70;
    public final static PIDConstants PIVOT_PID_CONSTANTS = new PIDConstants(0, 0, 0);

    public final static int NUM_WHEEL_MOTORS = 1;
    public final static double WHEEL_MOTOR_MOI = 0.0001;
    public final static double WHEEL_MOTOR_GEARING = 3;
    public final static PIDConstants WHEEL_PID_CONSTANTS = new PIDConstants(0, 0, 0);
}
