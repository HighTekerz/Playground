/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.tekerz.Rioduino;

/**
 * Add your docs here.
 */
public class Sensors extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Thread updater = new Thread(new SensorUpdater());
  Rioduino rioduino = RobotMap.RIODuinos.rioduino;

  AnalogInput ai0 = RobotMap.Analogs.port0;
  AnalogInput ai1 = RobotMap.Analogs.port1;


  public Sensors() {
    updater.start();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new SensorsCommand());
  }

  public void rioChat() {
    rioduino.write("teleoppp");
    System.out.println(rioduino.read());
  }

  public void log() {
    SmartDashboard.putNumber("analog 0", ai0.getAverageVoltage());
    SmartDashboard.putNumber("analog 1", ai1.getAverageVoltage());
    SmartDashboard.putBoolean("digital 0", RobotMap.Digitals.port0.get());
  }
}
