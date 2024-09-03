import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BloodBankApp {
    private JFrame frame;
    private JTextField nameField, bloodTypeField, contactField, addressField, quantityField, donorIdField,
            inventoryIdField, searchDonorIdField, searchBloodTypeField;
    private JButton addDonorButton, updateDonorButton, deleteDonorButton, addInventoryButton, updateInventoryButton,
            deleteInventoryButton, viewDonorButton, viewInventoryButton;
    private JTextArea donorListArea, inventoryListArea, donorDetailArea, inventoryDetailArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BloodBankApp window = new BloodBankApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BloodBankApp() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Title
        frame.getContentPane().add(new JLabel("Donor Management"), gbc);

        // Donor fields
        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Donor ID:"), gbc);
        gbc.gridx = 1;
        donorIdField = new JTextField();
        frame.getContentPane().add(donorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField();
        frame.getContentPane().add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Blood Type:"), gbc);
        gbc.gridx = 1;
        bloodTypeField = new JTextField();
        frame.getContentPane().add(bloodTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Contact:"), gbc);
        gbc.gridx = 1;
        contactField = new JTextField();
        frame.getContentPane().add(contactField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField();
        frame.getContentPane().add(addressField, gbc);

        // Donor buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel donorButtonPanel = new JPanel();
        donorButtonPanel.setLayout(new FlowLayout());
        addDonorButton = new JButton("Add Donor");
        updateDonorButton = new JButton("Update Donor");
        deleteDonorButton = new JButton("Delete Donor");
        donorButtonPanel.add(addDonorButton);
        donorButtonPanel.add(updateDonorButton);
        donorButtonPanel.add(deleteDonorButton);
        frame.getContentPane().add(donorButtonPanel, gbc);

        // Donors List
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        donorListArea = new JTextArea(5, 20);
        // JScrollPane donorScrollPane = new JScrollPane(donorListArea);
        // donorScrollPane.setPreferredSize(new Dimension(500, 150));
        frame.getContentPane().add(new JScrollPane(donorListArea), gbc);

        // Title
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Inventory Management"), gbc);

        // Inventory fields
        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Inventory ID:"), gbc);
        gbc.gridx = 1;
        inventoryIdField = new JTextField();
        frame.getContentPane().add(inventoryIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Blood Type:"), gbc);
        gbc.gridx = 1;
        bloodTypeField = new JTextField();
        frame.getContentPane().add(bloodTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField();
        frame.getContentPane().add(quantityField, gbc);

        // Inventory buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setLayout(new FlowLayout());
        addInventoryButton = new JButton("Add Inventory");
        updateInventoryButton = new JButton("Update Inventory");
        deleteInventoryButton = new JButton("Delete Inventory");
        inventoryButtonPanel.add(addInventoryButton);
        inventoryButtonPanel.add(updateInventoryButton);
        inventoryButtonPanel.add(deleteInventoryButton);
        frame.getContentPane().add(inventoryButtonPanel, gbc);

        // Inventory List
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weighty = 0.5;
        inventoryListArea = new JTextArea(5, 20);
        inventoryListArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(inventoryListArea), gbc);

        // Search Donor
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Search Donor ID:"), gbc);
        gbc.gridx = 1;
        searchDonorIdField = new JTextField();
        frame.getContentPane().add(searchDonorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        viewDonorButton = new JButton("View Donor");
        frame.getContentPane().add(viewDonorButton, gbc);

        gbc.gridx = 1;
        donorDetailArea = new JTextArea(3, 20);
        donorDetailArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(donorDetailArea), gbc);

        // Search Inventory
        gbc.gridx = 0;
        gbc.gridy++;
        frame.getContentPane().add(new JLabel("Search Blood Type:"), gbc);
        gbc.gridx = 1;
        searchBloodTypeField = new JTextField();
        frame.getContentPane().add(searchBloodTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        viewInventoryButton = new JButton("View Inventory");
        frame.getContentPane().add(viewInventoryButton, gbc);

        gbc.gridx = 1;
        inventoryDetailArea = new JTextArea(3, 20);
        inventoryDetailArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(inventoryDetailArea), gbc);

        // Add action listeners
        addDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateDonorInputs()) {
                    try {
                        new DonorDAO().addDonor(
                                nameField.getText(),
                                bloodTypeField.getText(),
                                contactField.getText(),
                                addressField.getText());
                        JOptionPane.showMessageDialog(frame, "Donor added successfully!");
                        updateDonorList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error adding donor: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        updateDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateDonorInputs() && validateId(donorIdField.getText())) {
                    try {
                        int id = Integer.parseInt(donorIdField.getText());
                        new DonorDAO().updateDonor(
                                id,
                                nameField.getText(),
                                bloodTypeField.getText(),
                                contactField.getText(),
                                addressField.getText());
                        JOptionPane.showMessageDialog(frame, "Donor updated successfully!");
                        updateDonorList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error updating donor: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateId(donorIdField.getText())) {
                    try {
                        int id = Integer.parseInt(donorIdField.getText());
                        new DonorDAO().deleteDonor(id);
                        JOptionPane.showMessageDialog(frame, "Donor deleted successfully!");
                        updateDonorList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error deleting donor: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInventoryInputs()) {
                    try {
                        new InventoryDAO().addInventory(
                                bloodTypeField.getText(),
                                Integer.parseInt(quantityField.getText()));
                        JOptionPane.showMessageDialog(frame, "Inventory added successfully!");
                        updateInventoryList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error adding inventory: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        updateInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInventoryInputs() && validateId(inventoryIdField.getText())) {
                    try {
                        int id = Integer.parseInt(inventoryIdField.getText());
                        new InventoryDAO().updateInventory(
                                id,
                                bloodTypeField.getText(),
                                Integer.parseInt(quantityField.getText()));
                        JOptionPane.showMessageDialog(frame, "Inventory updated successfully!");
                        updateInventoryList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error updating inventory: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateId(inventoryIdField.getText())) {
                    try {
                        int id = Integer.parseInt(inventoryIdField.getText());
                        new InventoryDAO().deleteInventory(id);
                        JOptionPane.showMessageDialog(frame, "Inventory deleted successfully!");
                        updateInventoryList();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error deleting inventory: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        viewDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateId(searchDonorIdField.getText())) {
                    try {
                        int id = Integer.parseInt(searchDonorIdField.getText());
                        Donor donor = new DonorDAO().getDonorById(id);
                        if (donor != null) {
                            donorDetailArea.setText("ID: " + donor.getId() + "\n" +
                                    "Name: " + donor.getName() + "\n" +
                                    "Blood Type: " + donor.getBloodType() + "\n" +
                                    "Contact: " + donor.getContact() + "\n" +
                                    "Address: " + donor.getAddress());
                        } else {
                            donorDetailArea.setText("Donor not found.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error retrieving donor: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        viewInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!searchBloodTypeField.getText().trim().isEmpty()) {
                    try {
                        String bloodType = searchBloodTypeField.getText();
                        List<Inventory> items = new InventoryDAO().getInventoryByBloodType(bloodType);
                        StringBuilder sb = new StringBuilder();
                        for (Inventory item : items) {
                            sb.append("ID: ").append(item.getId())
                                    .append(" - Blood Type: ").append(item.getBloodType())
                                    .append(": ").append(item.getQuantity()).append(" units\n");
                        }
                        inventoryDetailArea.setText(sb.toString());
                        if (items.isEmpty()) {
                            inventoryDetailArea.setText("No inventory found for blood type: " + bloodType);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error retrieving inventory: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Blood type cannot be empty.", "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Initial data load
        updateDonorList();
        updateInventoryList();
    }

    private void updateDonorList() {
        try {
            List<Donor> donors = new DonorDAO().getAllDonors();
            StringBuilder sb = new StringBuilder();
            for (Donor donor : donors) {
                sb.append(donor.getId()).append(" - ").append(donor.getName())
                        .append(" (").append(donor.getBloodType()).append(")\n");
            }
            donorListArea.setText(sb.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error retrieving donor list: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInventoryList() {
        try {
            List<Inventory> items = new InventoryDAO().getAllInventory();
            StringBuilder sb = new StringBuilder();
            for (Inventory item : items) {
                sb.append(item.getId()).append(" - ").append(item.getBloodType())
                        .append(": ").append(item.getQuantity()).append(" units\n");
            }
            inventoryListArea.setText(sb.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error retrieving inventory list: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateDonorInputs() {
        if (nameField.getText().trim().isEmpty() ||
                bloodTypeField.getText().trim().isEmpty() ||
                contactField.getText().trim().isEmpty() ||
                addressField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateInventoryInputs() {
        if (bloodTypeField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Blood type and quantity are required.", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Quantity must be a number.", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateId(String idText) {
        if (idText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "ID is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "ID must be a number.", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
