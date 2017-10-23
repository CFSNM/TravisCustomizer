package Panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cesar on 28/07/2017.
 */
public class BeforeInstallPanel extends StepPanel {


    public static String STEP = "BEFORE INSTALL";
    public static String HELP = "Write the path(s) of the file(s) you want to assign execution permission. If more than one, separate the paths with %";
    private JLabel helpLabel;

    private JTextField permissionField;

    public BeforeInstallPanel()
    {
        super(STEP);

    }


    protected void initializeComponents()
    {
        helpLabel = new JLabel(HELP);
        permissionField = new JTextField();

        centerPane.add(helpLabel,"spanx");
        //add(txt_file, "grow, spanx");
        centerPane.add(permissionField,  "grow, spanx");
        centerPane.add(new JLabel("Filtering Options"), "top, growx, spanx 2, wrap, wmin 100");
        //add(andButton,"wrap");
        //add(orButton, "wrap");
        centerPane.add(new JLabel("Filters"), "spanx 3, wrap");
        //add(deleteAll);
        //add(activeAll);
        //add(deactiveAll, "spanx 3, wrap");
        //add(new JScrollPane(filtersTable), "spanx 3, grow, wrap");
        //add(new JScrollPane(descriptionArea),"spanx 3, grow, wrap");
        //add(filtersLabel, "spanx 3, wrap");
        //add(new JScrollPane(parametersTable),"spanx 3, grow, wrap");

    }

    //Parsear la información del JTextField para escribir correctamente en travis
    public String getInfo()
    {
        String [] paths = permissionField.getText().split("%");
        String info = "before_install:\n";
        if(paths.length > 0)
        {
            for(int i = 0; i < paths.length; i++)
            {
                if (i == paths.length - 1)
                    info = info + "chmod +x "+paths[i]+"\n";
                else
                    info = info + "chmod +x "+paths[i]+" && ";
            }
        }

        return info;
    }

}
