/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pid_lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PIDLifter;
import frc.robot.subsystems.PIDLifter.LiftPos;;

public class BurstLifter extends Command {
  public BurstLifter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(PIDLifter.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    PIDLifter.getInstance().encoderReset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    PIDLifter.getInstance().goTo(LiftPos.BURST);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (PIDLifter.getInstance().isBurstDone());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
