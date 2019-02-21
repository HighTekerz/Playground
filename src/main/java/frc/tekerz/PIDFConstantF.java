/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.tekerz;

import edu.wpi.first.wpilibj.Timer;

/**
 * PIDFConstantF is an implementation of a PIDF loop for a steady state F
 * to use: call start, then call loop regularly
 * 
 * how to set F:
 * 
 * Elevator that holds itself at setpoint at constant value:
 * set to motor output that holds posistion
 * 
 * System that requires no ouput at setpoint:
 * set to 0
 * 
 */
public class PIDFConstantF {
    double p,i,d,f;
    double
        iMax = Double.MAX_VALUE,
        error = 0.0, 
        accumulatedError = 0.0,
        previousError = 0.0,
        changeInError = 0.0,
        setpoint = 0.0,
        rampSecondsPer = 1.0,
        output = 0.0,
        lastOutput = 0.0,
        changeInOutput = 0.0,
        elapsedTime = 0.0,
        timeNow = 0.0,
        timeLast = 0.0,
        pPart = 0.0,
        iPart = 0.0,
        dPart = 0.0,
        fPart = 0.0;


    Timer timer = new Timer();
    
    public PIDFConstantF(double p, double i, double d, double f) {
        this.setPIDF(p, i, d, f);
    }

    public void setPIDF(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    public void setIMax(double iMax) {
        this.iMax = iMax;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double getSetpoint() {
        return this.setpoint;
    }

    public void start() {
        accumulatedError = 
            changeInOutput =
            previousError = 
            0.0;
        timer.reset();
        timer.start();
        timeLast = timer.get();
    }

    /**
     * 
     * @param sensorData what you are measuring
     * @return motor output
     */
    public double loop(double sensorData) {
        timeNow = timer.get();
        elapsedTime = timeNow - timeLast;
        timeLast = timeNow; // save for next time

        error = setpoint - sensorData;

        accumulatedError += error * elapsedTime;
        if (accumulatedError > iMax) accumulatedError = iMax;

        changeInError = (error - previousError) / elapsedTime;

        output = 
            p * error +
            i * accumulatedError +
            d * changeInError +
            f;

        changeInOutput = (lastOutput - output) / elapsedTime;

        return output;        

    }
}
