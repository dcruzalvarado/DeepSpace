package frc.robot.commands.jester_arm;

import com.team2363.logger.HelixEvents;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ArmPreset;
import frc.robot.OI;
import frc.robot.subsystems.JesterArm;
import frc.robot.subsystems.JesterWrist;

public class DriveArmByJoystick extends Command {

    private JesterArm jesterArm = JesterArm.getInstance();

    int position;

    public DriveArmByJoystick() {
        super("Drive arm by joystick");
        requires(jesterArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        position = jesterArm.getArmPos();
        HelixEvents.getInstance().addEvent("JESTER ARM", "Starting to drive arm by joystick");
    }

    @Override
    protected void execute() {

        double error;
        int backLimit = ArmPreset.BACK_HATCH_LOWER.CalculateArmPos();
        int frontLimit = ArmPreset.FRONT_HATCH_LOWER.CalculateArmPos();

        position += OI.getInstance().getArmPower() * 10;
        if (position > backLimit) {
            position = backLimit;
        } else if (position < frontLimit) {
            position = frontLimit;
        }

        jesterArm.setArmMotionMagic(position);

        SmartDashboard.putNumber("Arm Manual Position", position);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        HelixEvents.getInstance().addEvent("JESTER ARM", "finished DriveArmByJoystick");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        HelixEvents.getInstance().addEvent("JESTER ARM", "Interrupted DriveArmByJoystick");
    }
}
