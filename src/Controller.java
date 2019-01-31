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
        // TODO: 1/31/2019 Other Pipeline registers

        pipelinePc.addLast(INITIAL_PC);

        do{
            int pc = pipelinePc.getLast();
            if(instructions.containsKey(pc))
                piplineInstructions.add(instructions.get(pc));

            while(piplineInstructions.size() > 5) {
                piplineInstructions.remove(0);
                pipelinePc.pollFirst();
            }

            for (int i = 0; i < piplineInstructions.size(); i++) {
                if (piplineInstructions.get(i) == null) // Stall
                    continue;
                Instruction instruction = piplineInstructions.get(i);
                switch (i){
                    case 0: // Instruction fetch
                        // nothing to do?
                    case 1: // Instruction decode
                        instruction.decode();
                    case 2: // ALU
                        int aluReturn = ALU.compute(instruction.type, instruction, forwardingRegisters);

                    case 3: // Memory read and write + pc update

                    case 4: // Register write?
                }
            }
        } while (!piplineInstructions.isEmpty());
    }
}
