// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.CompVision.ComputerVision;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot
{
    final double ANGULAR_P = 0.1;
    final double ANGULAR_D = 0.0;
    PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);
    private final WPI_TalonSRX[] wpi_talonSRXES = new WPI_TalonSRX[]{new WPI_TalonSRX(1),new WPI_TalonSRX(3),new WPI_TalonSRX(2),new WPI_TalonSRX(4)};
    private final MotorControllerGroup leftDrive = new MotorControllerGroup(wpi_talonSRXES[0],wpi_talonSRXES[1]);
    private final MotorControllerGroup rightDrive = new MotorControllerGroup(wpi_talonSRXES[2],wpi_talonSRXES[3]);
    private static double rotateVal;
    private final XboxController xboxController = new XboxController(0);
    private final Timer timer = new Timer();
    private static final PhotonCamera camera = new PhotonCamera("photonvision");
    
    
    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        rightDrive.setInverted(true);
        ComputerVision initCompVision = new ComputerVision();
        Runnable init = initCompVision::init;
        init.run();
    
    }
    
    
    /** This method is run once each time the robot enters autonomous mode. */
    @Override
    public void autonomousInit()
    {
        timer.reset();
        timer.start();
 
    }
    
    
    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic()
    {
        // Drive for 2 seconds
        
    }
    
    
    /** This method is called once each time the robot enters teleoperated mode. */
    @Override
    public void teleopInit() {
        leftDrive.set(0);
        rightDrive.set(0);
    }
    @Override
    public void disabledPeriodic(){}
    
    @Override
    public void robotPeriodic(){
        var result = camera.getLatestResult();
        if(result.hasTargets()){
            List<PhotonTrackedTarget> targets = result.getTargets();
            PhotonTrackedTarget bestTarget = result.getBestTarget();
            rotateVal = -turnController.calculate(bestTarget.getYaw(), 0);
//TODO: setup co-processor
        }
    }
    /** This method is called periodically during teleoperated mode. */
    @Override
    public void teleopPeriodic()
    {
        if(xboxController.getAButtonPressed()){
            leftDrive.stopMotor();
            rightDrive.stopMotor();
        }
        
        
        double forward = xboxController.getLeftTriggerAxis()*.2;
        double reverse = xboxController.getRightTriggerAxis()*.2;
        rotateVal = xboxController.getRightX()*.2;
        
        leftDrive.set(forward+rotateVal);
        rightDrive.set(forward-rotateVal);
        
        leftDrive.set(reverse+rotateVal);
        rightDrive.set(reverse-rotateVal);
    }
    
    
  
    
    //TODO: Setup photonvision sim testing
    @Override
    public void testInit() {
    
    }
  
    @Override
    public void simulationInit() {
    
        
    }
    
    @Override
    public void simulationPeriodic() {
    }
    
    @Override
    public void testPeriodic() {}
}
