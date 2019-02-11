import java.util.HashMap;
import java.util.Map;

/**
 * Created by mhebt on 29/01/2019.
 */
public class Memory {
    private Map<Integer,Integer> memory= new HashMap<Integer,Integer>();

    int read(int address){
        Integer mem = memory.get(address);
        return mem != null ? mem.intValue() : 0 ;
    }

    void write(int address, int value){
        memory.put(address,value);
    }
    Map<Integer,Integer> getMemory(){
        return memory;
    }
}
