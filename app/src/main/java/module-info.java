module io.github.alphameo.darray.visualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens io.github.alphameo.darray.visualization to javafx.fxml;

    exports io.github.alphameo.darray.visualization;
}
