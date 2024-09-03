import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO {

    public void addDonor(String name, String bloodType, String contact, String address) throws SQLException {
        String sql = "INSERT INTO donors (name, blood_type, contact, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, bloodType);
            stmt.setString(3, contact);
            stmt.setString(4, address);
            stmt.executeUpdate();
        }
    }

    public void updateDonor(int id, String name, String bloodType, String contact, String address) throws SQLException {
        String sql = "UPDATE donors SET name = ?, blood_type = ?, contact = ?, address = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, bloodType);
            stmt.setString(3, contact);
            stmt.setString(4, address);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void deleteDonor(int id) throws SQLException {
        String sql = "DELETE FROM donors WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Donor getDonorById(int id) throws SQLException {
        String sql = "SELECT * FROM donors WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Donor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("blood_type"),
                            rs.getString("contact"),
                            rs.getString("address"));
                } else {
                    return null;
                }
            }
        }
    }

    public List<Donor> getAllDonors() throws SQLException {
        String sql = "SELECT * FROM donors";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            List<Donor> donors = new ArrayList<>();
            while (rs.next()) {
                donors.add(new Donor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("blood_type"),
                        rs.getString("contact"),
                        rs.getString("address")));
            }
            return donors;
        }
    }
}
