/**
 * Created by mhebt on 29/01/2019.
 */
public enum Type {
    ADD {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 + alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    SUB {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 - alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    OR {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 | alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    AND {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 & alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    NOR {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return ~(alu1 | alu2);
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    SLT {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return (alu1 <= alu2 ? 0 : 1);
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._RTYPE_;
        }
    },
    BEQ {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            //TODO: is this correct  or it should be "alu1 < alu2  ? 1 : 0"       ?!
            return alu1 - alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._BEQ_;
        }
    },
    LW {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 + alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._LW_;
        }
    },
    SW {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 + alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._SW_;
        }
    },
    STALL{
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return 0;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._STALL_;
        }
    }
    ;



    public abstract int ALUCompute(int alu1, int alu2);

    public abstract ControlLines getControlLines();
}
