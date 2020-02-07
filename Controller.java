package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private ListView<Worker> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Change listener to let us know something on the list/UI changed
        //Selection Model - helps you identify in the UI which item is selected on the list by highlighting it & keeps track of the selected item
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                        ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();
                firstNameTextField.setText(((Employee)selectedItem).firstName);
                lastNameTextField.setText(((Employee)selectedItem).lastName);
                isActiveCheckbox.setSelected(((Employee)selectedItem).isActive);
                }
                );

        //Populating the observable list (1 way of doing it)
        ObservableList<Worker> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        Employee employee2 = new Employee();
        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";

        items.add(employee1); //populates the listView
        items.add(employee2);

        for (int i = 0; i <10; i++){
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "Staff Person";
        staff1.lastName = "Good Worker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "Faculty Person";
        faculty1.lastName = "Terrible Worker";
        items.add(staff1);
        items.add(faculty1);
    }
}
