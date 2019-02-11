import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    private final String[] REG_NAMES = {"zero", "at", "v0", "v1",
            "a0", "a1", "a2", "a3", "t0", "t1", "t2",
            "t3", "t4", "t5", "t6", "t7", "s0", "s1",
            "s2", "s3", "s4", "s5", "s6", "s7", "t8",
            "t9", "k0", "k1", "gp", "sp", "fp", "ra"};

    @FXML
    private TableView<Integer> registers = new TableView<>();

    @FXML
    private TableColumn<Integer, Number> regname = new TableColumn<>();

    @FXML
    public void compile(Event e) {
        System.out.println("Yayyy");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}
