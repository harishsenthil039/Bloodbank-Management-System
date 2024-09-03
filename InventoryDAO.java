import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public void addInventory(String bloodType, int quantity) throws SQLException {
        String sql = "INSERT INTO inventory (blood_type, quantity) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bloodType);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();
        }
    }

    public void updateInventory(int id, String bloodType, int quantity) throws SQLException {
        String sql = "UPDATE inventory SET blood_type = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bloodType);
            stmt.setInt(2, quantity);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public void deleteInventory(int id) throws SQLException {
        String sql = "DELETE FROM inventory WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Inventory> getAllInventory() throws SQLException {
        String sql = "SELECT * FROM inventory";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            List<Inventory> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new Inventory(
                    rs.getInt("id"),
                    rs.getString("blood_type"),
                    rs.getInt("quantity")
                ));
            }
            return items;
        }
    }

    public List<Inventory> getInventoryByBloodType(String bloodType) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE blood_type = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bloodType);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Inventory> items = new ArrayList<>();
                while (rs.next()) {
                    items.add(new Inventory(
                        rs.getInt("id"),
                        rs.getString("blood_type"),
                        rs.getInt("quantity")
                    ));
                }
                return items;
            }
        }
    }
}
