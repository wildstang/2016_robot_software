package org.wildstang.yearly.subsystems;

import org.wildstang.framework.config.Config;
/* Please edit!!!
 * This program does all these things
 * reads INTAKE_BOLDER_SENSOR. INTAKE_BOLDER_SENSOR = intakeSensorReading.
 * intakeSensorReading stops rollerMovingIn unless manRollerInOverrideCurrentState is true and manRollerInOverrideOldState is false.
 * 
 * reads MAN_LEFT_JOYSTICK_Y. MAN_LEFT_JOYSTICK_Y = manLeftJoyRollerIn
 * prints out manLeftJoyRollerIn. if manLeftJoyRollerIn is greater than .5, rollerMovingIn is set to true and rollerMovingOut is set to false. this makes the roller move in
 * if manLeftJoyRollerIn is less than -.5, rollerMovingIn is set to false and rollerMovingOut is set to true. this makes the roller move out
 * 
 * status of manLeftJoyRollerIn, intakeSensorReading, and rollerMovingIn are printed out
 * 
 * reads MAN_BUTTON_6. MAN_BUTTON_6 = manNoseControl
 * changes nosePneumatic to true and deployPneumatic to false when manNoseControl is true
 * 
 * reads DRV_BUTTON_6. DRV_BUTTON_6 = drvNoseControl
 * changes nosePneumatic to true and deployPneumatic to false when drvNoseControl is true
 * 
 * reads MAN_BUTTON_1. MAN_BUTTON_1 = manRollerInStartNew
 * toggles rollerMovingIn with manRollerInStartNew & manRollerInStartOld, stops intakeSensorReading from being true
 * 
 * Continue...
 */
//expand this and edit if trouble with Ws
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.crio.outputs.WsSolenoid;
import org.wildstang.yearly.robot.WSInputs;
import org.wildstang.yearly.robot.WSOutputs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubSystem_Template implements Subsystem
{
   // add variables here

   private boolean DigitalIO_0;
   private boolean intakeSensorReading;
   private boolean nosePneumatic;
   private boolean deployPneumatic;
   private boolean manRollerInOverride;
   private boolean manNoseControl = false;
   private boolean drvNoseControl = false;
   private boolean manDeployPneumaticControl = false;
   private boolean intakeLimboNew;
   private boolean intakeLimboOld;
   //private boolean limboOn = false;
   private boolean shoot;
   private WsDoubleSolenoid intakeDeploy;
   private WsSolenoid intakeFrontLower;
   private AnalogOutput frontRoller;
   private AnalogOutput frontRoller2;
   private double manLeftJoyRoller;
   private double rollerSpeed;
   private boolean AutoSubSystem_TemplateOverride = false;
   private boolean ShotSubSystem_TemplateOverride = false;
   
   private double OverrideValue;
   
   
   private double intakeSpeedIn;
   private double intakeSpeedOut;
   private double temp;
   private static final String intakeSpeedKey = ".intakeSpeedVictor";
   private static final double INTAKE_DEFAULT = 1;
   private static final String intakeSpeedInKey = ".intakeSpeedIn";
   private static final double INTAKE_IN_DEFAULT = -1;
   private static final String intakeSpeedOutKey = ".intakeSpeedOut";
   private static final double INTAKE_OUT_DEFAULT = 1;

   @Override
   public void selfTest()
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method must exist but for the time being, leave it empty. 
      //*********************************************************************************************
   }

   @Override
   public String getName()
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method should return the name of the subsystem.
      //*********************************************************************************************
      return "SubSystem_Template";
   }

   @Override
   public void init()
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method must exist even if it does nothing. It is called once and only once when the 
      // framework is started. It is used to setup local variables to initial values and to register
      // with the framework which inputs the framework should call the inputUpdate() method for.
      //*********************************************************************************************

      // Setup any local variables with intial values
      DigitalIO_0 = false;

      // Register the sensors that this subsystem wants to be notified about
      Core.getInputManager().getInput(WSInputs.DIO_0.getName()).addInputListener(this);
   }

   @Override
   public void inputUpdate(Input source)
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method is called any time one of the registered inputs has changed. 
      //*********************************************************************************************
      // 
      // TODO Auto-generated method stub

      // This section reads the input sensors and places them into local variables
      if (source.getName().equals(WSInputs.DIO_0.getName()))
      {
         DigitalIO_0 = ((DigitalInput) source).getValue();
      }
   }

   @Override
   public void update()
   {
      // TODO Auto-generated method stub

      // does something with variables and Outputs

      // tells status of certain variables
      // System.out.println("shoot=" + shoot + " rollerSpeed= " + rollerSpeed);

      // Puts the nose pneumatic in motion when either the drvNoseControl or
      // man nose control are true
//      if (drvNoseControl == true || manNoseControl == true)
//      {
//         nosePneumatic = true;
//      }
//      else
//      {
//         nosePneumatic = false;
//      }
      nosePneumatic = manNoseControl;

      // toggles deployPneumatic to manDeployPneumaticControl
      if (manDeployPneumaticControl == true)
      {
         deployPneumatic = true;
         rollerSpeed = 0;
      }
      else
      {
         deployPneumatic = false;
      }

      // if you push the left joy stick up, the intake will roll outwards.
      // if you push the left joy stick down, the intake will roll inwards.
      if (manLeftJoyRoller <= -0.5)
      {
         rollerSpeed = intakeSpeedIn;
      }
      else if (manLeftJoyRoller >= 0.5)
      {
         rollerSpeed = intakeSpeedOut;
      }
      else
      {
         rollerSpeed = 0;
      }

      if (intakeSensorReading == true && rollerSpeed < 0)
      {
         rollerSpeed = 0;
      }

      if (manRollerInOverride == true)
      {
         rollerSpeed = intakeSpeedIn;
      }

      if (shoot == true)
      {
         rollerSpeed = intakeSpeedIn;
      }

      // Allows for toggling of limbo
//      if (limboOn)
//      {
//         if (deployPneumatic == false)
//         {
//            deployPneumatic = true;
//         }
//         if(nosePneumatic == false)
//         {
//            nosePneumatic = true;
//         }
//      }

      // buttonPress controls DIO_LED_0 etc.
      // ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.DIO_LED_0.getName())).setValue(manLeftJoyRollerIn
      // >= .5);
      // ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.SENSOR_LED_1.getName())).setValue(intakeSensorReading);
      // ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.FRONT_ROLLER_LED_2.getName())).setValue(rollerMovingIn);
      // ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.Pneumatic_1.getName())).setValue(nosePneumatic);
      // ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.Pneumatic_2.getName())).setValue(deployPneumatic);
      
      
      intakeDeploy.setValue(deployPneumatic ? (WsDoubleSolenoidState.FORWARD).ordinal() : (WsDoubleSolenoidState.REVERSE).ordinal());
      intakeFrontLower.setValue(nosePneumatic);
      if(AutoSubSystem_TemplateOverride && !intakeSensorReading)
      {
      frontRoller.setValue(OverrideValue);
      frontRoller2.setValue(-OverrideValue);
      }
      else if(AutoSubSystem_TemplateOverride && intakeSensorReading)
      {
      frontRoller.setValue(0);
      frontRoller2.setValue(0);
      }
      else if(ShotSubSystem_TemplateOverride)
      {
      frontRoller.setValue(OverrideValue);
      frontRoller2.setValue(-OverrideValue);
      }
      else
      {
      frontRoller.setValue(rollerSpeed);
      frontRoller2.setValue(-rollerSpeed);
      }
      SmartDashboard.putBoolean("Has Ball", intakeSensorReading);
      SmartDashboard.putNumber("rollerSpeed=", rollerSpeed);
      SmartDashboard.putBoolean("SubSystem_Template staged=", intakeSensorReading);
   }
   
   public void notifyConfigChange(Config p_newConfig)
   {
      intakeSpeedIn = p_newConfig.getDouble(this.getClass().getName()
            + intakeSpeedInKey, INTAKE_IN_DEFAULT);

      intakeSpeedOut = p_newConfig.getDouble(this.getClass().getName()
            + intakeSpeedOutKey, INTAKE_OUT_DEFAULT);

   }

   public boolean isDeployed()
   {
      return deployPneumatic;
   }

   public boolean isNoseDeployed()
   {
      return nosePneumatic;
   }
   public void setSubSystem_TemplateOverrideOn(boolean state)
   {
      AutoSubSystem_TemplateOverride = state;
   }
   public void SubSystem_TemplateValue(double value)
   {
      OverrideValue = value;
   }
   public void setShotOverride(boolean state)
   {
      ShotSubSystem_TemplateOverride = state;
   }
   public void shotOverride(boolean on)
   {
      if(on) OverrideValue = -1;
      else OverrideValue = 0;
   }

}
