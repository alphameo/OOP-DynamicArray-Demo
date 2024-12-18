package io.github.alphameo.darray.visualization;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import io.github.alphameo.darray.DynamicArray;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class DemoController {

    private final DynamicArray<String> curArray = new DynamicArray<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonApply;

    @FXML
    private ComboBox<String> comboBoxMethod;
    @FXML
    private Spinner<Integer> spinnerIndex;

    @FXML
    private TextField textFieldInputValue;
    @FXML
    private TextField textFieldOutputValue;

    private final TreeMap<String, Method> LABEL_METHOD = new TreeMap<>(Map.ofEntries(
            Map.entry("add", () -> demonstrateAdd()),
            Map.entry("addAll", () -> demonstrateAddAll()),
            Map.entry("addFirst", () -> demonstrateAddFirst()),
            Map.entry("addLast", () -> demonstrateAddLast()),
            Map.entry("clear", () -> demonstrateClear()),
            Map.entry("contains", () -> demonstrateContains()),
            Map.entry("containsAll", () -> demonstrateContainsAll()),
            Map.entry("equals", () -> demonstrateEquals()),
            Map.entry("get", () -> demonstrateGet()),
            Map.entry("getFirst", () -> demonstrateGetFirst()),
            Map.entry("getLast", () -> demonstrateGetLast()),
            Map.entry("indexOf", () -> demonstrateIndexOf()),
            Map.entry("isEmpty", () -> demonstrateIsEmpty()),
            Map.entry("lastIndexOf", () -> demonstrateLastIndexOf()),
            Map.entry("remove(index)", () -> demonstrateRemoveByIndex()),
            Map.entry("remove(value)", () -> demonstrateRemoveByValue()),
            Map.entry("removeFirst", () -> demonstrateRemoveFirst()),
            Map.entry("removeLast", () -> demonstrateRemoveLast()),
            Map.entry("reversed", () -> demonstrateReversed()),
            Map.entry("set", () -> demonstrateSet()),
            Map.entry("size", () -> demonstrateSize()),
            Map.entry("toArray", () -> demonstrateToArray())));

    @FXML
    void initialize() {
        spinnerIndex.getValueFactory();
        buttonApply.setOnAction(event -> {
            buttonApply.setText("2424");
        });
        comboBoxMethod.getItems().clear();
        comboBoxMethod.getItems().addAll(LABEL_METHOD.keySet());
    }

    private void demonstrateAdd() {
        curArray.add((int) spinnerIndex.getValue(), textFieldInputValue.getText());
    }

    private void demonstrateAddAll() {
        curArray.addAll((int) spinnerIndex.getValue(),
                ListParser.parse(textFieldInputValue.getText()));
    }

    private void demonstrateAddFirst() {
        curArray.addFirst(textFieldInputValue.getText());
    }

    private void demonstrateAddLast() {
        curArray.addLast(textFieldInputValue.getText());
    }

    private void demonstrateClear() {
        curArray.clear();
    }

    private void demonstrateContains() {
        curArray.contains(textFieldInputValue.getText());
    }

    private void demonstrateContainsAll() {
        curArray.containsAll(ListParser.parse(textFieldInputValue.getText()));
    }

    private void demonstrateEquals() {
        curArray.equals(ListParser.parse(textFieldInputValue.getText()));
    }

    private void demonstrateGet() {
        textFieldOutputValue.setText(curArray.get((int) spinnerIndex.getValue()));
    }

    private void demonstrateGetFirst() {
        textFieldOutputValue.setText(curArray.getFirst());
    }

    private void demonstrateGetLast() {
        textFieldOutputValue.setText(curArray.getLast());
    }

    private void demonstrateIndexOf() {
        textFieldOutputValue.setText(Integer.toString(curArray.indexOf(textFieldInputValue.getText())));
    }

    private void demonstrateIsEmpty() {
        textFieldOutputValue.setText(Integer.toString(curArray.indexOf(textFieldInputValue.getText())));
    }

    private void demonstrateLastIndexOf() {
        textFieldOutputValue.setText(Integer.toString(curArray.lastIndexOf(textFieldInputValue.getText())));
    }

    private void demonstrateRemoveByIndex() {
        curArray.remove((int) spinnerIndex.getValue());
    }

    private void demonstrateRemoveByValue() {
        curArray.remove(textFieldInputValue.getText());
    }

    private void demonstrateRemoveFirst() {
        curArray.removeFirst();
    }

    private void demonstrateRemoveLast() {
        curArray.removeLast();
    }

    private void demonstrateReversed() {
        textFieldOutputValue.setText(curArray.reversed().toString());
    }

    private void demonstrateSet() {
        curArray.set((int) spinnerIndex.getValue(), textFieldInputValue.getText());
    }

    private void demonstrateSize() {
        textFieldOutputValue.setText(Integer.toString(curArray.size()));
    }

    private void demonstrateToArray() {
        textFieldOutputValue.setText(Arrays.toString(curArray.toArray()));
    }
}
