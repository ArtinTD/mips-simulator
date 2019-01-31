/**
 * Created by mhebt on 29/01/2019.
 */
public class Registers {
    private int[] registers = new int[32];
    int read(int index){
        return registers[index];
    }
    void write(int index,int value){
        registers[index]= value;
    }
}
