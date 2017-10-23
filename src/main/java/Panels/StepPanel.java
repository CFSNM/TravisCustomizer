package Panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cesar on 29/07/2017.
 */
public abstract class StepPanel extends JPanel
{

    private String info;
    private JButton saveButton;
    private JPanel buttonPanel, titlePanel;
    protected JPanel centerPane;

    public StepPanel(String step)
    {
        info = "default";
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel(step+" STEP");
        buttonPanel = new JPanel();
        titlePanel = new JPanel();
        centerPane = new JPanel();
        titlePanel.setBackground(Color.YELLOW);
        centerPane.setLayout(new MigLayout("", "[][grow][]", "[][][][][grow]"));
        initializeComponents();
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                info = getInfo();
                System.out.println(info);
            }
        });

        buttonPanel.add(saveButton);
        titlePanel.add(label);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JScrollPane(centerPane), BorderLayout.CENTER);
    }

    protected abstract void initializeComponents();
    public abstract String getInfo();

}
