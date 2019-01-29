import java.util.HashMap;
import java.util.Map;

/**
 * Created by mhebt on 29/01/2019.
 */
public class Memory {
    private static Map<Integer,Integer> memory= new HashMap<Integer,Integer>();

    static int read(int address){
        Integer mem = memory.get(address);
        return mem != null ? mem.intValue() : 0 ;
    }

    static void write(int address, int value){
        memory.put(address,value);
    }
}
