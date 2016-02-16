package org.wildstang.yearly.subsystems;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.crio.outputs.WsSolenoid;
import org.wildstang.hardware.crio.outputs.WsVictor;
import org.wildstang.yearly.robot.WSInputs;
import org.wildstang.yearly.robot.WSOutputs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber implements Subsystem
{
   /*
    * Climber Robot Class Authors: Wallace Butler and Lucas Papaioannou
    */
   private boolean arm;

   private double winchValue;
   private boolean winchRunning;

   private boolean hook;

   private int startDelay = 0;
   private int stopDelay = 0;
   private boolean brakeEngaged = true;
   private boolean override = false;
   private boolean rightArmTouch;
   private boolean leftArmTouch;
   private double rawWinchValue;

   private WsSolenoid brake;
   private WsDoubleSolenoid hooks;
   private WsSolenoid lowerArm;
   private WsSolenoid upperArm;
   private WsVictor leftWinch;
   private WsVictor rightWinch;

   @Override
   public void inputUpdate(Input source)
   {
      if (source.getName().equals(WSInputs.MAN_BUTTON_1.getName()))
      {
         if (((DigitalInput) source).getValue())
         {
            arm = !arm;
         }
      }
      else if (source.getName().equals(WSInputs.MAN_RIGHT_JOYSTICK_Y.getName()))
      {

         winchValue = ((AnalogInput) source).getValue();
         rawWinchValue = ((AnalogInput) source).getValue();
      }
      else if (source.getName().equals(WSInputs.MAN_BUTTON_2.getName()))
      {
         if (((DigitalInput) source).getValue())
         {
            hook = !hook;
         }

      }
      else if (source.getName().equals(WSInputs.MAN_BUTTON_9.getName()))
      {
         override = ((DigitalInput) source).getValue();
      }
      else if (source.getName().equals(WSInputs.LEFT_ARM_TOUCHING.getName()))
      {
         leftArmTouch = ((DigitalInput) source).getValue();
      }
      else if (source.getName().equals(WSInputs.RIGHT_ARM_TOUCHING.getName()))
      {
         rightArmTouch = ((DigitalInput) source).getValue();
      }
   }

   @Override
   public void init()
   {
      System.out.println("init got called");
      Core.getInputManager().getInput(WSInputs.MAN_RIGHT_JOYSTICK_Y.getName()).addInputListener(this);
      Core.getInputManager().getInput(WSInputs.MAN_BUTTON_1.getName()).addInputListener(this);
      Core.getInputManager().getInput(WSInputs.MAN_BUTTON_2.getName()).addInputListener(this);
      Core.getInputManager().getInput(WSInputs.MAN_BUTTON_9.getName()).addInputListener(this);
      Core.getInputManager().getInput(WSInputs.RIGHT_ARM_TOUCHING.getName()).addInputListener(this);
      Core.getInputManager().getInput(WSInputs.LEFT_ARM_TOUCHING.getName()).addInputListener(this);
      brake = ((WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.WINCH_BRAKE.getName()));
      hooks = (WsDoubleSolenoid) Core.getOutputManager().getOutput(WSOutputs.HOOK_EXTENSION.getName());
      lowerArm = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.LOWER_ARM.getName());
      upperArm = (WsSolenoid) Core.getOutputManager().getOutput(WSOutputs.UPPER_ARM.getName());
      leftWinch = (WsVictor) Core.getOutputManager().getOutput(WSOutputs.WINCH_LEFT.getName());
      rightWinch = (WsVictor) Core.getOutputManager().getOutput(WSOutputs.WINCH_RIGHT.getName());
   }

   @Override
   public void selfTest()
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void update()
   {
      if (arm)
      {
         winchValue = 0;
         winchRunning = false;
         brakeEngaged = true;
         brake.setValue(true);
         lowerArm.setValue(true);
         lowerArm.setValue(true);
      }
      else if (!arm)
      {
         upperArm.setValue(false);
         lowerArm.setValue(false);
      }

      if (!override)
      {
         if ((winchValue < .1) && (winchValue > -.1))
         {
            winchValue = 0.0;
            if (!brakeEngaged)
            {
               winchRunning = false;
               System.out.println("Winch Stopped");
               stopDelay++;
               if (stopDelay == 3)
               {
                  brake.setValue(true);
                  brakeEngaged = true;
                  stopDelay = 0;
                  System.out.println("Brake Engaged");
               }
            }
         }
         else
         {
            if (!winchRunning)
            {
               winchValue = 0.0;
               brakeEngaged = false;
               System.out.println("Brake Disengaged");
               brake.setValue(false);
               startDelay++;
               if (startDelay == 3)
               {
                  winchRunning = true;
                  startDelay = 0;
                  System.out.println("Winch Started");
               }
            }

         }

         leftWinch.setValue(winchValue);
         rightWinch.setValue(winchValue);

      }
      else
      {
         brakeEngaged = true;
         winchRunning = false;
         brake.setValue(true);
         rightWinch.setValue(0.0);
         leftWinch.setValue(0.0);
      }
      if (!hook)
      {
         hooks.setValue(WsDoubleSolenoidState.REVERSE.ordinal());
      }
      else
      {
         hooks.setValue(WsDoubleSolenoidState.FORWARD.ordinal());
      }

      SmartDashboard.putBoolean("liftState", arm);
      SmartDashboard.putBoolean("hookState", hook);
      SmartDashboard.putNumber("Winch Value", rawWinchValue);
      SmartDashboard.putBoolean("Override", override);
      SmartDashboard.putBoolean("Winch Running", winchRunning);
      SmartDashboard.putBoolean("brakeEngaged", brakeEngaged);
      // SmartDashboard.putBoolean("Override", override);
      SmartDashboard.putBoolean("Right Arm", rightArmTouch);
      SmartDashboard.putBoolean("Left Arm", leftArmTouch);

   }

   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }

}
