/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.List;
import java.util.function.Consumer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.tekerz.L;

public class TestMotors extends Command {
  Consumer<Double> motorSet;

  private TestMotors() {
    // Use requires() here to declare subsystem dependencies
  }

  public TestMotors(Consumer<Double> motorSet, String name, List<Subsystem> subs) {
    this();
    this.setName(name);
    this.motorSet = motorSet;

    for (Subsystem sub : subs) {
      requires(sub);      
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    L.ogCmdInit(this);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.getButtonB()) {
      motorSet.accept(Robot.oi.getLeftY() / 10.0);
    } else if (Robot.oi.getButtonY()) {
      motorSet.accept(Robot.oi.getLeftY());      
    } else {
      motorSet.accept(0.0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    L.ogCmdEnd(this);
    this.cleanUp();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    L.ogCmdInterrupted(this);
    this.cleanUp();
  }

  private void cleanUp() {
    motorSet.accept(0.0);
  }
}
