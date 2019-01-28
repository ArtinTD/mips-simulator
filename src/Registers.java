/**
 * Created by mhebt on 29/01/2019.
 */
public class Registers {
    private static int[] registers = new int[32];
    static int read(int index){
        return registers[index];
    }
    static void write(int index,int value){
        registers[index]= value;
    }
}
