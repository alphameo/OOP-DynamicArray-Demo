module io.github.alphameo.darray.visualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens io.github.alphameo.darray.visualization to javafx.fxml;

    exports io.github.alphameo.darray.visualization;
}
