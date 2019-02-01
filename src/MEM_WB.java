/**
 * Created by mhebt on 01/02/2019.
 */
public class MEM_WB extends PipelineReg{
    public ControlLines controlLines;
    public int readMem;
    public int ALUResult;
    public int writeReg;

    void eval(EX_MEM ex_mem,Instruction instruction,int pc) {
        this.controlLines = ex_mem.controlLines;
        this.readMem =Memory.read(ex_mem.ALUResult);
        this.ALUResult = ex_mem.ALUResult;
        this.writeReg = ex_mem.writeReg;
//        int writeData = ex_mem.readReg2;

        //TODO: should it write in the mem or not ?
    }
}
