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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.R;
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

        @Override
        public Species species() {
            return Species.J;
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

        @Override
        public Species species() {
            return  Species.LS;
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

        @Override
        public Species species() {
            return  Species.LS;
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

        @Override
        public Species species() {
            return null;
        }
    },
    ADDI {
        @Override
        public int ALUCompute(int alu1, int alu2) {
            return alu1 + alu2;
        }

        @Override
        public ControlLines getControlLines() {
            return ControlLines._ADDI_;
        }

        @Override
        public Species species() {
            return Species.I;
        }
    };



    public abstract int ALUCompute(int alu1, int alu2);

    public abstract ControlLines getControlLines();

    public abstract Species species();
}
