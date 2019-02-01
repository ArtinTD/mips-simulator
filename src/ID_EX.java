import java.nio.channels.Pipe;

/**
 * Created by mhebt on 01/02/2019.
 */
 class ID_EX extends PipelineReg{
     public ControlLines controlLines;
     public int pc;
     public int readReg1;
     public int readReg2;
     public int Rt;
     public int Rd;
     public int immediate;
     public int funct;

    
    void eval(Instruction i, Registers registers,int pc) {
        this.controlLines = new ControlLines(i);
        this.pc = pc;
        this.readReg1 = registers.read(i.Rs);
        this.readReg2 = registers.read(i.Rt);
        this.Rt = i.Rt;
        this.Rd = i.Rd;
        this.immediate = i.immediate;
        this.funct = i.funct;
    }


//TODO: Contstructor
}
