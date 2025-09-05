module com.eduardogisoldi.indexadordetextobst {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.eduardogisoldi.indexadordetextobst to javafx.fxml;
    exports com.eduardogisoldi.indexadordetextobst;
}