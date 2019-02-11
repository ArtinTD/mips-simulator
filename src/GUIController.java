import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    private final String[] REG_NAMES = {"zero", "at", "v0", "v1",
            "a0", "a1", "a2", "a3", "t0", "t1", "t2",
            "t3", "t4", "t5", "t6", "t7", "s0", "s1",
            "s2", "s3", "s4", "s5", "s6", "s7", "t8",
            "t9", "k0", "k1", "gp", "sp", "fp", "ra"};

    private Controller mipsController;
    private int cycleNumber = 0;
    private ArrayList<Instruction> instructions;
    @FXML
    private TableView<Integer> registers = new TableView<>();

    @FXML
    private TableView<Integer> memory = new TableView<>();

    @FXML
    private TextArea code = new TextArea();

    @FXML
    private TextField clockCycle = new TextField();

    @FXML
    private ListView<String> errors = new ListView<>();

    @FXML
    private Label if_id = new Label();
    @FXML
    private Label id_ex = new Label();
    @FXML
    private Label ex_mem = new Label();
    @FXML
    private Label mem_wb = new Label();
    @FXML
    private Label wb_fin = new Label();

    @FXML
    public void compile(Event e) {
        String[] codeLines = new String[code.getParagraphs().size()];
        for (int i = 0; i < code.getParagraphs().size(); i++) {
            codeLines[i] = code.getParagraphs().get(i).toString();
        }
        Parser parser = new Parser(codeLines);

        errors.getItems().clear();
        if(parser.errors.isEmpty()){
            errors.getItems().add("Compiled Successfully");
            instructions = parser.instructions;
            mipsController = new Controller(parser.instructions, cycleNumber);
        }
        else {
            errors.getItems().add("ERRORS:");
            parser.errors.forEach(error -> {
                errors.getItems().add(error);
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clockCycle.textProperty().addListener((obj, oldVal, newVal) -> {
            changeClockcycle(newVal, 0);
        });

        for (int i = 0; i < 32; i++) {
            registers.getItems().add(i);
        }

        registers.getColumns().forEach(
                column -> {
                    switch (column.textProperty().getValue()) {
                        case "Val":
                            TableColumn<Integer, Number> val = (TableColumn<Integer, Number>) column;
                            val.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(0));
                            break;
                        case "RegName":

                            TableColumn<Integer, String> regName = (TableColumn<Integer, String>) column;
                            regName.setCellValueFactory(cellData -> {
                                Integer rowIndex = cellData.getValue();
                                return new SimpleStringProperty(REG_NAMES[rowIndex]);
                            });
                            break;
                        case "RegNum":
                            TableColumn<Integer, Number> regNum = (TableColumn<Integer, Number>) column;
                            regNum.setCellValueFactory(cellData -> {
                                Integer rowIndex = cellData.getValue();
                                return new ReadOnlyIntegerWrapper(rowIndex);
                            });
                            break;
                    }
                }
        );

    }

    public void changeClockcycle(String val, int increase) {
        try {
            int clockCycleNumber = Integer.parseInt(val);
            clockCycle.textProperty().setValue((String.valueOf(Math.max(0, clockCycleNumber + increase))));
            cycleNumber = clockCycleNumber + increase;
            mipsController = new Controller(instructions, cycleNumber);
            mipsController.execute();

            registers.getColumns().forEach(
                    column -> {
                        switch (column.textProperty().getValue()) {
                            case "Val":
                                TableColumn<Integer, Number> newVal = (TableColumn<Integer, Number>) column;
                                newVal.setCellValueFactory(cellData -> {
                                    Integer rowIndex = cellData.getValue();
                                    return new ReadOnlyIntegerWrapper(mipsController.forwardingRegisters.read(rowIndex));
                                });
                                break;
                        }
                    }
            );
            registers.refresh();

            memory.getItems().clear();
            mipsController.memory.getMemory().forEach((k, v) -> {
                memory.getItems().add(k);
            });
            memory.getColumns().forEach(
                    column -> {
                        switch (column.textProperty().getValue()) {
                            case "Address":
                                TableColumn<Integer, Number> memAddress = (TableColumn<Integer, Number>) column;
                                memAddress.setCellValueFactory(cellData -> {
                                    Integer rowIndex = cellData.getValue();
                                    return new ReadOnlyIntegerWrapper(rowIndex);
                                });
                                break;
                            case "Value(Not displayed are zero)":
                                TableColumn<Integer, Number> memVal = (TableColumn<Integer, Number>) column;
                                memVal.setCellValueFactory(cellData -> {
                                    Integer rowIndex = cellData.getValue();
                                    return new ReadOnlyIntegerWrapper(mipsController.memory.read(rowIndex));
                                });
                                break;

                        }
                    }
            );
            memory.refresh();

            if_id.setText( mipsController.IF_IDtoString() );
            id_ex.setText( mipsController.ID_EXtoString() );
            ex_mem.setText( mipsController.EX_MEMtoString() );
            mem_wb.setText( mipsController.MEM_WBtoString() );
            wb_fin.setText( mipsController.WB_FINtoString() );

        } catch(Exception ignored){
        }
    }


    public void nextClockcycle(ActionEvent actionEvent) {
        changeClockcycle(clockCycle.getText(), 1);
    }

    public void prevClockcycle(ActionEvent actionEvent) {
        changeClockcycle(clockCycle.getText(), -1);
    }

}
