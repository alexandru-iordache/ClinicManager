/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.administrator_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.User;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainDialog;
import clinic_manager.user_interface.components.TitleLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;


/**
 *
 * @author Iordi
 */
public class TablePanel extends JPanel{
    private final User user;
    private final DatabaseAPI databaseAPI;
    private JTable usersTable;
    private DefaultTableModel model;

    public TablePanel(User user, DatabaseAPI databaseAPI) {
        this.user = user;
        this.databaseAPI = databaseAPI;
        
        init();
    }
    
    private void init(){
        setBounds(0, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fill", "[][]", "10[][][]"));
        
        var titleLabel = new TitleLabel("View Users");
        add(titleLabel, "center, span 3, wrap 20");
        
        String columnNames[] = {"ID", "Username", "Password", "Type"};
        String data[][] = fetchDataFromTable();
        model = new DefaultTableModel(data, columnNames);
        usersTable = new JTable(model);

        JScrollPane tablePane = new JScrollPane(usersTable);
        tablePane.setPreferredSize(new Dimension(600, 200));
        add(tablePane, "center, span 3, wrap 20");
        
        var deleteButton = new MainButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 50));
        deleteButton.addActionListener(e -> {
            int row = usersTable.getSelectedRow();
            String value = usersTable.getModel().getValueAt(row, 0).toString();
            String valueType = usersTable.getModel().getValueAt(row, 3).toString();
            if(valueType.equals(String.valueOf("Administrator") )){
                 var dialogBox = new MainDialog("Nu poti sterge un user de tip Administrator!");
            }
            else{
                deleteRow(value);
            }
        });
        add(deleteButton, "center, wrap 20, span 3 ");
    }
    
    private String[][] fetchDataFromTable(){
        String data[][] = databaseAPI.retrieveDataFromUsers();
        return data;
    }

    private void deleteRow(String value) {
        int id = Integer.parseInt(value);
        boolean response = databaseAPI.deleteRecordFromUsers(id);
        if(response){
            model.removeRow(usersTable.getSelectedRow());
            var dialogBox = new MainDialog("Stergerea a avut succes!");
        }
        else{
            var dialogBox = new MainDialog("Stergerea nu a avut succes!");
        }
    }
}
