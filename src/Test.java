import javafx.scene.media.MediaPlayer;

/**
 * Created by mhebt on 29/01/2019.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Memory.read(0));
        Memory.write(0,10);
        System.out.println(Memory.read(0));

//     Examples:
//    lw     $t0, 4($gp)
//    add   $t0, $t0, $t0
//    lw     $t1, 4($gp)
//    mult   $t1, $t1, $t2
//    add    $t2, $t0, $t1
//    sw     $t2, 0($gp)
        String str = "lw     $t0, 4($gp)";
        Instruction inst = new Instruction(str);
        System.out.println("Type:" + inst.type);
        System.out.println("Rs" + inst.Rs);
        System.out.println("Rd"+inst.Rd);
        System.out.println("Rt"+inst.Rt);
        System.out.println("imm"+inst.immediate);
    }


}
