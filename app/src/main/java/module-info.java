module io.github.alphameo.darr_visualization.visualization {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.alphameo.darr_visualization.visualization to javafx.fxml;

    exports io.github.alphameo.darr_visualization.visualization;
}
