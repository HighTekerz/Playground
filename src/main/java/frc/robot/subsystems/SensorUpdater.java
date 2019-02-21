/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SensorUpdater implements Runnable {

    Timer t = new Timer();
    double lastTime = 0.0;
    double now = 0.0;

    int i = 0;
    public void run() {
        t.start();
        try {
            while (true) {
                Thread.sleep(10);
                SmartDashboard.putNumber("thread analog", RobotMap.Analogs.port0.getVoltage());
                SmartDashboard.putNumber("thread length", -lastTime + (lastTime = t.get()));

            }
        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

    public void log() {
    
    }
  }
