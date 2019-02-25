/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.tekerz.L;
import frc.tekerz.PIDF;

/**
 * Add your docs here.
 */
public class SparkSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax spark = RobotMap.Sparks.testSpark;
  CANEncoder enc = spark.getEncoder();
  CANSparkMax spark2 = RobotMap.Sparks.testSpark2;
  CANEncoder enc2 = spark2.getEncoder();
  
  double 
    p = .01,
    i = 0.0,
    d = 0.001,
    f = 0.0;
  PIDF pid = new PIDF(p, i, d);

  public SparkSub() {
    pid.setIMax(.5);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setSpeed(double speed) {
    spark.set(speed);
  }

  public double getEncoder() {
    return enc.getPosition();
  }

  public void setSetpoint(double setpoint) {
    pid.setSetpoint(setpoint);
    pid.start();
  }

  public void executePID() {
    double speed = pid.loop(enc.getPosition());
    L.ogSD("PID Sensor", enc.getPosition());
    L.ogSD("PID ouput", speed);
    spark.set(speed);
  }

  public void log() {
    L.ogSD("enc1", enc.getPosition());
    L.ogSD("enc2", enc2.getPosition());
  }
}
