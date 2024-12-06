package Table.TablePicoBranch.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Data
public class PicoBranch {
    @Id
    @Length(max=40)
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Length(max=40)
    @Column(name="client_id")
    private String clientId;

    @Length(max=40)
    @Column(name="org_id")
    private String orgId;

    @Length(max=100)
    @Column(unique=true)
    private String name;

    @Length(max=1000)
    private String description;

    @Length(max=40)
    @Column(name="created_by")
    private String createdBy;

    @Column(name="creation_time")
    private Date creationTime;

    @Length(max=40)
    @Column(name="modified_by")
    private String modifiedBy;

    @Column(name="modification_time")
    private Date modificationTime;

    @Column(name="is_deleted")
    private int isDeleted;

    @Length(max=40)
    @Column(name="deleted_by")
    private String deletedBy;

    @Column(name="deletion_time")
    private Date deletionTime;


}



















