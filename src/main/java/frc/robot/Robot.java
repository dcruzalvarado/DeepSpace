/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.team2363.logger.HelixEvents;
import com.team2363.logger.HelixLogger;
import com.team319.follower.FollowArc;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.arcs.DistanceScalingArc;
import frc.arcs.ExampleArc;
import frc.arcs.Figure8Arc;
import frc.arcs.ForwardLeftArc;
import frc.arcs.MultiSpeedTestArc;
import frc.arcs.SpeedTestingArc;
import frc.arcs.Straight10FeetArc;
import frc.arcs.StraightBack10FeetArc;
import frc.arcs.TurnScalingArc;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.JesterArm;
import frc.robot.subsystems.JesterWrist;
import frc.robot.subsystems.PIDLifter;
// import frc.robot.subsystems.RobotLifter;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.camera.CAMERA;
import frc.robot.commands.drivetrain.driveByCamera;
import frc.robot.subsystems.CargoGrabber;
import frc.robot.subsystems.CargoIntake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
	
  private final Compressor compressor = new Compressor();

  Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() { 
    initializeSubsystems();
    Drivetrain.getInstance().resetHeading();
    
    // CAMERA.FRONT.setCameraMode();
    // CAMERA.BACK.setCameraMode();

  }

  private void initializeSubsystems() {
    OI.getInstance();
    Drivetrain.getInstance();

    JesterArm.getInstance();
    JesterWrist.getInstance();
    CargoGrabber.getInstance();
    HatchGrabber.getInstance();
    CargoIntake.getInstance();
    // RobotLifter.getInstance();
    PIDLifter.getInstance();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() { }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();

    // SmartDashboard.putNumber("Pigeon Yaw", Drivetrain.getInstance().getYaw());
    JesterArm.getInstance().updateSmartDash();
    JesterWrist.getInstance().updateSmartDash();
    JesterArm.getInstance().resetArmPreset();

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    Drivetrain.getInstance().resetHeading();
    // autonomousCommand = new FollowArc(Drivetrain.getInstance(), new Straight10FeetArc());
    // autonomousCommand = new FollowArc(Drivetrain.getInstance(), new ForwardLeftArc());
    // autonomousCommand = new FollowArc(Drivetrain.getInstance(), new DistanceScalingArc());
    // autonomousCommand = new FollowArc(Drivetrain.getInstance(), new Figure8Arc());
    // autonomousCommand = new FollowArc(Drivetrain.getInstance(), new Straight10FeetArc(), false, false, false);
    // JesterArm.getInstance().goTo(ArmPreset.START);
    autonomousCommand = null;
    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
    HelixEvents.getInstance().startLogging();
    // CAMERA.FRONT.setCameraMode();
    // CAMERA.BACK.setCameraMode();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    HelixLogger.getInstance().saveLogs();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    HelixLogger.getInstance().saveLogs();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() { }
}
