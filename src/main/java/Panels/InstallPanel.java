package Panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 01/08/2017.
 */
public class InstallPanel extends StepPanel
{
    public static String STEP = "INSTALL";
    public static String HELP = "Write the command you want to execute. If you want to execute more than one, click the button";
    private JLabel helpLabel;
    private JButton addButton;

    private JTextField commandField;
    private List<JTextField> fieldList;

    public InstallPanel()
    {
        super(STEP);

    }


    protected void initializeComponents()
    {
        helpLabel = new JLabel(HELP);
        commandField = new JTextField();
        fieldList = new ArrayList<JTextField>();
        fieldList.add(commandField);

        addButton = new JButton("Add new command");
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addTextField();
            }
        });

        centerPane.add(helpLabel,"spanx");
        //add(txt_file, "grow, spanx");
        centerPane.add(addButton,"wrap");
        centerPane.add(new JLabel("Write the command"), "spanx 3, wrap");
        centerPane.add(commandField,  "grow, spanx");
        //add(orButton, "wrap");

        //add(deleteAll);
        //add(activeAll);
        //add(deactiveAll, "spanx 3, wrap");
        //add(new JScrollPane(filtersTable), "spanx 3, grow, wrap");
        //add(new JScrollPane(descriptionArea),"spanx 3, grow, wrap");
        //add(filtersLabel, "spanx 3, wrap");
        //add(new JScrollPane(parametersTable),"spanx 3, grow, wrap");


    }

    private void addTextField()
    {
        centerPane.add(new JLabel("Write the command"), "spanx 3, wrap");
        JTextField newField = new JTextField();
        fieldList.add(newField);
        centerPane.add(newField,  "grow, spanx");
        this.updateUI();

    }

    public String getInfo()
    {
        String info = "install:\n";
        if(fieldList.size() > 0)
        {
            for(JTextField tf : fieldList)
            {
               info = info + "- "+tf.getText()+"\n";
            }
        }
        else
            info += "true\n";

        return info;
    }

}
