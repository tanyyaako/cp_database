module com.example.cp_database {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.cp_database to javafx.fxml;
    exports com.example.cp_database;
}