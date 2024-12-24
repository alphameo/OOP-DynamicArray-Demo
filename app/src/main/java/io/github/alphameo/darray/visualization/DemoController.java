package io.github.alphameo.darray.visualization;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import io.github.alphameo.darray.DynamicArray;
import io.github.alphameo.darray.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DemoController {

    private DynamicArray<String> curArray = new DynamicArray<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonApply;

    @FXML
    private Button buttonAddItemMain;
    @FXML
    private Button buttonRemoveItemMain;

    @FXML
    private Button buttonAddItemAdditional;
    @FXML
    private Button buttonRemoveItemAdditional;

    @FXML
    private ComboBox<String> comboBoxMethod;
    @FXML
    private Spinner<Integer> spinnerIndex;

    @FXML
    private TextField textFieldInputValue;
    @FXML
    private TextField textFieldOutputValue;

    private GridPane tableMain;
    private GridPane tableAdditional;

    @FXML
    private ScrollPane paneMain;

    @FXML
    private ScrollPane paneAdditional;

    private final TreeMap<String, Method> LABEL_METHOD = new TreeMap<>(Map.ofEntries(
            Map.entry("add(index, val)", () -> demonstrateAdd()),
            Map.entry("addAll(arr)", () -> demonstrateAddAll()),
            Map.entry("addFirst(val)", () -> demonstrateAddFirst()),
            Map.entry("addLast(val)", () -> demonstrateAddLast()),
            Map.entry("clear()", () -> demonstrateClear()),
            Map.entry("contains(val)", () -> demonstrateContains()),
            Map.entry("containsAll(arr)", () -> demonstrateContainsAll()),
            Map.entry("equals(arr)", () -> demonstrateEquals()),
            Map.entry("get(index)", () -> demonstrateGet()),
            Map.entry("getFirst()", () -> demonstrateGetFirst()),
            Map.entry("getLast()", () -> demonstrateGetLast()),
            Map.entry("indexOf(val)", () -> demonstrateIndexOf()),
            Map.entry("isEmpty()", () -> demonstrateIsEmpty()),
            Map.entry("lastIndexOf(val)", () -> demonstrateLastIndexOf()),
            Map.entry("remove(index)", () -> demonstrateRemoveByIndex()),
            Map.entry("remove(val)", () -> demonstrateRemoveByValue()),
            Map.entry("removeFirst()", () -> demonstrateRemoveFirst()),
            Map.entry("removeLast()", () -> demonstrateRemoveLast()),
            Map.entry("reversed()", () -> demonstrateReversed()),
            Map.entry("set(index, val)", () -> demonstrateSet()),
            Map.entry("size()", () -> demonstrateSize()),
            Map.entry("toArray()", () -> demonstrateToArray())));

    @FXML
    void initialize() {
        buttonApply.setOnAction(event -> {
            LABEL_METHOD.get(comboBoxMethod.getValue()).apply();
        });

        comboBoxMethod.getItems().clear();
        comboBoxMethod.getItems().addAll(LABEL_METHOD.keySet());

        tableMain = new GridPane();
        tableAdditional = new GridPane();

        //resizeSpinner();

        paneMain.setContent(tableMain);
        paneAdditional.setContent(tableAdditional);

        buttonAddItemMain.setOnAction(event -> {
            addItem(tableMain, "");
            resizeSpinner();
        });
        buttonRemoveItemMain.setOnAction(event -> {
            deleteLastItem(tableMain);
            resizeSpinner();
        });

        buttonAddItemAdditional.setOnAction(event -> {
            addItem(tableAdditional, "");
        });
        buttonRemoveItemAdditional.setOnAction(event -> {
            deleteLastItem(tableAdditional);
        });
    }

    private TextField createValuableCell(String value) {
        TextField tf = new TextField();

        tf.setPrefHeight(25);
        tf.setPrefWidth(50);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(true);
        tf.setText(value);

        return tf;
    }

    private Label createIndexCell(int index) {
        Label l = new Label(Integer.toString(index));
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private void addCell(GridPane gridPane, Node node, int r, int c) {
        GridPane.setRowIndex(node, r);
        GridPane.setColumnIndex(node, c);
        gridPane.getChildren().add(node);
    }

    private void addItem(GridPane gridPane, String value) {
        int index = gridPane.getColumnCount();
        addCell(gridPane, createIndexCell(index), 0, index);
        addCell(gridPane, createValuableCell(value), 1, index);
    }

    private void deleteLastItem(GridPane gridPane) {
        int index = gridPane.getColumnCount() - 1;
        // gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) ==
        // index);
        gridPane.getChildren().remove(gridPane.getChildren().getLast());
        gridPane.getChildren().remove(gridPane.getChildren().getLast());
    }

    private TextField getItem(int index, GridPane gridPane) {
        return (TextField) gridPane.getChildren().get(index * 2 + 1);
    }

    private void writeList(GridPane gridPane, List<String> data) {
        gridPane.getChildren().clear();
        for (int i = 0; i < data.size(); i++) {
            addItem(gridPane, data.get((i)));
        }
    }

    private DynamicArray<String> readList(GridPane gridPane) {
        DynamicArray<String> list = new DynamicArray<>();
        for (int i = 0; i < gridPane.getColumnCount(); i++) {
            list.addLast(getItem(i, gridPane).getText());
        }

        return list;
    }

    private void resizeSpinner() {
        spinnerIndex
                .setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, tableMain.getColumnCount() - 1));
    }

    private void demonstrateAdd() {
        curArray = readList(tableMain);
        curArray.add((int) spinnerIndex.getValue(), textFieldInputValue.getText());
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateAddAll() {
        DynamicArray<String> arr = readList(tableAdditional);
        curArray.addAll((int) spinnerIndex.getValue(), arr);
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateAddFirst() {
        curArray = readList(tableMain);
        curArray.addFirst(textFieldInputValue.getText());
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateAddLast() {
        curArray = readList(tableMain);
        curArray.addLast(textFieldInputValue.getText());
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateClear() {
        curArray = readList(tableMain);
        curArray.clear();
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateContains() {
        curArray = readList(tableMain);
        boolean res = curArray.contains(textFieldInputValue.getText());
        textFieldOutputValue.setText(Boolean.toString(res));
    }

    private void demonstrateContainsAll() {
        curArray = readList(tableMain);
        DynamicArray<String> arr = readList(tableAdditional);
        boolean res = curArray.containsAll(arr);
        textFieldOutputValue.setText(Boolean.toString(res));
    }

    private void demonstrateEquals() {
        curArray = readList(tableMain);
        DynamicArray<String> arr = readList(tableAdditional);
        boolean res = curArray.equals(arr);
        textFieldOutputValue.setText(Boolean.toString(res));
    }

    private void demonstrateGet() {
        curArray = readList(tableMain);
        String res = curArray.get((int) spinnerIndex.getValue());
        textFieldOutputValue.setText(res);
    }

    private void demonstrateGetFirst() {
        curArray = readList(tableMain);
        String res = curArray.getFirst();
        textFieldOutputValue.setText(res);
    }

    private void demonstrateGetLast() {
        curArray = readList(tableMain);
        String res = curArray.getLast();
        textFieldOutputValue.setText(res);
    }

    private void demonstrateIndexOf() {
        curArray = readList(tableMain);
        int res = curArray.indexOf(textFieldInputValue.getText());
        textFieldOutputValue.setText(Integer.toString(res));
    }

    private void demonstrateIsEmpty() {
        curArray = readList(tableMain);
        boolean res = curArray.isEmpty();
        textFieldOutputValue.setText(Boolean.toString(res));
    }

    private void demonstrateLastIndexOf() {
        curArray = readList(tableMain);
        int res = curArray.lastIndexOf(textFieldInputValue.getText());
        textFieldOutputValue.setText(Integer.toString(res));
    }

    private void demonstrateRemoveByIndex() {
        curArray = readList(tableMain);
        String res = curArray.remove((int) spinnerIndex.getValue());
        textFieldOutputValue.setText(res);
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateRemoveByValue() {
        curArray = readList(tableMain);
        curArray.remove(textFieldInputValue.getText());
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateRemoveFirst() {
        curArray = readList(tableMain);
        curArray.removeFirst();
        writeList(tableMain, curArray);
    }

    private void demonstrateRemoveLast() {
        curArray = readList(tableMain);
        curArray.removeLast();
        writeList(tableMain, curArray);
        resizeSpinner();
    }

    private void demonstrateReversed() {
        curArray = readList(tableMain);
        var res = curArray.reversed();
        writeList(tableAdditional, res);
    }

    private void demonstrateSet() {
        curArray = readList(tableMain);
        curArray.set((int) spinnerIndex.getValue(), textFieldInputValue.getText());
        writeList(tableMain, curArray);
    }

    private void demonstrateSize() {
        curArray = readList(tableMain);
        int res = curArray.size();
        textFieldOutputValue.setText(Integer.toString(res));
    }

    private void demonstrateToArray() {
        curArray = readList(tableMain);
        var res = curArray.toArray();
        textFieldOutputValue.setText(Arrays.toString(res));
    }
}
