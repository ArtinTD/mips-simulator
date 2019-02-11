/**
 * Created by ASUS on 1/31/2019.
 */
public class ALU {
    public static int compute(Instruction instruction, Registers registers, Memory memory) {
        //Removed type from arguments
        Type type = instruction.type;
        int reg1,reg2,imd = 0;
        switch(type){
            // Rtype $rd,$rs,$rt
            case ADD:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, reg1 + reg2);
                return reg1+ reg2;
            case SUB:
                reg1= registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, reg1 - reg2);
                return reg1 - reg2;
            case AND:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, reg1 & reg2);
                return reg1 & reg2;
            case OR:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, reg1 | reg2);
                return reg1 | reg2;
            case NOR:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, ~(reg1 | reg2));
                return ~(reg1 | reg2);
            case SLT:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                registers.write(instruction.Rd, (reg1 <= reg2 ? 0 : 1));
                return (reg1 <= reg2 ? 0 : 1);
            case BEQ:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return (reg1 == reg2 ? 1 : 0);
            case LW:
                reg1 = registers.read( instruction.Rs );
                imd = instruction.immediate;
                registers.write(instruction.Rt,memory.read(reg1 + imd));

                return reg1 + imd;
            case SW:
                reg1 = registers.read( instruction.Rs );
                imd = instruction.immediate;
                return reg1 + imd;
            case ADDI:
                reg1 = registers.read( instruction.Rs );
                imd = instruction.immediate;
                registers.write(instruction.Rt, (reg1 + imd));
                return reg1 + imd;
        }
        return -1;
    }

}

