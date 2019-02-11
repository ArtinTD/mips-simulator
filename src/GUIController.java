import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    private final String[] REG_NAMES = {"zero", "at", "v0", "v1",
            "a0", "a1", "a2", "a3", "t0", "t1", "t2",
            "t3", "t4", "t5", "t6", "t7", "s0", "s1",
            "s2", "s3", "s4", "s5", "s6", "s7", "t8",
            "t9", "k0", "k1", "gp", "sp", "fp", "ra"};

    private Controller mipsController;
    @FXML
    private TableView<Integer> registers = new TableView<>();

    @FXML
    private TextArea code = new TextArea();

    @FXML
    private TextField clockCycle = new TextField();

    @FXML
    public void compile(Event e) {
        System.out.println(code.getParagraphs());
        // TODO: 2/11/19  initial mipsController
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
        // TODO: 2/11/19 execute till given
        try {
            int clockCycleNumber = Integer.parseInt(val);
            clockCycle.textProperty().setValue((String.valueOf(Math.max(0, clockCycleNumber + increase))));
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
