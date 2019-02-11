/**
 * Created by mhebt on 29/01/2019.
 */
public class Registers {
    private int[] registers = new int[32];

    int read(int index) {
        return registers[index];
    }

    void write(int index, int value) {
        if (index != 0)
            registers[index] = value;

    }

    Registers copy() {
        Registers ret = new Registers();
        for (int i = 0; i < 32; i++) {
            ret.write(i, this.read(i));
        }
        return ret;
    }
}
