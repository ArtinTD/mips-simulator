/**
 * Created by mhebt on 29/01/2019.
 */
//TODO: completing remained parts
public class Instruction {
    int Rs = 0;
    int Rt = 0;
    int Rd = 0;
    int immediate = 0;
    String code;
    int binaryCode = 0;
    public Type type;
    String label = null;
    int funct = 0;


    static Instruction _STALL_ = new Instruction("Stall 0 0 0");
    //TODO FUNCT + INSTRUCTION COMPLETE DECODE;
    Instruction(String code) {
        this.code = code;
        decode();
    }

    //    TODO
    void decode() {
        ParseString ps = new ParseString(this.code);
        //TODO  catching the exception : IllegalArgumentException
        funct = 0;
        binaryCode = 0;
        //End of TODO
        this.type = ps.type;
        switch (type) {
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
                this.immediate = ps.args[1];
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
