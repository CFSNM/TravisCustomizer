package Panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cesar on 01/08/2017.
 */
public class PreStepsPanel extends StepPanel
{
    public static String STEP = "PRE STEPS";
    public static String [] LANGUAGE_OPTIONS = new String[]{"Java","Ruby"};
    public static String [] TABLE_HEADER = new String[]{"Variable","Value"};
    private JComboBox languageBox;
    private JPanel languageOptionsPanel, newVarPane;
    private JCheckBox sudoBox;
    private String selectedLanguage;
    private JTable envTable;
    private JScrollPane scroll;
    private DefaultTableModel envTableModel;
    private JButton addVarButton, removeVarButton, removeAllVarButton;
    private JTextField varNameField, varValueField, jdkVersionField;

    private Map<String, String> var2valueMap;

    public PreStepsPanel()
    {
        super(STEP);
    }

    protected void initializeComponents()
    {
        selectedLanguage = LANGUAGE_OPTIONS[0];
        varNameField = new JTextField(20);
        varValueField = new JTextField(20);
        jdkVersionField = new JTextField(20);
        var2valueMap = new HashMap<String, String>();
        languageBox = new JComboBox();
        languageOptionsPanel = new JPanel();
        languageOptionsPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][grow]"));
        newVarPane = new JPanel();
        sudoBox = new JCheckBox();
        for(String option : LANGUAGE_OPTIONS)
            languageBox.addItem(option);

        languageOptionsPanel.add(new JLabel("Choose your JDK version (default 1.8)"),"spanx");
        languageOptionsPanel.add(jdkVersionField,"spanx 3, wrap");

        newVarPane.add(new JLabel("Name"));
        newVarPane.add(varNameField);
        newVarPane.add(Box.createHorizontalStrut(30));
        newVarPane.add(new JLabel("Value"));
        newVarPane.add(varValueField);

        addVarButton = new JButton("Add variable");
        addVarButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int result = JOptionPane.showConfirmDialog(null, newVarPane, "Enter new variable name and value", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result != JOptionPane.OK_OPTION) return;

                var2valueMap.put(varNameField.getText(), varValueField.getText());
                updateEnvironmentVariablesTable();

                varNameField.setText("");
                varValueField.setText("");

            }
        });
        removeVarButton = new JButton("Remove selected variable");
        removeVarButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                int selectedRow = envTable.getSelectedRow();
                String selectedVar = (String)envTable.getModel().getValueAt(selectedRow,0);
                var2valueMap.remove(selectedVar);
                updateEnvironmentVariablesTable();
            }
        });
        removeAllVarButton = new JButton("Remove all variables");
        removeAllVarButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                var2valueMap.clear();
                updateEnvironmentVariablesTable();
            }
        });

        envTableModel = new DefaultTableModel(TABLE_HEADER,0);
        envTable = new JTable(envTableModel);
        scroll = new JScrollPane(envTable);

        envTableModel.addTableModelListener(new TableModelListener()
        {
            public void tableChanged(TableModelEvent e)
            {
                var2valueMap.clear();
                String var, value;
                for(int j = 0; j < envTable.getModel().getRowCount(); j++)
                {
                    var = (String)envTableModel.getValueAt(j,0);
                    value = (String)envTableModel.getValueAt(j,1);
                    var2valueMap.put(var,value);
                }
            }
        });

        centerPane.add(new JLabel("Choose your project's language"),"spanx");
        //add(txt_file, "grow, spanx");
        centerPane.add(languageBox,"wrap");
        centerPane.add(new JScrollPane(languageOptionsPanel),"spanx 3, growx, wrap");
        // centerPane.add(commandField,  "grow, spanx");

        centerPane.add(new JLabel("Check if you need root permission"));
        centerPane.add(sudoBox, "wrap");
        //add(deleteAll);
        //add(activeAll);
        //add(deactiveAll, "spanx 3, wrap");
        //add(new JScrollPane(filtersTable), "spanx 3, grow, wrap");
        //add(new JScrollPane(descriptionArea),"spanx 3, grow, wrap");
        //add(filtersLabel, "spanx 3, wrap");
        centerPane.add(new JLabel("Environment variables"),"spanx");
        centerPane.add(addVarButton);
        centerPane.add(removeVarButton);
        centerPane.add(removeAllVarButton, "spanx 3, wrap");
        centerPane.add(scroll, "spanx 3, grow, wrap");

        languageBox.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {

                languageOptionsPanel.removeAll();
                selectedLanguage = (String)e.getItem();
                if(selectedLanguage.equals("Java"))
                {
                   languageOptionsPanel.add(new JLabel("Choose your JDK version (default 1.8)"),"spanx");
                   languageOptionsPanel.add(jdkVersionField,"spanx 3, wrap");
                }

                else if(selectedLanguage.equals("Ruby"))
                {
                    languageOptionsPanel.add(new JLabel("RUBYYYY"));
                }

                else
                    languageOptionsPanel.add(new JLabel("UNKNOWN LANGUAGE"));

                languageOptionsPanel.updateUI();
            }
        });


    }

    private void updateEnvironmentVariablesTable()
    {
        int numberOfVariables = var2valueMap.size();
        Object[][] newData = new Object[numberOfVariables][2];
        int counter = 0;
        for(Map.Entry<String, String> entry : var2valueMap.entrySet())
        {
            newData[counter][0] = entry.getKey();
            newData[counter][1] = entry.getValue();
            counter++;
        }

        ((DefaultTableModel)envTable.getModel()).setDataVector(newData, TABLE_HEADER);
    }

    public String getInfo()
    {
        String info = "";
        info += "language: "+selectedLanguage.toLowerCase()+"\n";

        if(selectedLanguage.equals("Java"))
        {
            if(jdkVersionField.getText().equals(""))
                info += "jdk: 1.8\n";
            else
                info += "jdk: "+jdkVersionField.getText()+"\n";
        }

        else if(selectedLanguage.equals("Ruby"))
        {

        }

        else
        {

        }


        if(sudoBox.isSelected())
            info += "sudo: required\n";

        else
            info += "sudo: false";

        info += "env:\n";

        for(int i = 0; i < envTable.getRowCount(); i++)
            info += "- "+envTable.getModel().getValueAt(i,0)+"=:"+envTable.getModel().getValueAt(i,1)+"\n";

        return info;
    }
}
