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

    
    void eval(IF_ID id_if, Instruction i, Registers registers) {
        this.controlLines = i.type.getControlLines();
        this.pc = id_if.pc;
        this.readReg1 = registers.read(i.Rs);
        this.readReg2 = registers.read(i.Rt);
        this.Rt = i.Rt;
        this.Rd = i.Rd;
        this.immediate = i.immediate;
        this.funct = i.funct;
    }
    void exec(){}

    ID_EX copy(){
        ID_EX id_ex = new ID_EX();
        id_ex.controlLines = this.controlLines;
        id_ex.pc = this.pc;
        id_ex.readReg1 = this.readReg1;
        id_ex.readReg2 = this.readReg2;
        id_ex.Rt = this.Rt;
        id_ex.Rd= this.Rd;
        id_ex.immediate = this.immediate;
        id_ex.funct = this.funct;

        return id_ex;
    }
 }
