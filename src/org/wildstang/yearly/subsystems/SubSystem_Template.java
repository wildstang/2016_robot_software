package org.wildstang.yearly.subsystems;

import org.wildstang.framework.config.Config;
/* Please edit!!!
 *
 * This class is an example of a subsystem. For the moment, it reads a digital sensor 
 * and drives a digital output which turns on or off an LED.
 *
 * Continue...
 */
//expand this and edit if trouble with Ws
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.io.outputs.DigitalOutput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.crio.outputs.WsDigitalOutput;
import org.wildstang.hardware.crio.outputs.WsSolenoid;
import org.wildstang.yearly.robot.WSInputs;
import org.wildstang.yearly.robot.WSOutputs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubSystem_Template implements Subsystem
{
   // add variables here
   private boolean TestSwitchSensor;

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
      TestSwitchSensor = false;

      // Register the sensors that this subsystem wants to be notified about
      Core.getInputManager().getInput(WSInputs.TEST_SWITCH_SENSOR.getName()).addInputListener(this);
   }

   @Override
   public void inputUpdate(Input source)
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method is called any time one of the registered inputs has changed. The software in 
      // this method should do the following:
      // 
      // 1.  Determine which registered input this method is beeing called with
      // 2.  Read the updated value and store so it can be used in the update() method
      //*********************************************************************************************

      // This section reads the input sensors and places them into local variables
      if (source.getName().equals(WSInputs.TEST_SWITCH_SENSOR.getName()))
      {
         TestSwitchSensor = ((DigitalInput) source).getValue();
      }
   }

   @Override
   public void update()
   {
      // 
      // TODO Auto-generated method stub
      // 
      //*********************************************************************************************
      // This method is called after all of the registered updates have gone through the inputUpdate()
      // method. The software in this method should do the following:
      //
      // 1. Update any variables based on the input variables
      // 2. Tell the framework what the updated output values should be set to.
      // 
//       ((DigitalOutput)Core.getOutputManager().getOutput(WSOutputs.TEST_LED.getName())).setValue(TestSwitchSensor);
   }
}
