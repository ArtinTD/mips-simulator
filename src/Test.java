import javafx.scene.media.MediaPlayer;

/**
 * Created by mhebt on 29/01/2019.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Memory.read(0));
        Memory.write(0,10);
        System.out.println(Memory.read(0));


        String str = "      add          $a,  $b,        $c,$d(e)";
        ParseString.parseString(str);

    }


}
