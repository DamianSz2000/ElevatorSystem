package com.damian.elevatorsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorGUI {
    private JFrame frame;
    private JTextArea resultArea;
    private JTextField pickupField;
    private JTextField targetField;
    private ControlSystem controlSystem;
    private int elevatorCount;

    public ElevatorGUI() {
        setup();
    }

    public void setup() {
        String elevatorCountStr = JOptionPane.showInputDialog("Enter number of elevators:");
        elevatorCount = Integer.parseInt(elevatorCountStr);
        controlSystem = new ControlSystem(elevatorCount);
        frame = new JFrame("Elevator Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        pickupField = new JTextField(10);
        targetField = new JTextField(10);
        JButton addButton = new JButton("Add Request");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pickupFloor = Integer.parseInt(pickupField.getText());
                int targetFloor = Integer.parseInt(targetField.getText());
                controlSystem.addRequest(pickupFloor, targetFloor);
                pickupField.setText("");
                targetField.setText("");
            }
        });

        inputPanel.add(new JLabel("Pickup Floor:"));
        inputPanel.add(pickupField);
        inputPanel.add(new JLabel("Target Floor:"));
        inputPanel.add(targetField);
        inputPanel.add(addButton);

        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int step = 1;
                while (!controlSystem.allRequestsProcessed()) {
                    controlSystem.startSimulation(1);
                    resultArea.append("Step " + step + "\n");
                    resultArea.append(controlSystem.getSystemStatus() + "\n");
                    step++;
                }
            }
        });

        JButton showRequestsButton = new JButton("Show Requests");
        showRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultArea.append("Requests: \n");
                resultArea.append(controlSystem.getRequests() + "\n");
            }
        });

        JButton resetButton = new JButton("Reset Simulation");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                setup();
                show();
            }
        });
        JButton randomRequestsButton = new JButton("Generate Random Requests");
        randomRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numRequestsStr = JOptionPane.showInputDialog("Enter number of random requests to generate:");
                String numFloorsStr = JOptionPane.showInputDialog("Enter number of floors in the building:");
                int numFloors = Integer.parseInt(numFloorsStr);
                int numRequests = Integer.parseInt(numRequestsStr);
                controlSystem.addRandomRequests(numRequests, numFloors);
                resultArea.append(numRequests + " random requests generated\n");
            }
        });
        controlPanel.add(randomRequestsButton);
        controlPanel.add(startButton);
        controlPanel.add(showRequestsButton);
        controlPanel.add(resetButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }


}



