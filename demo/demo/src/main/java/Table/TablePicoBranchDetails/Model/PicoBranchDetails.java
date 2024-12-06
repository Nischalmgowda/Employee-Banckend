package Table.TablePicoBranchDetails.Model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name="pico_branch_details")
@Data
public class PicoBranchDetails {
    @Id
    @Length(max=40)
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="client_id")
    @Length(max=40)
    private String clientId;

    @Length(max=40)
    @Column(name="org_id")
    private String orgId;

    @Length(max=40)
    @Column(name="branch_id")
    private String branchId;

    @Length(max=40)
    @Column(name="branch_head_id")
    private String branchHeadId;

    @Length(max=40)
    @Column(name="branch_admin_id")
    private String branchAdminId;

    @Length(max=40)
    @Column(name="created_by")
    private String createdBy;

    private Date creationTime;

    @Length(max=40)
    @Column(name="modified_by")
    private String modifiedBy;

    private Date modificationTime;

    private int isDeleted;

    @Length(max=40)
    @Column(name="deleted_by")
    private String deletedBy;

    private Date deletionTime;
}
