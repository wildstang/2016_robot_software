package org.wildstang.yearly.auto.steps.intake;

import org.wildstang.framework.auto.steps.AutoStep;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.yearly.robot.WSInputs;
import org.wildstang.yearly.robot.WSSubsystems;
import org.wildstang.yearly.subsystems.Intake;

public class StepSetNoseState extends AutoStep
{
   private boolean deployed;
   public StepSetNoseState(boolean state)
   {
      this.deployed = state;
   }
   
   @Override
   public void initialize()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void update()
   {
      // TODO Auto-generated method stub
      if(((Intake)Core.getSubsystemManager().getSubsystem(WSSubsystems.INTAKE.getName())).isNoseDeployed() != deployed)
      {
      ((DigitalInput)Core.getInputManager().getInput(WSInputs.MAN_BUTTON_6.getName())).setValue(true);
      }
   }

   @Override
   public String toString()
   {
      // TODO Auto-generated method stub
      return "Nose position is " + deployed;
   }

}