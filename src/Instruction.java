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
    String label = null;

    Instruction(String code){
        this.code = code;
        decode();
    }
//    TODO
void decode(){
        ParseString ps = new ParseString(this.code);
        //TODO  catching the exception : IllegalArgumentException
        this.type = ps.type;
        switch(type){
            // Rtype $rd,$rs,$rt
            case ADD:
            case SUB:
            case AND:
            case OR:
            case NOR:
            case SLT:
                this.Rd = ps.args[0];
                this.Rs = ps.args[1];
                this.Rt = ps.args[2];
                break;
            // Itype $rs,$rd,immediate
            case BEQ:
                this.Rs = ps.args[0];
                this.Rt = ps.args[1];
                this.immediate = ps.args[2];
                break;

            // I Type lw $rt,immediate($rs)
            case LW:
            case SW:
                this.Rt = ps.args[0];
                this.immediate= ps.args[1];
                this.Rs = ps.args[2];
//                TODO
                break;
//                , and, or,nor,slt,beq,lw,swadd, sub, and, or,nor,slt,beq,lw,sw
        }
    }


//     Examples:
//    lw     $t0, 4($gp)
//    add   $t0, $t0, $t0
//    lw     $t1, 4($gp)
//    mult   $t1, $t1, $t2
//    add    $t2, $t0, $t1
//    sw     $t2, 0($gp)
}
