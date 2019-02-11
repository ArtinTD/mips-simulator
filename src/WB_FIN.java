/**
 * Created by mhebt on 01/02/2019.
 */
public class WB_FIN extends PipelineReg {

    ControlLines controlLines =ControlLines._STALL_;
    private int writeData;
    private int writeRegister;

    void eval(MEM_WB mem_wb, Registers registers){
        this.controlLines = mem_wb.controlLines;
        int readMem = mem_wb.readMem;
        int ALUResult = mem_wb.ALUResult;
        writeData = this.controlLines.MemToReg ? readMem : ALUResult;
        writeRegister = mem_wb.writeReg;
    }
    void exec(Registers registers){
        if (controlLines.RegWrite){
            registers.write(writeRegister,writeData);
        }
    }

    WB_FIN copy(){return null;}
}
