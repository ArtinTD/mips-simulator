/**
 * Created by mhebt on 29/01/2019.
 */
public class Instruction {
    int Rs;
    int Rt;
    int Rd;
    int immediate;
    String code;
    int binaryCode;
    Type type;

    Instruction(String code){
        this.code = code;
        decode();
    }
//    TODO
    private void decode(){
        String[] tokens = code.trim().split("[()\\$,\\s]+");
        //TODO  catching the exception : IllegalArgumentException
        type = Type.valueOf(tokens[0].toUpperCase());
        switch(type){
            case ADD:
            case SUB:
            case AND:
            case OR:
            case NOR:
            case SLT:
                this.Rd = Integer.parseInt(tokens[1]);
                this.Rs = Integer.parseInt(tokens[2]);
                this.Rt = Integer.parseInt(tokens[3]);
                break;
            case BEQ:
                this.Rs = Integer.parseInt(tokens[1]);
                this.Rt = Integer.parseInt(tokens[2]);
                this.immediate = Integer.parseInt(tokens[3]);
                break;
            case LW:
            case SW:
//                TODO
                break;


//                , and, or,nor,slt,beq,lw,swadd, sub, and, or,nor,slt,beq,lw,sw
        }
    }
}
