import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by mhebt on 29/01/2019.
 */

//TODO: comment handling : Trimming
//TODO: exception handling : invalid type or arguments

public class ParseString {
    public int[] args;
    public Type type;
    public String label;
    String rType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+";
    String iType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[0-9]+";
    String jType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[a-zA-Z]+";
    String lsType= "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[0-9]+[\\(][$][a-zA-Z0-9]+[\\)]";

    ParseString(String str){
        str = str.trim();

        String[] split = str.split("[\\s()$,]+");

        this.type = Type.valueOf(split[0].toUpperCase());


            args = new int[split.length-1];

            for (int i = 1; i < split.length; i++) {
//            TODO: EXCEPTION HANDLING : INVALID ARGUMENTS
                if (map.containsKey(split[i]))
                    args[i-1] = map.get(split[i]);
                else
                    try {
                        args[i-1] = Integer.parseInt(split[i]);
                    } catch (NumberFormatException e) {
                        continue;
                    }
            }
            this.label = split[split.length-1];
    }
    private static Map<String,Integer> map = new HashMap<String,Integer>() {{
        int j = 0;
        put("zero",j++ );
        put("at",j++ );
        put("v0",j++);
        put("v1",j++);
        for (int i = 0; i <= 3; i++) {
            String s = "a" + i;
            put(s,j++);
        }
        for (int i = 0; i <= 7; i++) {
            String s = "t" + i;
            put(s,j++);
        }
        for (int i = 0; i <= 7; i++) {
            String s = "s" + i;
            put(s,j++);
        }
        for (int i = 8; i <= 9; i++) {
            String s = "t" + i;
            put(s,j++);
        }
        put("k0",j++);
        put("k1",j++);
        put("gp",j++);
        put("sp",j++);
        put("fp",j++);
        put("ra",j++);
    }};
}
