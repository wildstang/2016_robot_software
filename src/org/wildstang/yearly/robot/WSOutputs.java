package org.wildstang.yearly.robot;

// expand this and edit if trouble with Ws
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.framework.io.outputs.OutputType;
import org.wildstang.hardware.crio.outputs.WSOutputType;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoidState;
import org.wildstang.hardware.crio.outputs.config.WsDoubleSolenoidConfig;
import org.wildstang.hardware.crio.outputs.config.WsDigitalOutputConfig;
import org.wildstang.hardware.crio.outputs.config.WsI2COutputConfig;
import org.wildstang.hardware.crio.outputs.config.WsSolenoidConfig;
import org.wildstang.hardware.crio.outputs.config.WsVictorConfig;

import edu.wpi.first.wpilibj.I2C;

public enum WSOutputs implements Outputs
{
   FRONT_LEFT("Front left drive",            WSOutputType.VICTOR,    new WsVictorConfig(0, 0.0), true),
   FRONT_RIGHT("Front right drive",          WSOutputType.VICTOR,    new WsVictorConfig(1, 0.0), true),
   REAR_LEFT("Rear left drive",              WSOutputType.VICTOR,    new WsVictorConfig(2, 0.0), true),
   REAR_RIGHT("Rear right drive",            WSOutputType.VICTOR,    new WsVictorConfig(3, 0.0), true),
   FRONT_LEFT_ROT("Front left rotation",     WSOutputType.VICTOR,    new WsVictorConfig(4, 0.0), true),
   FRONT_RIGHT_ROT("Front right rotation",   WSOutputType.VICTOR,    new WsVictorConfig(5, 0.0), true),
   REAR_LEFT_ROT("Rear left rotation",       WSOutputType.VICTOR,    new WsVictorConfig(6, 0.0), true),
   REAR_RIGHT_ROT("Rear right rotation",     WSOutputType.VICTOR,    new WsVictorConfig(7, 0.0), true),
   FRONT_ROLLER("Front intake roller",     WSOutputType.VICTOR,    new WsVictorConfig(8, 0.0), true),
   INTAKE_ROLLER("Front intake roller",     WSOutputType.VICTOR,    new WsVictorConfig(9, 0.0), true),

   LED("LEDs", WSOutputType.I2C, new WsI2COutputConfig(I2C.Port.kOnboard, 0x10), true),

   // Solenoids
   DOUBLE("Double solenoid", WSOutputType.SOLENOID_DOUBLE, new WsDoubleSolenoidConfig(1, 0, 1, WsDoubleSolenoidState.FORWARD), true),
   SINGLE("Single solenoid", WSOutputType.SOLENOID_SINGLE, new WsSolenoidConfig(1, 2, false), true),
   
   // DIO Outputs
   PNUMATIC_1_LED("Back pnumatic LED", WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(2, false), true),
   PNUMATIC_2_LED("Front pnumatic LED", WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(3, false), true),
   PNUMATIC_1("Back pnumatic", WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(4, false), true),
   PNUMATIC_2("Front pnumatic", WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(5, false), true),
   DIO_LED_0("LED 0",             WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(7, false), true),
   SENSOR_LED_1("Sensor LED",       WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(8, false), true),
   FRONT_ROLLER_LED_2("Front roller LED", WSOutputType.DIGITAL_OUTPUT,    new WsDigitalOutputConfig(9, false), true);
   
   private String m_name;
   private OutputType m_type;
   private OutputConfig m_config;
   private boolean m_trackingState;

   WSOutputs(String p_name, OutputType p_type, OutputConfig p_config, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_config = p_config;
      m_trackingState = p_trackingState;
   }
   
   
   @Override
   public String getName()
   {
      return m_name;
   }
   
   @Override
   public OutputType getType()
   {
      return m_type;
   }
   
   public OutputConfig getConfig()
   {
      return m_config;
   }

   public boolean isTrackingState()
   {
      return m_trackingState;
   }

}
