
/**
 * Created by mhebt on 01/02/2019.
 */
public class ID_IF extends PipelineReg {
    public int binary;
    public int pc;


    void eval(Instruction instruction,int pc) {
        this.binary = instruction.binaryCode;
        this.pc = pc;
    }



    //TODO: Contstructor
}
