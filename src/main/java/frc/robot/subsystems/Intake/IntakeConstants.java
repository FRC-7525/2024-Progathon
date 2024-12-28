package frc.robot.subsystems.Intake;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.MomentOfInertia;

public final class IntakeConstants {
    public final static AngularVelocity INTAKING_SPEED = RotationsPerSecond.of(60);
    public final static AngularVelocity OUTTAKING_SPEED = RotationsPerSecond.of(-60);
    public final static AngularVelocity PASSING_SPEED = RotationsPerSecond.of(30); //might be negative idk
    public final static AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0);

    public final static Angle INTAKING_PIVOT = Degrees.of(70);
    public final static Angle OUTTAKING_PIVOT = Degrees.of(70);
    public final static Angle PASSING_PIVOT = Degrees.of(0);
    public final static Angle IDLE_PIVOT = Degrees.of(0);


    public final static class Sim {
        public final static int NUM_PIVOT_MOTORS = 1;
        public final static double PIVOT_GEARING = 5;
        public final static MomentOfInertia PIVOT_MOI = KilogramSquareMeters.of(0.31654227);
        public final static Distance PIVOT_ARM_LENGTH = Meters.of(.26199558);
        public final static Angle MIN_PIVOT_ANGLE = Degrees.of(0);
        public final static Angle MAX_PIVOT_ANGLE = Degrees.of(70);
        public final static Angle STARTING_PIVOT_ANGLE = Degrees.of(0);
        public final static PIDConstants PIVOT_PID_CONSTANTS = new PIDConstants(3, 0, 0);
    
        public final static int NUM_WHEEL_MOTORS = 1;
        public final static MomentOfInertia WHEEL_MOTOR_MOI = KilogramSquareMeters.of(1); // random value
        public final static double WHEEL_MOTOR_GEARING = 3;
        public final static PIDConstants WHEEL_PID_CONSTANTS = new PIDConstants(0.0012, 0, 0);
    }

    public final static class Real {
        public final static int WHEEL_MOTOR_CANID = 10;
        public final static int PIVOT_MOTOR_CANID = 11;
        public final static PIDConstants PIVOT_PID_CONSTANTS = new PIDConstants(3, 0, 0);
        public final static PIDConstants WHEEL_PID_CONSTANTS = new PIDConstants(0.0012, 0, 0);
    }
    }
