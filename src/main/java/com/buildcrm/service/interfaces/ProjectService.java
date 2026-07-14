package com.buildcrm.service.interfaces;

import com.buildcrm.dto.request.CreateProjectRequest;
import com.buildcrm.dto.request.UpdateProjectRequest;
import com.buildcrm.dto.request.AddInventoryRequest;
import com.buildcrm.dto.request.AddNoteRequest;
import com.buildcrm.dto.request.AddDocumentRequest;
import com.buildcrm.dto.request.AddExpenseRequest;
import com.buildcrm.dto.request.AddTransactionRequest;
import com.buildcrm.dto.request.AddActivityRequest;
import com.buildcrm.dto.response.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProjectService {
    ProjectResponse createProject(CreateProjectRequest request);

    ProjectResponse getProject(UUID id);

    ProjectResponse getProject(String id);

    PagedResponse<ProjectResponse> listProjects(int page, int size, String sort, String status, String category, String search);

    ProjectResponse updateProject(UUID id, UpdateProjectRequest request);

    void deleteProject(UUID id);

    ProjectInventoryResponse addInventory(UUID id, AddInventoryRequest request);

    List<ProjectInventoryResponse> getInventories(UUID id);

    ProjectDocumentResponse addDocument(UUID id, AddDocumentRequest request, MultipartFile file);

    ProjectExpenseResponse addExpense(UUID id, AddExpenseRequest request, MultipartFile receipt);

    ProjectResponse addTransaction(UUID id, AddTransactionRequest request);

    ProjectResponse addActivity(UUID id, AddActivityRequest request);

    ProjectResponse assignTeam(UUID id, Set<UUID> employeeIds);

    List<EmployeeResponse> getProjectTeam(UUID id);

    List<ProjectNoteResponse> getProjectNotes(UUID id);

    ProjectNoteResponse addNote(UUID id, AddNoteRequest request);

    List<ProjectExpenseResponse> getExpenses(UUID id);

    ProjectFileResponse getDocumentFile(UUID projectId, UUID documentId);

    List<ProjectDocumentResponse> getDocuments(UUID id);
}
