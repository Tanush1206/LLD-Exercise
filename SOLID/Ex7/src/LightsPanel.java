public class LightsPanel implements PowerDevice, LightDevice {
    public void powerOn(){

    }

    public void powerOff(){
        System.out.println("Lights OFF");
    }

    public void setBrightness(int pct){
        System.out.println("Lights brightness set to " + pct + "%");
    }
}
