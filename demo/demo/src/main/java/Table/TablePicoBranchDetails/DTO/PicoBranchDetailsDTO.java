package Table.TablePicoBranchDetails.DTO;

import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import lombok.Data;

import java.util.Date;
@Data
public class PicoBranchDetailsDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String branchId;
    private String branchHeadId;
    private String branchAdminId;
    private String createdBy;
    private Date creationTime;
    private String modifiedBy;
    private Date modificationTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletionTime;
    private String name;
    public PicoBranchDetailsDTO(){

    }
    public PicoBranchDetailsDTO(PicoBranchDetails picoBranchDetails){
        this.id=picoBranchDetails.getId();
        this.clientId=picoBranchDetails.getClientId();
        this.orgId=picoBranchDetails.getOrgId();
        this.branchId=picoBranchDetails.getBranchId();
        this.branchHeadId=picoBranchDetails.getBranchHeadId();
        this.branchAdminId=picoBranchDetails.getBranchAdminId();
        this.createdBy=picoBranchDetails.getCreatedBy();
        this.creationTime=picoBranchDetails.getCreationTime();
        this.modifiedBy=picoBranchDetails.getModifiedBy();
        this.modificationTime=picoBranchDetails.getModificationTime();
        this.isDeleted=picoBranchDetails.getIsDeleted();
        this.deletedBy=picoBranchDetails.getDeletedBy();
        this.deletionTime=picoBranchDetails.getDeletionTime();

    }

    public PicoBranchDetailsDTO(String id, String clientId, String orgId, String branchId, String branchHeadId, String branchAdminId, String createdBy, Date creationTime, String modifiedBy, Date modificationTime, int isDeleted, String deletedBy, Date deletionTime, String name) {
        this.id = id;
        this.clientId = clientId;
        this.orgId = orgId;
        this.branchId = branchId;
        this.branchHeadId = branchHeadId;
        this.branchAdminId = branchAdminId;
        this.createdBy = createdBy;
        this.creationTime = creationTime;
        this.modifiedBy = modifiedBy;
        this.modificationTime = modificationTime;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.deletionTime = deletionTime;
        this.name = name;
    }
}
