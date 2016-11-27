package org.wildstang.yearly.robot;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputConfig;
import org.wildstang.framework.hardware.WsRemoteAnalogInputConfig;
import org.wildstang.framework.io.inputs.InputType;
import org.wildstang.hardware.JoystickConstants;
import org.wildstang.hardware.crio.inputs.WSInputType;
import org.wildstang.hardware.crio.inputs.config.WsDigitalInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsI2CInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsJSButtonInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsJSJoystickInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsMotionProfileConfig;

import edu.wpi.first.wpilibj.I2C;

public enum WSInputs implements Inputs
{
   // im.addSensorInput(LIDAR, new WsLIDAR());
   //

   // Driver Enums

   // Driver Joysticks
   DRV_THROTTLE("Driver throttle", WSInputType.JS_JOYSTICK,
         new WsJSJoystickInputConfig(0, JoystickConstants.LEFT_JOYSTICK_Y),
         getLogging()),
   DRV_HEADING("Driver heading", WSInputType.JS_JOYSTICK,
         new WsJSJoystickInputConfig(0, JoystickConstants.RIGHT_JOYSTICK_X),
         getLogging()),
   DRV_LEFT_X("Driver left X", WSInputType.JS_JOYSTICK,
         new WsJSJoystickInputConfig(0, JoystickConstants.LEFT_JOYSTICK_X),
         getLogging()),
   DRV_RIGHT_Y("Driver right Y", WSInputType.JS_JOYSTICK,
         new WsJSJoystickInputConfig(0, JoystickConstants.RIGHT_JOYSTICK_Y),
         getLogging()),

   // Driver DPAD Buttons
   DRV_DPAD_X_LEFT("Driver DPAD X Left", WSInputType.JS_DPAD_BUTTON,
	 new WsJSButtonInputConfig(0, JoystickConstants.DPAD_X_LEFT), getLogging()),
   DRV_DPAD_X_RIGHT("Driver DPAD X Right", WSInputType.JS_DPAD_BUTTON,
	 new WsJSButtonInputConfig(0, JoystickConstants.DPAD_X_RIGHT), getLogging()),
   DRV_DPAD_Y_UP("Driver DPAD Y Up", WSInputType.JS_DPAD_BUTTON,
	 new WsJSButtonInputConfig(0, JoystickConstants.DPAD_Y_UP), getLogging()),
   DRV_DPAD_Y_DOWN("Driver DPAD Y Down", WSInputType.JS_DPAD_BUTTON,
	 new WsJSButtonInputConfig(0, JoystickConstants.DPAD_Y_DOWN), getLogging()),

   // Driver Buttons
   DRV_BUTTON_1("Driver button 1", WSInputType.JS_BUTTON, 
	 new WsJSButtonInputConfig(0, 0), getLogging()),
   DRV_BUTTON_2("Driver button 2", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 1), getLogging()),
   DRV_BUTTON_3("Driver button 3", WSInputType.JS_BUTTON, 
	 new WsJSButtonInputConfig(0, 2), getLogging()),
   DRV_BUTTON_4("Driver button 4", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 3), getLogging()),

   DRV_BUTTON_5("Driver Turret Mode", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 4), getLogging()),
   DRV_BUTTON_6("Driver Shift", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 5), getLogging()),

   DRV_BUTTON_7("Driver button 7", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 6), getLogging()),
   DRV_BUTTON_8("Driver Turbo", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 7), getLogging()),

   DRV_BUTTON_9("Driver button 9", WSInputType.JS_BUTTON,
	 new WsJSButtonInputConfig(0, 8), getLogging()),
   DRV_BUTTON_10("Driver button 10", WSInputType.JS_BUTTON,
	 new WsJSButtonInputConfig(0, 9), getLogging()),
   DRV_BUTTON_11("Driver button 11", WSInputType.JS_BUTTON,
	 new WsJSButtonInputConfig(0, 10), getLogging()),

   DRV_BUTTON_12_PG("Pistol Grip", WSInputType.JS_BUTTON,
         new WsJSButtonInputConfig(0, 11), getLogging()),


   // Manipulator Enums
   
   //// Manipulator Joysticks
   //MAN_LEFT_JOYSTICK_X("Manip Left Joystick X", WSInputType.JS_JOYSTICK,
   //      new WsJSJoystickInputConfig(1, JoystickConstants.LEFT_JOYSTICK_X), getLogging()),
   //MAN_LEFT_JOYSTICK_Y("Manip Left Joystick Y", WSInputType.JS_JOYSTICK,
   //      new WsJSJoystickInputConfig(1, JoystickConstants.LEFT_JOYSTICK_Y), getLogging()),
   //MAN_RIGHT_JOYSTICK_X("Manip Right Joystick X", WSInputType.JS_JOYSTICK,
   //      new WsJSJoystickInputConfig(1, JoystickConstants.RIGHT_JOYSTICK_X), getLogging()),
   //MAN_RIGHT_JOYSTICK_Y("Manip Right Joystick Y", WSInputType.JS_JOYSTICK,
   //      new WsJSJoystickInputConfig(1, JoystickConstants.RIGHT_JOYSTICK_Y), getLogging()),
   //
   //// Manipulator DPAD Buttons
   //MAN_DPAD_X_LEFT("Manip DPAD X Left", WSInputType.JS_DPAD_BUTTON,
   //      new WsJSButtonInputConfig(1, JoystickConstants.DPAD_X_LEFT), getLogging()),
   //MAN_DPAD_X_RIGHT("Manip DPAD X Right", WSInputType.JS_DPAD_BUTTON,
   //      new WsJSButtonInputConfig(1, JoystickConstants.DPAD_X_RIGHT), getLogging()),
   //MAN_DPAD_Y_UP("Manip DPAD Y Up", WSInputType.JS_DPAD_BUTTON,
   //      new WsJSButtonInputConfig(1, JoystickConstants.DPAD_Y_UP), getLogging()),
   //MAN_DPAD_Y_DOWN("Manip DPAD Y Down", WSInputType.JS_DPAD_BUTTON,
   //      new WsJSButtonInputConfig(1, JoystickConstants.DPAD_Y_DOWN), getLogging()),
   //
   //// Manipulator Buttons
   //MAN_BUTTON_1("Manip Deploy Climber Arm", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 0), getLogging()),
   //MAN_BUTTON_2("Manip Deploy Climber Hook", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 1), getLogging()),
   //MAN_BUTTON_3("Manip Flywheel on_off", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 2), getLogging()),
   //MAN_BUTTON_4("Manip Shot Distance", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 3), getLogging()),
   //MAN_BUTTON_5("Manip Intake Nose", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 4), getLogging()),
   //MAN_BUTTON_6("Manip Shooter Hood", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 5), getLogging()),
   //MAN_BUTTON_7("Manip Deploy Intake", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 6), getLogging()),
   //MAN_BUTTON_8("Manip Shoot", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 7), getLogging()),
   //MAN_BUTTON_9("Override", WSInputType.JS_BUTTON,
   //      new WsJSButtonInputConfig(1, 8), getLogging()),
   //MAN_BUTTON_10("Manipulator button 10", WSInputType.JS_BUTTON, 
   //      new WsJSButtonInputConfig(1, 9), getLogging()),
   //MAN_BUTTON_11("Manipulator button 11", WSInputType.JS_BUTTON, 
   //      new WsJSButtonInputConfig(1, 10), getLogging()),
   //MAN_BUTTON_12("Manipulator button 12", WSInputType.JS_BUTTON, 
   //      new WsJSButtonInputConfig(1, 11), getLogging()),

   // Digital IOs
   TEST_SWITCH_SENSOR("Test Switch Sensor", WSInputType.SWITCH, 
	 new WsDigitalInputConfig(9, true), getLogging()),	// Digital IO Channel 9


   IMU("IMU", WSInputType.I2C, 
         new WsI2CInputConfig(I2C.Port.kMXP, 0x20), getLogging()),

   TARGET_BOTTOM("Target Bottom", WSInputType.REMOTE_ANALOG,
         new WsRemoteAnalogInputConfig("remoteIO"), getLogging()),
   TARGET_CENTER("Target Center", WSInputType.REMOTE_ANALOG,
         new WsRemoteAnalogInputConfig("remoteIO"), getLogging()),
   VISION_ANGLE("Vision Angle", WSInputType.REMOTE_ANALOG,
         new WsRemoteAnalogInputConfig("remoteIO"), getLogging()),
   VISION_DISTANCE("Vision Distance", WSInputType.REMOTE_ANALOG,
         new WsRemoteAnalogInputConfig("remoteIO"), getLogging()),

//   INTAKE_BOLDER_SENSOR("Intake Ball Staging", WSInputType.SWITCH,
//	 new WsDigitalInputConfig(8, false), getLogging()),
//   TEST_SWITCH_SENSOR("Test Switch Sensor", WSInputType.SWITCH,
//	 new WsDigitalInputConfig(6, false), getLogging());
   // INTAKE_BALL_DETECT("Intake ball detection", WSInputType.SWITCH, new
   // WsDigitalInputConfig(9, false), getLogging()),
   //RIGHT_ARM_TOUCHING("Right Lift arm touching", WSInputType.SWITCH,
   //      new WsDigitalInputConfig(7, false), getLogging()),
   //LEFT_ARM_TOUCHING("Left Lift arm touching", WSInputType.SWITCH,
   //      new WsDigitalInputConfig(6, false), getLogging());

   // LEFT_DRIVE_ENCODER
   // RIGHT_DRIVER_ENCODER
   // SHOOTER_ENCODER
   // DIO_0_INTAKE_SENSOR("intake sensor", WSInputType.SWITCH, new
   // WsDigitalInputConfig(6, true), getLogging());
   MOTION_PROFILE_CONTROL("MotionProfileConfig",
	 WSInputType.MOTION_PROFILE_CONTROL, new WsMotionProfileConfig(), getLogging());

   private final String m_name;
   private final InputType m_type;

   private InputConfig m_config = null;

   private boolean m_trackingState;

   private static boolean isLogging = false;

   WSInputs(String p_name, InputType p_type, InputConfig p_config,
         boolean p_trackingState)
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
   public InputType getType()
   {
      return m_type;
   }

   public InputConfig getConfig()
   {
      return m_config;
   }

   public boolean isTrackingState()
   {
      return m_trackingState;
   }

   public static boolean getLogging()
   {
      return isLogging;
   }

}
