package com.sheva.jcombobox;

import com.sheva.domain.TrainingTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComboFrame extends JFrame {

    public ComboFrame() throws HeadlessException {
        super("Training types");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font = new Font("Helvetica", Font.PLAIN, 18);

        Container content = getContentPane();
        setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Trainings");
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(font);

        content.add(label);

        ActionListener actionListener = e -> {
            JComboBox box = (JComboBox) e.getSource();
            String item = (String) box.getSelectedItem();
            label.setText(item);
        };

        JComboBox comboBox = new JComboBox(TrainingTypes.trainingNames);
        comboBox.setFont(font);
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        comboBox.addActionListener(actionListener);
        content.add(comboBox);

        setPreferredSize(new Dimension(300, 100));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

}
