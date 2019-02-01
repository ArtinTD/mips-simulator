
/**
 * Created by mhebt on 01/02/2019.
 */
public class EX_MEM extends PipelineReg {
    public ControlLines controlLines;
    public int pc;
    public boolean zero;
    public int ALUResult;
    public int readReg2;
    public int writeReg;


    void eval(ID_EX id_ex, Instruction instruction) {
        controlLines = id_ex.controlLines;
        this.pc = id_ex.pc;           //JUMP IGNORANT

        int alu1 = id_ex.readReg1;
        int alu2 = this.controlLines.ALUSrc ? id_ex.immediate : id_ex.readReg2;


        this.zero = alu1 == alu2;
        this.ALUResult = instruction.type.ALUCompute(alu1, alu2);

        this.readReg2 = id_ex.readReg2;

        this.writeReg = this.controlLines.RegDest ? id_ex.Rd : id_ex.Rt;
    }

    void exec(){
        //TODO: BRANCHING
    }

    EX_MEM copy(){
        EX_MEM ex_mem = new EX_MEM();
        ex_mem.controlLines = this.controlLines;
        ex_mem.pc = this.pc;
        ex_mem.zero = this.zero;
        ex_mem.ALUResult = this.ALUResult;
        ex_mem.readReg2 = this.readReg2;
        ex_mem.writeReg = this.writeReg;
        return ex_mem;
    }

    public void eval_forward() {
        //TODO:
    }
}
