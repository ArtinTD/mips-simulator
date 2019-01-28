import javafx.scene.media.MediaPlayer;

/**
 * Created by mhebt on 29/01/2019.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Memory.readMem(0));
        Memory.writeMem(0,10);
        System.out.println(Memory.readMem(0));
    }


}
