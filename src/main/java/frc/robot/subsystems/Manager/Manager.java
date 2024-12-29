package frc.robot.subsystems.Manager;

import static frc.robot.GlobalConstants.*;
import static frc.robot.GlobalConstants.Controllers.TEST_CONTROLLER;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.pioneersLib.subsystem.Subsystem;
import frc.robot.subsystems.Drive.Drive;
import frc.robot.subsystems.Elevator.Elevator;
import frc.robot.subsystems.Indexer.Indexer;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Vision.Vision;

public class Manager extends Subsystem<ManagerStates> {

    private static Manager instance;

    private final Drive drive = Drive.getInstance();
    private final Vision vision = Vision.getInstance();
    private final Elevator elevator = Elevator.getInstance();
    private final CommandScheduler commandScheduler = CommandScheduler.getInstance();
    private final Intake intake = Intake.getInstance();
    private final Indexer indexer = Indexer.getInstance();
    // Change to change the subsystem that gets tested (has runnable sysID tests) saftey ish
    private final Subsystem<?> sysIdSubsystem = drive;


    private Manager() {
        super("Manager", ManagerStates.IDLE);

        // State transitions go here

        // SYS ID Tests (see constants to change test type for drive)
        if (ROBOT_MODE == RobotMode.TESTING) {
            addRunnableTrigger(() -> {commandScheduler.schedule(sysIdSubsystem.sysIdDynamic(Direction.kForward));}, () -> TEST_CONTROLLER.getAButtonPressed());
            addRunnableTrigger(() -> {commandScheduler.schedule(sysIdSubsystem.sysIdDynamic(Direction.kReverse));}, () -> TEST_CONTROLLER.getBButtonPressed());
            addRunnableTrigger(() -> {commandScheduler.schedule(sysIdSubsystem.sysIdQuasistatic(Direction.kForward));}, () -> TEST_CONTROLLER.getXButtonPressed());
            addRunnableTrigger(() -> {commandScheduler.schedule(sysIdSubsystem.sysIdQuasistatic(Direction.kReverse));}, () -> TEST_CONTROLLER.getYButtonPressed());
        }
        // From idle
        addTrigger(ManagerStates.IDLE, ManagerStates.INTAKING, () -> Controllers.DRIVER_CONTROLLER.getBButtonPressed());
        addTrigger(ManagerStates.IDLE, ManagerStates.OUTTAKING, () -> Controllers.DRIVER_CONTROLLER.getAButtonPressed());
        addTrigger(ManagerStates.IDLE, ManagerStates.GOING_HIGH, () -> Controllers.DRIVER_CONTROLLER.getRightTriggerAxis() > Controllers.TRIGGERS_REGISTER_POINT);
        addTrigger(ManagerStates.IDLE, ManagerStates.GOING_MID, () -> Controllers.DRIVER_CONTROLLER.getLeftTriggerAxis() > Controllers.TRIGGERS_REGISTER_POINT);

        //from intaking
        addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, () -> Controllers.DRIVER_CONTROLLER.getBButtonPressed() || Controllers.OPERATOR_CONTROLLER.getAButtonPressed() || indexer.getNumberOfPieces() == MAX_PIECES);
        addTrigger(ManagerStates.INTAKING, ManagerStates.OUTTAKING, () -> Controllers.DRIVER_CONTROLLER.getAButtonPressed());

        //from outtaking
        addTrigger(ManagerStates.OUTTAKING, ManagerStates.IDLE, () -> Controllers.DRIVER_CONTROLLER.getAButtonPressed());
        addTrigger(ManagerStates.OUTTAKING, ManagerStates.INTAKING, () -> Controllers.DRIVER_CONTROLLER.getYButtonPressed());

        //form going high
        addTrigger(ManagerStates.GOING_HIGH, ManagerStates.SCORING_HIGH, () -> elevator.nearTarget());

        //from scoring high
        addTrigger(ManagerStates.SCORING_HIGH, ManagerStates.IDLE, () -> Controllers.OPERATOR_CONTROLLER.getAButtonPressed() || indexer.isEmpty());

        //from going mid
        addTrigger(ManagerStates.GOING_MID, ManagerStates.SCORING_MID, () -> elevator.nearTarget());

        //from scoring mid
        addTrigger(ManagerStates.SCORING_MID, ManagerStates.IDLE, () -> Controllers.OPERATOR_CONTROLLER.getAButtonPressed() || indexer.isEmpty());
    }

    public static Manager getInstance() {
        if (instance == null) {     
            instance = new Manager();
        }
        return instance;
    }

    @Override
    public void runState() {
        Logger.recordOutput("Manager/State", getState().getStateString());
        Logger.recordOutput("Manager/State Time", getStateTime());

        if (Controllers.OPERATOR_CONTROLLER.getXButtonPressed()) {
            setState(ManagerStates.IDLE);
        }

        elevator.setState(getState().getElevatorState());
        intake.setState(getState().getIntakeState());
        indexer.setState(getState().getIndexerState());

        drive.periodic();
        vision.periodic();
        elevator.periodic();
        intake.periodic();
        indexer.periodic();

        // Other subsystem periodics go here
    }
}
