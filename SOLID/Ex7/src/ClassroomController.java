public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerController pjPower = reg.getByCapabilities(PowerController.class);
        InputController pjInput = reg.getByCapabilities(InputController.class);
        pjPower.powerOn();
        pjInput.connectInput("HDMI-1");

        BrightnessController lights = reg.getByCapabilities(BrightnessController.class);
        lights.setBrightness(60);

        TempController ac = reg.getByCapabilities(TempController.class);
        ac.setTemperatureC(24);

        AttendanceScanner scan = reg.getByCapabilities(AttendanceScanner.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getByCapabilities(Projector.class).powerOff();
        reg.getByCapabilities(LightsPanel.class).powerOff();
        reg.getByCapabilities(AirConditioner.class).powerOff();
    }
}
