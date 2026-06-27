package com.buildcrm.controller;

import com.buildcrm.dto.request.CreateProjectRequest;
import com.buildcrm.dto.request.UpdateProjectRequest;
import com.buildcrm.dto.request.AddInventoryRequest;
import com.buildcrm.dto.request.AddDocumentRequest;
import com.buildcrm.dto.request.AddExpenseRequest;
import com.buildcrm.dto.request.AddTransactionRequest;
import com.buildcrm.dto.request.AddActivityRequest;
import com.buildcrm.dto.response.ApiResponse;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.dto.response.ProjectResponse;
import com.buildcrm.service.interfaces.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(@Valid @RequestBody CreateProjectRequest request) {
        ProjectResponse response = projectService.createProject(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Project created", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProject(@PathVariable UUID id) {
        ProjectResponse response = projectService.getProject(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Project retrieved", response));
    }

     @GetMapping("/code/{projectCode}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProject(@PathVariable String projectCode) {
        ProjectResponse response = projectService.getProject(projectCode);
        return ResponseEntity.ok(new ApiResponse<>(true, "Project retrieved", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<ProjectResponse>>> listProjects(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt,desc") String sort,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String search
    ) {
        PagedResponse<ProjectResponse> response = projectService.listProjects(page, size, sort, status, category, search);
        return ResponseEntity.ok(new ApiResponse<>(true, "Projects listed", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProjectRequest request
    ) {
        ProjectResponse response = projectService.updateProject(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Project updated", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Project soft deleted", null));
    }

    @PostMapping("/{id}/inventories")
    public ResponseEntity<ApiResponse<ProjectResponse>> addInventory(
        @PathVariable UUID id,
        @Valid @RequestBody AddInventoryRequest request
    ) {
        ProjectResponse response = projectService.addInventory(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Inventory added to project", response));
    }

    @PostMapping("/{id}/documents")
    public ResponseEntity<ApiResponse<ProjectResponse>> addDocument(
        @PathVariable UUID id,
        @Valid @RequestBody AddDocumentRequest request
    ) {
        ProjectResponse response = projectService.addDocument(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Document added to project", response));
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<ApiResponse<ProjectResponse>> addExpense(
        @PathVariable UUID id,
        @Valid @RequestBody AddExpenseRequest request
    ) {
        ProjectResponse response = projectService.addExpense(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Expense added to project", response));
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity<ApiResponse<ProjectResponse>> addTransaction(
        @PathVariable UUID id,
        @Valid @RequestBody AddTransactionRequest request
    ) {
        ProjectResponse response = projectService.addTransaction(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Transaction recorded for project", response));
    }

    @PostMapping("/{id}/activities")
    public ResponseEntity<ApiResponse<ProjectResponse>> addActivity(
        @PathVariable UUID id,
        @Valid @RequestBody AddActivityRequest request
    ) {
        ProjectResponse response = projectService.addActivity(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Activity recorded for project", response));
    }

    @PostMapping("/{id}/team")
    public ResponseEntity<ApiResponse<ProjectResponse>> assignTeam(
        @PathVariable UUID id,
        @RequestBody Set<UUID> employeeIds
    ) {
        ProjectResponse response = projectService.assignTeam(id, employeeIds);
        return ResponseEntity.ok(new ApiResponse<>(true, "Team members assigned to project", response));
    }
}
