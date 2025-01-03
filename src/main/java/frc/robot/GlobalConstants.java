package frc.robot;

import static edu.wpi.first.units.Units.DegreesPerSecond;

import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.pioneersLib.misc.VisionUtil.CameraResolution;
import frc.robot.subsystems.Drive.Drive.SysIdMode;

public final class GlobalConstants {

	public enum RobotMode {
		REAL,
		TESTING,
		SIM,
		REPLAY,
	}

	public static final RobotMode ROBOT_MODE = "Crash".equals(System.getenv("CI_NAME"))
		? RobotMode.SIM
		: RobotMode.SIM;
	public static final double SIM_DELTA_TIME = 0.02;
	public static final int MAX_PIECES = 6;

	public static class Controllers {

		public static final XboxController DRIVER_CONTROLLER = new XboxController(0);
		public static final XboxController OPERATOR_CONTROLLER = new XboxController(1);
		public static final XboxController TEST_CONTROLLER = new XboxController(3);

		// NOTE: Set to 0.1 on trash controllers
		public static final double DEADBAND = 0.01;
		public static final double TRIGGERS_REGISTER_POINT = 0.5;
	}

	public static class Drive {

		public static final double SIM_UPDATE_TIME = 0.004;

		public static final AngularVelocity ANGULAR_VELOCITY_LIMIT = AngularVelocity.ofBaseUnits(
			180,
			DegreesPerSecond
		);

		// Change to change the sysID test that gets run for drive
		public static final SysIdMode SYS_ID_MODE = SysIdMode.STEER;
		public static final String SUBSYSTEM_NAME = "Drive";

		// For zeroing on robot init
		public static final Rotation2d BLUE_ALLIANCE_PERSPECTIVE_ROTATION = Rotation2d.fromDegrees(
			0
		);
		public static final Rotation2d RED_ALLIANCE_PERSPECTIVE_ROTATION = Rotation2d.fromDegrees(
			180
		);

		// Weird syntax because we have our own PIDConstants class (literally just the PP one :skull: copy pasted) so we can use it without installing PP Lib
		public static final PPHolonomicDriveController PATH_PLANNER_PID =
			new PPHolonomicDriveController(
				new com.pathplanner.lib.config.PIDConstants(5.0, 0, 0),
				new com.pathplanner.lib.config.PIDConstants(5.0, 0, 0)
			);

		public static RobotConfig geRobotConfig() {
			try {
				return RobotConfig.fromGUISettings();
			} catch (Exception e) {
				e.printStackTrace();
				// Dummy robot config, unsure of if this is good or bad
				return new RobotConfig(
					1,
					1,
					new ModuleConfig(1, 1, 1, DCMotor.getKrakenX60(1), 1, 1),
					1
				);
			}
		}

		public static final RobotConfig ROBOT_CONFIG = geRobotConfig();
	}

	// TODO: PROGATHON SPECIFIC | MAKE REALISTIC CAMERA POSITIONS FOR THIS ROBOT! ASSUME THERE ARE 2 CAMERAS
	public static final class Vision {

		// Robot to cam
		public static final Translation3d ROBOT_TO_SIDE_CAMERA_TRALSLATION = new Translation3d(
			Units.inchesToMeters(-7.19),
			Units.inchesToMeters(11),
			Units.inchesToMeters(15.25)
		);
		public static final Rotation3d ROBOT_TO_SIDE_CAMERA_ROTATION = new Rotation3d(
			0,
			Math.toRadians(-20),
			Math.toRadians(90)
		);
		public static final Transform3d ROBOT_TO_SIDE_CAMERA = new Transform3d(
			ROBOT_TO_SIDE_CAMERA_TRALSLATION,
			ROBOT_TO_SIDE_CAMERA_ROTATION
		);
		public static final Translation3d ROBOT_TO_FRONT_CAMERA_TRANSLATION = new Translation3d(
			Units.inchesToMeters(-14.25),
			0,
			Units.inchesToMeters(6)
		);
		public static final Rotation3d ROBOT_TO_FRONT_CAMERA_ROTATION = new Rotation3d(
			0,
			Math.toRadians(-67),
			Math.toRadians(180)
		);
		public static final Transform3d ROBOT_TO_FRONT_CAMERA = new Transform3d(
			ROBOT_TO_FRONT_CAMERA_TRANSLATION,
			ROBOT_TO_FRONT_CAMERA_ROTATION
		);

		public static final double CAMERA_DEBOUNCE_TIME = 0.5;

		// TODO: What camera resolutions actually are these? Assuming they're high bc 1080p is high
		public static final CameraResolution SIDE_RESOLUTION = CameraResolution.HIGH_RES;
		public static final CameraResolution FRONT_RESOLUTION = CameraResolution.HIGH_RES;

		// Other
		public static final AprilTagFieldLayout APRIL_TAG_FIELD_LAYOUT =
			AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo);
	}
}
