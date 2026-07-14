package com.buildcrm.mapper;

import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.dto.response.ProjectActivityResponse;
import com.buildcrm.dto.response.ProjectDocumentResponse;
import com.buildcrm.dto.response.ProjectExpenseResponse;
import com.buildcrm.dto.response.ProjectFileResponse;
import com.buildcrm.dto.response.ProjectInventoryResponse;
import com.buildcrm.dto.response.ProjectNoteResponse;
import com.buildcrm.dto.response.ProjectResponse;
import com.buildcrm.dto.response.ProjectTransactionResponse;
import com.buildcrm.entity.ActivityLogEntity;
import com.buildcrm.entity.DocumentEntity;
import com.buildcrm.entity.EmployeeEntity;
import com.buildcrm.entity.ExpenseEntity;
import com.buildcrm.entity.ProjectEntity;
import com.buildcrm.entity.ProjectInventoryEntity;
import com.buildcrm.entity.ProjectNoteEntity;
import com.buildcrm.entity.SaleTransactionEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-08T20:51:59+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ProjectResponse toResponse(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setTeamMembers( employeeEntitySetToEmployeeResponseSet( entity.getTeam() ) );
        projectResponse.setTotalContractValue( entity.getContractValue() );
        projectResponse.setTransactions( saleTransactionEntitySetToProjectTransactionResponseList( entity.getSalesTransactions() ) );
        projectResponse.setInventories( projectInventoryEntitySetToProjectInventoryResponseList( entity.getInventories() ) );
        projectResponse.setId( entity.getId() );
        projectResponse.setProjectCode( entity.getProjectCode() );
        projectResponse.setName( entity.getName() );
        projectResponse.setDescription( entity.getDescription() );
        projectResponse.setLocation( entity.getLocation() );
        projectResponse.setClientName( entity.getClientName() );
        projectResponse.setClientEmail( entity.getClientEmail() );
        projectResponse.setClientPhone( entity.getClientPhone() );
        projectResponse.setCategory( entity.getCategory() );
        projectResponse.setStatus( entity.getStatus() );
        projectResponse.setStartDate( entity.getStartDate() );
        projectResponse.setEndDate( entity.getEndDate() );
        projectResponse.setActualEndDate( entity.getActualEndDate() );
        projectResponse.setTotalBudget( entity.getTotalBudget() );
        projectResponse.setCompletionPercentage( entity.getCompletionPercentage() );
        projectResponse.setCoverImageUrl( entity.getCoverImageUrl() );
        projectResponse.setProjectManager( employeeMapper.toResponse( entity.getProjectManager() ) );
        projectResponse.setExpenses( expenseEntitySetToProjectExpenseResponseList( entity.getExpenses() ) );
        projectResponse.setDocuments( documentEntitySetToProjectDocumentResponseList( entity.getDocuments() ) );
        projectResponse.setActivities( activityLogEntitySetToProjectActivityResponseList( entity.getActivities() ) );
        projectResponse.setCreatedAt( entity.getCreatedAt() );
        projectResponse.setUpdatedAt( entity.getUpdatedAt() );

        calculateFinancialsAndSummaries( entity, projectResponse );

        return projectResponse;
    }

    @Override
    public ProjectNoteResponse toNoteResponse(ProjectNoteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectNoteResponse projectNoteResponse = new ProjectNoteResponse();

        projectNoteResponse.setId( entity.getId() );
        projectNoteResponse.setCreatedBy( entity.getCreatedBy() );
        projectNoteResponse.setCreatedAt( entity.getCreatedAt() );
        projectNoteResponse.setNote( entity.getContent() );

        return projectNoteResponse;
    }

    @Override
    public ProjectInventoryResponse toInventoryResponse(ProjectInventoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectInventoryResponse projectInventoryResponse = new ProjectInventoryResponse();

        projectInventoryResponse.setId( entity.getId() );
        projectInventoryResponse.setUnitNo( entity.getUnitNo() );
        projectInventoryResponse.setType( entity.getType() );
        projectInventoryResponse.setArea( entity.getArea() );
        projectInventoryResponse.setPrice( entity.getPrice() );
        projectInventoryResponse.setStatus( entity.getStatus() );
        projectInventoryResponse.setTotalCost( entity.getTotalCost() );

        return projectInventoryResponse;
    }

    @Override
    public ProjectTransactionResponse toTransactionResponse(SaleTransactionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectTransactionResponse projectTransactionResponse = new ProjectTransactionResponse();

        projectTransactionResponse.setInvoiceNo( entity.getInvoiceNumber() );
        projectTransactionResponse.setDate( entity.getInvoiceDate() );
        projectTransactionResponse.setAmount( entity.getAmount() );
        projectTransactionResponse.setCollectedAmount( entity.getCollectedAmount() );
        projectTransactionResponse.setDescription( entity.getDescription() );
        projectTransactionResponse.setId( entity.getId() );
        if ( entity.getStatus() != null ) {
            projectTransactionResponse.setStatus( entity.getStatus().name() );
        }

        return projectTransactionResponse;
    }

    @Override
    public ProjectExpenseResponse toExpenseResponse(ExpenseEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectExpenseResponse projectExpenseResponse = new ProjectExpenseResponse();

        projectExpenseResponse.setDate( entity.getExpenseDate() );
        projectExpenseResponse.setAddedBy( entity.getAddedBy() );
        projectExpenseResponse.setAmount( entity.getAmount() );
        if ( entity.getCategory() != null ) {
            projectExpenseResponse.setCategory( entity.getCategory().name() );
        }
        projectExpenseResponse.setDescription( entity.getDescription() );
        projectExpenseResponse.setId( entity.getId() );
        projectExpenseResponse.setReceiptContentType( entity.getReceiptContentType() );
        projectExpenseResponse.setReceiptFileName( entity.getReceiptFileName() );
        byte[] receiptImage = entity.getReceiptImage();
        if ( receiptImage != null ) {
            projectExpenseResponse.setReceiptImage( Arrays.copyOf( receiptImage, receiptImage.length ) );
        }
        projectExpenseResponse.setVendorName( entity.getVendorName() );

        return projectExpenseResponse;
    }

    @Override
    public ProjectActivityResponse toActivityResponse(ActivityLogEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectActivityResponse projectActivityResponse = new ProjectActivityResponse();

        projectActivityResponse.setActivityDate( entity.getCreatedAt() );
        projectActivityResponse.setMessage( entity.getDescription() );
        projectActivityResponse.setId( entity.getId() );
        projectActivityResponse.setPerformedBy( entity.getPerformedBy() );

        return projectActivityResponse;
    }

    @Override
    public ProjectDocumentResponse toDocumentResponse(DocumentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectDocumentResponse projectDocumentResponse = new ProjectDocumentResponse();

        projectDocumentResponse.setName( entity.getFileTitle() );
        projectDocumentResponse.setType( entity.getMimeType() );
        projectDocumentResponse.setSize( mapFileSize( entity.getFileSize() ) );
        projectDocumentResponse.setId( entity.getId() );
        projectDocumentResponse.setUploadedAt( entity.getUploadedAt() );
        projectDocumentResponse.setFilePresent( entity.getFileData() != null );

        return projectDocumentResponse;
    }

    @Override
    public ProjectFileResponse toFileResponse(DocumentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectFileResponse projectFileResponse = new ProjectFileResponse();

        byte[] fileData = entity.getFileData();
        if ( fileData != null ) {
            projectFileResponse.setFileData( Arrays.copyOf( fileData, fileData.length ) );
        }
        projectFileResponse.setFileName( entity.getFileName() );
        projectFileResponse.setId( entity.getId() );
        projectFileResponse.setMimeType( entity.getMimeType() );

        return projectFileResponse;
    }

    protected Set<EmployeeResponse> employeeEntitySetToEmployeeResponseSet(Set<EmployeeEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<EmployeeResponse> set1 = new LinkedHashSet<EmployeeResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EmployeeEntity employeeEntity : set ) {
            set1.add( employeeMapper.toResponse( employeeEntity ) );
        }

        return set1;
    }

    protected List<ProjectTransactionResponse> saleTransactionEntitySetToProjectTransactionResponseList(Set<SaleTransactionEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<ProjectTransactionResponse> list = new ArrayList<ProjectTransactionResponse>( set.size() );
        for ( SaleTransactionEntity saleTransactionEntity : set ) {
            list.add( toTransactionResponse( saleTransactionEntity ) );
        }

        return list;
    }

    protected List<ProjectInventoryResponse> projectInventoryEntitySetToProjectInventoryResponseList(Set<ProjectInventoryEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<ProjectInventoryResponse> list = new ArrayList<ProjectInventoryResponse>( set.size() );
        for ( ProjectInventoryEntity projectInventoryEntity : set ) {
            list.add( toInventoryResponse( projectInventoryEntity ) );
        }

        return list;
    }

    protected List<ProjectExpenseResponse> expenseEntitySetToProjectExpenseResponseList(Set<ExpenseEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<ProjectExpenseResponse> list = new ArrayList<ProjectExpenseResponse>( set.size() );
        for ( ExpenseEntity expenseEntity : set ) {
            list.add( toExpenseResponse( expenseEntity ) );
        }

        return list;
    }

    protected List<ProjectDocumentResponse> documentEntitySetToProjectDocumentResponseList(Set<DocumentEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<ProjectDocumentResponse> list = new ArrayList<ProjectDocumentResponse>( set.size() );
        for ( DocumentEntity documentEntity : set ) {
            list.add( toDocumentResponse( documentEntity ) );
        }

        return list;
    }

    protected List<ProjectActivityResponse> activityLogEntitySetToProjectActivityResponseList(Set<ActivityLogEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<ProjectActivityResponse> list = new ArrayList<ProjectActivityResponse>( set.size() );
        for ( ActivityLogEntity activityLogEntity : set ) {
            list.add( toActivityResponse( activityLogEntity ) );
        }

        return list;
    }
}
