/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.sparksub;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.SparkSub;

public class SparkSubPIDCommand extends Command {
  private double setpoint;
  private SparkSub sub = Robot.Subsystems.sparkSub;

  @SuppressWarnings({"unused"})
  private SparkSubPIDCommand() {}
  public SparkSubPIDCommand(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    requires(sub);
    this.setpoint = setpoint;
  }

  public SparkSubPIDCommand(double setpoint, String name) {
    this(setpoint);
    this.setName(name);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sub.setSetpoint(this.setpoint);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sub.executePID();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
