/**
 * Created by mhebt on 01/02/2019.
 */
//Checked.
public class ControlLines {

    //ID_EX
    public boolean RegDest;
    public boolean ALUOp1;
    public boolean ALUOp0;
    public boolean ALUSrc;
    //EX_MEM
    public boolean Branch;
    public boolean MemRead;
    public boolean MemWrite;
    //MEM_WB
    public boolean RegWrite;
    public boolean MemToReg;

    //TODO: CHECKING STATIC INStANCES TO BE CORRECT
    // STATIC PREDEFINED CONTROLLINES
    static ControlLines _RTYPE_ = new ControlLines(true, true, false, false, false, false, false, true, false);
    static ControlLines _LW_ = new ControlLines(false, false, false, true, false, true, false, true, true);
    static ControlLines _SW_ = new ControlLines(false, false, false, true, false, false, true, false, false);
    static ControlLines _BEQ_ = new ControlLines(false, false, true, false, true, false, false, false, false);
    static ControlLines _STALL_ = new ControlLines(false,false,false,false,false,false,false,false,false);
    static ControlLines _ADDI_ = new ControlLines(false,false,false,true,false,false,false,true,false) ;
    public ControlLines(boolean regDest, boolean aluOp1, boolean aluOp0, boolean aluSrc, boolean branch, boolean memRead, boolean memWrite, boolean regWrite, boolean memToReg) {
        RegDest = regDest;
        ALUOp1 = aluOp1;
        ALUOp0 = aluOp0;
        ALUSrc = aluSrc;
        Branch = branch;
        MemRead = memRead;
        MemWrite = memWrite;
        RegWrite = regWrite;
        MemToReg = memToReg;
    }


}
