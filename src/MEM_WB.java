/**
 * Created by mhebt on 01/02/2019.
 */
public class MEM_WB extends PipelineReg{
    private final Memory memory;
    public ControlLines controlLines = ControlLines._STALL_;
    public int readMem;
    public int ALUResult;
    public int writeReg;


    public int Rt;
    public int Rd;
    public int Rs;

    private int writeData;

    public MEM_WB(Memory memory) {
        this.memory = memory;
    }

    void eval(EX_MEM ex_mem,Instruction instruction){
        this.controlLines = ex_mem.controlLines;
        this.readMem =memory.read(ex_mem.ALUResult);
        this.ALUResult = ex_mem.ALUResult;
        this.writeReg = ex_mem.writeReg;

        this.writeData = ex_mem.readReg2;

        //TODO: should it write in the mem or not ?
        this.Rd = ex_mem.Rd;
        this.Rs = ex_mem.Rs;
        this.Rt = ex_mem.Rt;
    }

    void exec(){
        if (this.controlLines.MemWrite)
            memory.write(ALUResult,writeData);
    }

    MEM_WB copy(){
        MEM_WB mem_wb = new MEM_WB(memory);
        mem_wb.controlLines = this.controlLines;
        mem_wb.readMem = this.readMem;
        mem_wb.ALUResult = this.ALUResult;
        mem_wb.writeReg = this.writeReg;
        return mem_wb;
    }
}
