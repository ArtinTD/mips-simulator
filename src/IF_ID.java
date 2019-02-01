
/**
 * Created by mhebt on 01/02/2019.
 */
public class IF_ID extends PipelineReg {
    public int binary;
    public int pc;


    void eval(Instruction instruction,int pc) {
        this.binary = instruction.binaryCode;
        this.pc = pc;
    }

    void exec(){
        this.pc +=4;
    }

    IF_ID copy(){
        IF_ID ret= new IF_ID();
        ret.binary = this.binary;
        ret.pc = this.pc;

        return ret;
    }
    //TODO: Contstructor
}
