/**
 * Created by ASUS on 1/31/2019.
 */
public class ALU {
    public static int compute(Type type, Instruction instruction, Registers registers) {
        switch(type){
            // Rtype $rd,$rs,$rt
            case ADD:
                int reg1 = registers.read( instruction.Rs );
                int reg2 = registers.read( instruction.Rt );
                return reg1+ reg2;
            case SUB:
                reg1= registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return reg1 - reg2;
            case AND:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return reg1 & reg2;
            case OR:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return reg1 | reg2;
            case NOR:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return ~(reg1 | reg2);
            case SLT:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return (reg1 <= reg2 ? 0 : 1);
            case BEQ:
                reg1 = registers.read( instruction.Rs );
                reg2 = registers.read( instruction.Rt );
                return (reg1 == reg2 ? 1 : 0);
            case LW:
                // TODO: 1/31/2019
            case SW:
                // TODO: 1/31/2019
        }

        return -1;
    }

}

