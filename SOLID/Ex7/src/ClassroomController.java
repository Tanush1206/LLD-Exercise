public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerDevice projectorPower = reg.getDevice(Projector.class);
        projectorPower.powerOn() ; 

        MediaDevice projector = reg.getDevice(Projector.class);
        projector.connectInput("HDMI") ;

        LightDevice light = reg.getDevice(LightsPanel.class);
        light.setBrightness(100) ;

        TemperatureDevice ac = reg.getDevice(AirConditioner.class);
        ac.setTemperatureC(22) ;

        AttendanceDevice attendanceScanner = reg.getDevice(AttendanceScanner.class);
        System.out.println("Attendance scanned: present=" + attendanceScanner.scanAttendance()) ;
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getDevice(Projector.class).powerOff();
        reg.getDevice(LightsPanel.class).powerOff();
        reg.getDevice(AirConditioner.class).powerOff();
    }
}
