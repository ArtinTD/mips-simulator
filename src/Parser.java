import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mhebt on 11/02/2019.
 */
public class Parser {
    ArrayList<String> lines;
    Map<String, Integer> labelToAddress = new HashMap<String, Integer>();
    ArrayList<Instruction> instructions = new ArrayList<Instruction>();
    ArrayList<String> errors = new ArrayList<String>();

    Parser(String[] code) {
        for (int i = 0; i < code.length; i++) {
            code[i] = code[i].split("#")[0].trim();
        }
        lines = new ArrayList(Arrays.asList(code));
        lines.add("STALL");

//        removeEmptyLines();
        parseLines();
        stallOnBeq();
    }

    private void stallOnBeq() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).type == Type.BEQ) {
                instructions.add(i + 1, Instruction._STALL_);
                instructions.add(i + 1, Instruction._STALL_);
                instructions.add(i + 1, Instruction._STALL_);
            }

            if (instructions.get(i).type == Type.LW && i + 1 < instructions.size()) {
                Instruction a = instructions.get(i);
                Instruction b = instructions.get(i + 1);

                if (b.type.species() == Species.I) {
                    if (b.Rs == a.Rt)
                        instructions.add(i + 1, Instruction._STALL_);
                } else {
                    if (b.Rs == a.Rt || b.Rt == a.Rt)
                        instructions.add(i + 1, Instruction._STALL_);
                }
            }

        }
    }

    void parseLines() {

        String label = "[a-zA-z0-9]+:$";
        //add     1,2,3
        String rType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+";
        String iType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[0-9]+";
        String jType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[a-zA-Z][a-zA-Z0-9]*";
        String lsType = "[a-zA-z]+[\\s]+[$][a-zA-Z0-9]+[\\s]*[,][\\s]*[0-9]+[(][$][a-zA-Z0-9]+[)]";
        Pattern labelPattern = Pattern.compile(label);
        Pattern rTypePattern = Pattern.compile(rType);
        Pattern iTypePattern = Pattern.compile(iType);
        Pattern jTypePattern = Pattern.compile(jType);
        Pattern lsTypePattern = Pattern.compile(lsType);

        boolean hasLabel = false;
        String inpLabel = null;
        for (int i = 0; i < lines.size(); i++) {
            String strL;
            String str;
            if (lines.get(i).matches("[a-zA-Z0-9]+:.+")) {
                String[] split = lines.get(i).split(":");
                strL = split[0]+":";
                str = split[1].trim();
            } else {
                strL = lines.get(i);
                str = lines.get(i);
            }
            boolean lm = labelPattern.matcher(strL).matches();
            boolean rm = rTypePattern.matcher(str).matches();
            boolean im = iTypePattern.matcher(str).matches();
            boolean jm = jTypePattern.matcher(str).matches();
            boolean lsm = lsTypePattern.matcher(str).matches();


            if (lm) {
//                labelToAddress.put(str.substring(0, str.length() - 1), 4 * (i + 1));
                hasLabel = true;
                inpLabel = strL.substring(0, strL.length() - 1);
            }
            if (rm || im || jm || lsm) {
                Instruction instruction = new Instruction(str);
                Type t = instruction.type;
                if (str.matches(rType) && t.species() != Species.R)
                    errors.add("Incorrect arguments:" + i + ":\t " + str);
                else if (str.matches(iType) && t.species() != Species.I)
                    errors.add("Incorrect arguments:" + i + ":\t " + str);
                else if (str.matches(jType) && t.species() != Species.J)
                    errors.add("Incorrect arguments:" + i + ":\t " + str);
                else if (str.matches(lsType) && t.species() != Species.LS)
                    errors.add("Incorrect arguments:" + i + ":\t " + str);


                instructions.add(instruction);
                if (hasLabel) {
                    hasLabel = false;
                    instruction.inpLabel = inpLabel;
                }
            } else if (str.equals("STALL")) {
               Instruction instruction = Instruction._STALL_;
                instructions.add(instruction);
                if (hasLabel) {
                    hasLabel = false;
                    instruction.inpLabel = inpLabel;
                }

            }else if (str.equals("") || lm)
                continue;
            else {
                errors.add("Instruction not defined at line:" + i + ":\t " + str);
            }
        }

//        Pattern pattern = Pattern.compile(patternString);
//
//        Matcher matcher = pattern.matcher(text);
//        boolean matches = matcher.matches();
    }


}
