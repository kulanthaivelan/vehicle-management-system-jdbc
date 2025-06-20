package com.example.VehicleManagementusingJDBC.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.VehicleManagementusingJDBC.Config.JDBCUtil;
import com.example.VehicleManagementusingJDBC.Entity.Vehicle;

public class VehicleDAO {
    // CREATE
    public void addVehicle(Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicles (registration_no, make, model, year) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getRegistrationNo());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setInt(4, v.getYear());
            ps.executeUpdate();
        }
    }

    // READ with Filtering, Pagination, Sorting
    public List<Vehicle> getVehicles(String filter, String sortField, String sortDir, int page, int size) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE make LIKE ? OR model LIKE ? OR registration_no LIKE ? " +
                     "ORDER BY " + sortField + " " + sortDir + " LIMIT ? OFFSET ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + filter + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setInt(4, size);
            ps.setInt(5, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            List<Vehicle> list = new ArrayList<>();
            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setRegistrationNo(rs.getString("registration_no"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getInt("year"));
                list.add(v);
            }
            return list;
        }
    }

    // COUNT for pagination
    public int countVehicles(String filter) throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehicles WHERE make LIKE ? OR model LIKE ? OR registration_no LIKE ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + filter + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    // DUPLICATE CHECK
    public boolean existsByRegistrationNo(String regNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehicles WHERE registration_no = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, regNo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    // UPDATE
    public void updateVehicle(Vehicle v) throws SQLException {
        String sql = "UPDATE vehicles SET registration_no = ?, make = ?, model = ?, year = ? WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getRegistrationNo());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setInt(4, v.getYear());
            ps.setInt(5, v.getId());
            ps.executeUpdate();
        }
    }

    public void deleteVehicle(int id) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // FIND BY ID
    public Vehicle getVehicleById(int id) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setRegistrationNo(rs.getString("registration_no"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getInt("year"));
                return v;
            }
            return null;
        }
    }
}
