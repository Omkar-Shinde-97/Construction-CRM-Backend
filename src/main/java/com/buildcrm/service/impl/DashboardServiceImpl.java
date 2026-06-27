package com.buildcrm.service.impl;

import com.buildcrm.service.interfaces.DashboardService;
import com.buildcrm.repository.EmployeeRepository;
import com.buildcrm.repository.ProjectRepository;
import com.buildcrm.repository.SaleTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final SaleTransactionRepository saleTransactionRepository;

    @Override
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        long totalProjects = projectRepository.count();
        long totalEmployees = employeeRepository.count();
        BigDecimal totalRevenue = saleTransactionRepository.findAll().stream()
            .map(tx -> tx.getCollectedAmount() != null ? tx.getCollectedAmount() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.put("totalProjects", totalProjects);
        stats.put("totalEmployees", totalEmployees);
        stats.put("totalRevenue", totalRevenue);
        stats.put("profitMargin", BigDecimal.ZERO);
        return stats;
    }
}
