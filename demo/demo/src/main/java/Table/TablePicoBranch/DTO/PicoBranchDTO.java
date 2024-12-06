package Table.TablePicoBranch.DTO;

import Table.TablePicoBranch.Model.PicoBranch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PicoBranchDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String name;
    private String description;
    private String createdBy;
    private Date creationTime;
    private String modifiedBy;
    private Date modificationTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletionTime;

    public PicoBranchDTO(){

    }
    public PicoBranchDTO(PicoBranch picoBranch){
        this.id=picoBranch.getId();
        this.clientId= picoBranch.getClientId();
        this.orgId=picoBranch.getOrgId();
        this.name=picoBranch.getName();
        this.description=picoBranch.getDescription();
        this.createdBy=picoBranch.getCreatedBy();
        this.creationTime=picoBranch.getCreationTime();
        this.modifiedBy=picoBranch.getModifiedBy();
        this.modificationTime=picoBranch.getModificationTime();
        this.isDeleted=picoBranch.getIsDeleted();
        this.deletedBy=picoBranch.getDeletedBy();
        this.deletionTime=picoBranch.getDeletionTime();
    }

    public PicoBranchDTO(String id, String clientId, String orgId, String name, String description, String createdBy, Date creationTime, String modifiedBy, Date modificationTime, int isDeleted, String deletedBy, Date deletionTime) {
        this.id = id;
        this.clientId = clientId;
        this.orgId = orgId;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.creationTime = creationTime;
        this.modifiedBy = modifiedBy;
        this.modificationTime = modificationTime;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.deletionTime = deletionTime;
    }

}
