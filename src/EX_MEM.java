import java.util.Map;

/**
 * Created by mhebt on 01/02/2019.
 */
public class EX_MEM extends PipelineReg {
    public ControlLines controlLines =ControlLines._STALL_;
    public int pc;
    public boolean zero;
    public int ALUResult;
    public int readReg2;
    public int writeReg;


    public int Rt;
    public int Rd;
    public int Rs;
    public boolean branch;

    void eval(ID_EX id_ex, Instruction instruction, Registers registers, Map<String, Integer> labelsToAddresses) {
        controlLines = id_ex.controlLines;
        this.pc = id_ex.pc;           //JUMP IGNORANT

//        int alu1 = id_ex.readReg1;
//        int alu2 = this.controlLines.ALUSrc ? id_ex.immediate : id_ex.readReg2;
        int alu1 = registers.read(id_ex.Rs);
        int alu2 = this.controlLines.ALUSrc ? id_ex.immediate : registers.read(id_ex.Rt);

        this.zero = alu1 == alu2;
        //if hazard has happened:
        this.ALUResult = instruction.type.ALUCompute(alu1, alu2);

        this.readReg2 = registers.read(id_ex.Rt);
//        this.readReg2 = id_ex.readReg2;

        this.writeReg = this.controlLines.RegDest ? id_ex.Rd : id_ex.Rt;

        this.Rt = id_ex.Rt;
        this.Rd = id_ex.Rd;
        this.Rs = id_ex.Rs;

        this.branch = this.controlLines.Branch && this.zero;
//        else
//            this.pc +=4;
    }

    void exec(){}

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
