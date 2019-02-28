/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SensorUpdater implements Runnable {

    StringBuilder sb = new StringBuilder();
    CANEncoder enc = RobotMap.Sparks.testSpark.getEncoder();
    boolean logData = false;
    long lastTime = 0;
    double now = 0.0;
    long i = 0;
    AnalogInput 
        a0=RobotMap.Analogs.port0,
        a1=RobotMap.Analogs.port1,
        a2=RobotMap.Analogs.port2,
        a3=RobotMap.Analogs.port3;

    public void logData(boolean logData) {
        this.logData = logData;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(1);
                if (i%100==0) {
                    SmartDashboard.putNumber("thread analog", RobotMap.Analogs.port0.getVoltage());
                    System.out.println(-lastTime + (lastTime = RobotController.getFPGATime()));
                }

                if (this.logData) {
                    this.sb.append(enc.getPosition()).append(",");
                    this.sb.append(a0.getAverageVoltage()).append(",");
                    this.sb.append(a1.getAverageVoltage()).append(",");
                    this.sb.append(a2.getAverageVoltage()).append(",");
                    this.sb.append(a3.getAverageVoltage());
                    this.sb.append("\n");
                    if (i%100==0) {
                        System.out.print(sb);
                        sb = new StringBuilder();
                    }
                }

                i++;
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    public void log() {
    
    }
  }
