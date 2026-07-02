package com.buildcrm.service.impl;

import com.buildcrm.dto.request.CreateProjectRequest;
import com.buildcrm.dto.request.UpdateProjectRequest;
import com.buildcrm.dto.request.AddInventoryRequest;
import com.buildcrm.dto.request.AddNoteRequest;
import com.buildcrm.dto.request.AddDocumentRequest;
import com.buildcrm.dto.request.AddExpenseRequest;
import com.buildcrm.dto.request.AddTransactionRequest;
import com.buildcrm.domain.model.Employee;
import com.buildcrm.dto.request.AddActivityRequest;
import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.dto.response.ProjectInventoryResponse;
import com.buildcrm.dto.response.ProjectNoteResponse;
import com.buildcrm.dto.response.ProjectResponse;
import com.buildcrm.entity.ProjectEntity;
import com.buildcrm.entity.EmployeeEntity;
import com.buildcrm.entity.ProjectInventoryEntity;
import com.buildcrm.entity.ProjectNoteEntity;
import com.buildcrm.entity.DocumentEntity;
import com.buildcrm.entity.ExpenseEntity;
import com.buildcrm.entity.SaleTransactionEntity;
import com.buildcrm.entity.ActivityLogEntity;
import com.buildcrm.enums.ProjectCategory;
import com.buildcrm.enums.ProjectStatus;
import com.buildcrm.exception.BadRequestException;
import com.buildcrm.exception.ResourceNotFoundException;
import com.buildcrm.mapper.EmployeeMapper;
import com.buildcrm.mapper.ProjectMapper;
import com.buildcrm.repository.ProjectNoteRepository;
import com.buildcrm.repository.ProjectRepository;
import com.buildcrm.repository.EmployeeRepository;
import com.buildcrm.repository.ProjectInventoryRepository;
import com.buildcrm.repository.DocumentRepository;
import com.buildcrm.repository.ExpenseRepository;
import com.buildcrm.repository.SaleTransactionRepository;
import com.buildcrm.repository.ActivityLogRepository;
import com.buildcrm.service.interfaces.ProjectService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectNoteRepository projectNoteRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectInventoryRepository projectInventoryRepository;
    private final DocumentRepository documentRepository;
    private final ExpenseRepository expenseRepository;
    private final SaleTransactionRepository saleTransactionRepository;
    private final ActivityLogRepository activityLogRepository;
    private final ProjectMapper projectMapper;
    private final EmployeeMapper employeeMapper;


    @Override
    @Transactional
    @SuppressWarnings("null")
    public ProjectResponse createProject(CreateProjectRequest request) {
        EmployeeEntity projectManager = null;
        if (request.getProjectManagerId() != null) {
            projectManager = employeeRepository.findByIdAndDeletedFalse(request.getProjectManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getProjectManagerId()));
        }

        Set<EmployeeEntity> team = new HashSet<>();
        if (request.getTeamMemberIds() != null && !request.getTeamMemberIds().isEmpty()) {
            team.addAll(employeeRepository.findAllById(request.getTeamMemberIds()));
        }

        ProjectEntity project = ProjectEntity.builder()
            .id(UUID.randomUUID())
            .projectCode(generateProjectCode())
            .name(request.getName())
            .clientName(request.getClientName())
            .clientEmail(request.getClientEmail())
            .clientPhone(request.getClientPhone())
            .category(request.getCategory())
            .status(request.getStatus())
            .location(request.getLocation())
            .description(request.getDescription())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .totalBudget(request.getTotalBudget())
            .contractValue(request.getContractValue())
            .completionPercentage(request.getCompletionPercentage())
            .projectManager(projectManager)
            .team(team)
            .build();

        ProjectEntity savedProject = Objects.requireNonNull(projectRepository.save(project));
        return projectMapper.toResponse(savedProject);
    }

    private String generateProjectCode() {
        long count = projectRepository.count() + 1;
        return String.format("PRJ-%04d", count);
    }

    @Override
    @Transactional
    public ProjectResponse getProject(UUID id) {
        java.util.Optional<ProjectEntity> data = projectRepository.findByIdAndDeletedFalse(id);
System.out.println("ProjectEntity11111:" +data.stream().findFirst().orElse(null).getTeam().size());
         return data.map(projectMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));       
    }

      @Override
    @Transactional
    public ProjectResponse getProject(String id) {
        return projectRepository.findByProjectCodeAndDeletedFalse(id)
            .map(projectMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

      @Override
      @Transactional
    public List<ProjectNoteResponse> getProjectNotes(UUID id) {
     projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        List<ProjectNoteEntity> notesEntityList=  projectNoteRepository.findByProjectIdAndDeletedFalse(id);


        return notesEntityList.stream()
            .map(projectMapper::toNoteResponse)
            .collect(Collectors.toList());
    }

     @Override
    public ProjectNoteResponse addNote(UUID id, AddNoteRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ProjectNoteEntity note = ProjectNoteEntity.builder()
            .id(UUID.randomUUID())
            .project(project)
            .content(request.note())
            .createdBy(request.createdBy())
            .createdAt(OffsetDateTime.now())
            .build();

        ProjectNoteEntity savedNote = projectNoteRepository.save(note);

        return projectMapper.toNoteResponse(savedNote);
    }

    @Override
    @Transactional
    public PagedResponse<ProjectResponse> listProjects(int page, int size, String sort, String status, String category, String search) {
        Pageable pageable = PageRequest.of(page, size, parseSort(sort));
        var result = projectRepository.findAll(projectSpec(status, category, search), pageable);
        return new PagedResponse<>(
            result.getContent().stream().map(projectMapper::toResponse).collect(Collectors.toList()),
            result.getNumber(),
            result.getSize(),
            result.getTotalElements(),
            result.getTotalPages(),
            result.isFirst(),
            result.isLast()
        );
    }

    private Specification<ProjectEntity> projectSpec(String status, String category, String search) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            if (status != null && !status.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), parseEnum(ProjectStatus.class, status, "status")));
            }
            if (category != null && !category.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("category"), parseEnum(ProjectCategory.class, category, "category")));
            }
            if (search != null && !search.isBlank()) {
                String pattern = "%" + search.toLowerCase() + "%";
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("clientName")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("projectCode")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), pattern)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "createdAt");
        }
        String[] parts = sort.split(",", 2);
        String property = parts[0].isBlank() ? "createdAt" : parts[0].trim();
        Sort.Direction direction = parts.length > 1 && "asc".equalsIgnoreCase(parts[1].trim())
            ? Sort.Direction.ASC
            : Sort.Direction.DESC;
        return Sort.by(direction, property);
    }

    private <T extends Enum<T>> T parseEnum(Class<T> enumType, String value, String fieldName) {
        try {
            return Enum.valueOf(enumType, value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid " + fieldName + ": " + value);
        }
    }


    @Override
    @Transactional
    @SuppressWarnings("null")
    public ProjectResponse updateProject(UUID id, UpdateProjectRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        project.setName(request.getName());
        project.setClientName(request.getClientName());
        project.setClientEmail(request.getClientEmail());
        project.setClientPhone(request.getClientPhone());
        project.setCategory(request.getCategory());
        project.setStatus(request.getStatus());
        project.setLocation(request.getLocation());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setTotalBudget(request.getTotalBudget());
        project.setContractValue(request.getContractValue());
        project.setCompletionPercentage(request.getCompletionPercentage());

        if (request.getProjectManagerId() != null) {
            EmployeeEntity projectManager = employeeRepository.findByIdAndDeletedFalse(request.getProjectManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getProjectManagerId()));
            project.setProjectManager(projectManager);
        } else {
            project.setProjectManager(null);
        }

        if (request.getTeamMemberIds() != null) {
            Set<EmployeeEntity> team = new HashSet<>(employeeRepository.findAllById(request.getTeamMemberIds()));
            project.setTeam(team);
        } else {
            project.getTeam().clear();
        }

        ProjectEntity savedProject = Objects.requireNonNull(projectRepository.save(project));
        return projectMapper.toResponse(savedProject);
    }

    @Override
    @Transactional
    public void deleteProject(UUID id) {
        projectRepository.findByIdAndDeletedFalse(id).ifPresent(project -> {
            project.setDeleted(true);
            projectRepository.save(project);
        });
    }

    @Override
    @Transactional
    public ProjectInventoryResponse addInventory(UUID id, AddInventoryRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ProjectInventoryEntity inventory = ProjectInventoryEntity.builder()
            .id(UUID.randomUUID())
            .project(project)
            .unitNo(request.getUnitNo())
            .type(request.getType())
            .area(request.getArea())
            .price(request.getPrice())
            .status(request.getStatus())
            .totalCost( request.getArea().multiply(request.getPrice()))
            .build();

       ProjectInventoryEntity savedInventory = projectInventoryRepository.save(inventory);
            
        return projectMapper.toInventoryResponse(savedInventory);
    }

        @Override
    @Transactional
    public List<ProjectInventoryResponse> getInventories(UUID id) {
           List<ProjectInventoryEntity> inventories = projectInventoryRepository.findByProjectIdAndDeletedFalse(id);

        return inventories.stream()
            .map(projectMapper::toInventoryResponse)
            .collect(Collectors.toList());
            
    }

  @Override
@Transactional
public List<EmployeeResponse> getProjectTeam(UUID id) {

    projectRepository.findByIdAndDeletedFalse(id)
        .orElseThrow(() -> new EntityNotFoundException("Project not found"));

    return employeeMapper.toResponseList(
        projectRepository.findTeamByProjectId(id)
    );
}

    @Override
    @Transactional
    public ProjectResponse addDocument(UUID id, AddDocumentRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        DocumentEntity document = DocumentEntity.builder()
            .id(UUID.randomUUID())
            .project(project)
            .fileName(request.getName())
            .originalName(request.getName())
            .fileUrl(request.getFileUrl())
            .fileSize(request.getFileSize())
            .documentType(request.getDocumentType())
            .description(request.getDescription())
            .uploadedBy(request.getUploadedBy() != null ? request.getUploadedBy() : "System")
            .uploadedAt(OffsetDateTime.now())
            .build();

        documentRepository.save(document);

        ActivityLogEntity activity = ActivityLogEntity.builder()
            .id(UUID.randomUUID())
            .projectId(project.getId())
            .activityType(com.buildcrm.enums.ActivityType.DOCUMENT_UPLOADED)
            .title("Document Uploaded")
            .description("Document " + request.getName() + " uploaded to project")
            .performedBy(request.getUploadedBy() != null ? request.getUploadedBy() : "System")
            .entityType("DOCUMENT")
            .entityId(document.getId())
            .build();
        activityLogRepository.save(activity);

        project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.toResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse addExpense(UUID id, AddExpenseRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ExpenseEntity expense = ExpenseEntity.builder()
            .id(UUID.randomUUID())
            .project(project)
            .category(request.getCategory())
            .description(request.getDescription())
            .amount(request.getAmount())
            .expenseDate(request.getExpenseDate())
            .vendorName(request.getVendorName())
            .receiptUrl(request.getReceiptUrl())
            .addedBy(request.getAddedBy() != null ? request.getAddedBy() : "System")
            .approved(true)
            .build();

        expenseRepository.save(expense);

        ActivityLogEntity activity = ActivityLogEntity.builder()
            .id(UUID.randomUUID())
            .projectId(project.getId())
            .activityType(com.buildcrm.enums.ActivityType.EXPENSE_ADDED)
            .title("Expense Added")
            .description("Expense of " + request.getAmount() + " added to project: " + request.getDescription())
            .performedBy(request.getAddedBy() != null ? request.getAddedBy() : "System")
            .entityType("EXPENSE")
            .entityId(expense.getId())
            .build();
        activityLogRepository.save(activity);

        project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.toResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse addTransaction(UUID id, AddTransactionRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        SaleTransactionEntity transaction = SaleTransactionEntity.builder()
            .id(UUID.randomUUID())
            .project(project)
            .invoiceNumber(request.getInvoiceNumber())
            .description(request.getDescription())
            .amount(request.getAmount())
            .collectedAmount(request.getCollectedAmount())
            .status(request.getStatus())
            .invoiceDate(request.getInvoiceDate())
            .dueDate(request.getDueDate())
            .paymentMode(request.getPaymentMode())
            .transactionReference(request.getTransactionReference())
            .notes(request.getNotes())
            .build();

        saleTransactionRepository.save(transaction);

        ActivityLogEntity activity = ActivityLogEntity.builder()
            .id(UUID.randomUUID())
            .projectId(project.getId())
            .activityType(com.buildcrm.enums.ActivityType.PAYMENT_RECEIVED)
            .title("Payment Received")
            .description("Invoice " + request.getInvoiceNumber() + " with amount " + request.getAmount() + " recorded")
            .performedBy("System")
            .entityType("SALE_TRANSACTION")
            .entityId(transaction.getId())
            .build();
        activityLogRepository.save(activity);

        project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.toResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse addActivity(UUID id, AddActivityRequest request) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ActivityLogEntity activity = ActivityLogEntity.builder()
            .id(UUID.randomUUID())
            .projectId(project.getId())
            .activityType(request.getActivityType())
            .title(request.getTitle())
            .description(request.getDescription())
            .performedBy(request.getPerformedBy() != null ? request.getPerformedBy() : "System")
            .entityType("PROJECT")
            .entityId(project.getId())
            .build();

        activityLogRepository.save(activity);

        project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return projectMapper.toResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse assignTeam(UUID id, Set<UUID> employeeIds) {
        ProjectEntity project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        Set<EmployeeEntity> team = new HashSet<>(employeeRepository.findAllById(employeeIds));
    
        project.setTeam(team);
        projectRepository.save(project);

        // ActivityLogEntity activity = ActivityLogEntity.builder()
        //     .id(UUID.randomUUID())
        //     .projectId(project.getId())
        //     .activityType(com.buildcrm.enums.ActivityType.PROJECT_UPDATED)
        //     .title("Team Updated")
        //     .description("Project team members updated")
        //     .performedBy("System")
        //     .entityType("PROJECT")
        //     .entityId(project.getId())
        //     .build();
        // activityLogRepository.save(activity);

        System.out.println(project.getTeam().size() + " team members assigned to project with id: " + id);

        project = projectRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

                    System.out.println(project.getTeam().size() + " team members assigned to project with id: " + id);

        return projectMapper.toResponse(project);
    }

}
