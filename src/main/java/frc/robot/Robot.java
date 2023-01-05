// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

import java.awt.event.KeyEvent;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot
{

    private final WPI_TalonSRX[] wpi_talonSRXES = new WPI_TalonSRX[]{new WPI_TalonSRX(1),new WPI_TalonSRX(3),new WPI_TalonSRX(2),new WPI_TalonSRX(4)};
    
    private final MotorControllerGroup leftDrive = new MotorControllerGroup(wpi_talonSRXES[0],wpi_talonSRXES[1]);
    private final MotorControllerGroup rightDrive = new MotorControllerGroup(wpi_talonSRXES[2],wpi_talonSRXES[3]);
    //private final MotorControllerGroup leftDrive = new DifferentialDrive(leftDrive, rightDrive);
    private final XboxController xboxController = new XboxController(0);
    private final Timer timer = new Timer();
    
    
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
    
    }
    /** This method is called periodically during teleoperated mode. */
    @Override
    public void teleopPeriodic()
    {
        DriverStation.reportWarning("teleperiodic",false);
        if(xboxController.getAButtonPressed()){
            leftDrive.stopMotor();
            rightDrive.stopMotor();
        }
        double forward = xboxController.getLeftTriggerAxis()*.4;
        double reverse = xboxController.getRightTriggerAxis()*.4;
        double rotate = xboxController.getRightX()*.4;
        
    
        leftDrive.set(forward+rotate);
        rightDrive.set(forward-rotate);
        
        leftDrive.set(reverse+rotate);
        rightDrive.set(reverse-rotate);
      
        
        
    }
    
    
    /** This method is called once each time the robot enters test mode. */
    @Override
    public void testInit() {}
    
    
    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
