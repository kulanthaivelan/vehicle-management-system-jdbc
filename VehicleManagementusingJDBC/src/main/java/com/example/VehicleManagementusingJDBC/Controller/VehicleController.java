package com.example.VehicleManagementusingJDBC.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.VehicleManagementusingJDBC.DAO.VehicleDAO;
import com.example.VehicleManagementusingJDBC.Entity.Vehicle;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class VehicleController {
    private VehicleDAO dao = new VehicleDAO();

    @GetMapping("/vehicles")
    public String listVehicles(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "id") String sortField,
        @RequestParam(defaultValue = "asc") String sortDir,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model) throws SQLException {
        List<Vehicle> vehicles = dao.getVehicles(keyword, sortField, sortDir, page, size);
        int total = dao.countVehicles(keyword);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int)Math.ceil((double)total/size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "vehicles";
    }

    @GetMapping("/vehicles/add")
    public String showAddForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle_form";
    }

    @PostMapping("/vehicles/save")
    public String saveVehicle(@ModelAttribute Vehicle vehicle, Model model) throws SQLException {
        // Input validation
        if (vehicle.getRegistrationNo() == null || vehicle.getRegistrationNo().isEmpty() ||
            vehicle.getMake() == null || vehicle.getMake().isEmpty() ||
            vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            model.addAttribute("error", "All fields are required");
            return "vehicle_form";
        }
        // Duplicate check
        if (vehicle.getId() == 0 && dao.existsByRegistrationNo(vehicle.getRegistrationNo())) {
            model.addAttribute("error", "Duplicate registration number");
            return "vehicle_form";
        }
        if (vehicle.getId() == 0) {
            dao.addVehicle(vehicle);
        } else {
            dao.updateVehicle(vehicle);
        }
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) throws SQLException {
        Vehicle vehicle = dao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle_form";
    }

    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable int id) throws SQLException {
        dao.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/export")
    public void exportCSV(HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=vehicles.csv");
        List<Vehicle> vehicles = dao.getVehicles("", "id", "asc", 1, Integer.MAX_VALUE);
        PrintWriter writer = response.getWriter();
        writer.println("ID,Registration No,Make,Model,Year");
        for (Vehicle v : vehicles) {
            writer.printf("%d,%s,%s,%s,%d\n", v.getId(), v.getRegistrationNo(), v.getMake(), v.getModel(), v.getYear());
        }
        writer.flush();
    }
}
