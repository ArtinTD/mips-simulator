import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 1/31/2019.
 */
public class Controller {
    private static final int INITIAL_PC = 1000;
    Map<Integer, Instruction> instructions;
    Map<String, Integer> labelToAddress;
    int cycleNumber;
    ArrayDeque<Integer> pipelinePc;
    ArrayList<Instruction> piplineInstructions;

    Registers registers = new Registers();
    Registers forwardingRegisters = new Registers();

    IF_ID if_id;
    ID_EX  id_ex;
    EX_MEM ex_mem;
    MEM_WB mem_wb;
    WB_FIN wb_fin;

    public Controller(ArrayList<Instruction> instructions) {
        this.instructions = new HashMap<>();
        this.labelToAddress = new HashMap<>();

        for (int i = 0; i < instructions.size(); i++) {
            this.instructions.put(4 * i + INITIAL_PC, instructions.get(i));
            if(instructions.get(i).label != null)
                labelToAddress.put(instructions.get(i).label, 4 * i + INITIAL_PC);
        }
    }

    public void execute(){
        pipelinePc = new ArrayDeque<>();
        piplineInstructions = new ArrayList<>();

        if_id = new IF_ID();
        id_ex = new ID_EX();
        ex_mem = new EX_MEM();
        mem_wb = new MEM_WB();
        wb_fin = new WB_FIN();


        pipelinePc.addLast(INITIAL_PC);

        do{
            int pc = pipelinePc.getLast();
            if(instructions.containsKey(pc))
                piplineInstructions.add(instructions.get(pc));

            while(piplineInstructions.size() > 5) {
                piplineInstructions.remove(0);
                pipelinePc.pollFirst();
            }

            IF_ID copy_if_id = if_id.copy();
            ID_EX copy_id_ex = id_ex.copy();
            EX_MEM copy_ex_mem = ex_mem.copy();
            MEM_WB copy_mem_wb = mem_wb.copy();

            for (int i = 0; i < piplineInstructions.size(); i++) {
                if (piplineInstructions.get(i) == null) // Stall
                //TODO: pipeline registers
                    continue;
                Instruction instruction = piplineInstructions.get(i);
                switch (i){
                    //FIXME: THIS IS WRITTEN TO JUST GIVE AN IDEA
                    case 0:
                        instruction.decode();
                        if_id.eval(instruction,pc);
                        // Instruction fetch
                        // nothing to do?
                    case 1: // Instruction decode
//                        instruction.decode();
                        id_ex.eval(copy_if_id,instruction,forwardingRegisters);
                    case 2: // ALU
//                        int aluReturn = ALU.compute(instruction, forwardingRegisters);
                        //TODO: FORWARDING
                        boolean forward = false;
                        if (!forward)
                            ex_mem.eval(copy_id_ex,instruction);
                        else
                            ex_mem.eval_forward();
                    case 3: // Memory read and write + pc update
                        mem_wb.eval(copy_ex_mem,instruction);
                    case 4: // Register write?
                        wb_fin.eval(copy_mem_wb,forwardingRegisters);


                }
            }
        } while (!piplineInstructions.isEmpty());
    }

}
