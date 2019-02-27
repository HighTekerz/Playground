/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.tekerz.Rioduino;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static class Talons {
    public static TalonSRX testTalonEncoder = new TalonSRX(0);
    public static TalonSRX testTalonIMU = new TalonSRX(21);
  }

  public static class Sparks {
    public static CANSparkMax testSpark = new CANSparkMax(20, MotorType.kBrushless);
    public static CANSparkMax testSpark2 = new CANSparkMax(24, MotorType.kBrushless);
  }

  public static class Analogs {
    public static AnalogInput port0 = new AnalogInput(0);
    public static AnalogInput port1 = new AnalogInput(1);
  }

  public static class Pigeons {
    public static PigeonIMU imu = new PigeonIMU(RobotMap.Talons.testTalonIMU);
  }

  public static class RIODuinos {
    public static Rioduino rioduino = new Rioduino();
  }

  public static class Digitals {
    public static DigitalInput port0 = new DigitalInput(0);
  }

}
