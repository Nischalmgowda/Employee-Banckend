package Table.TablePicoBranchDetails.Response;

import Table.TablePicoBranch.DTO.PicoBranchDTO;
import Table.TablePicoBranchDetails.DTO.PicoBranchDetailsDTO;
import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import lombok.Data;

import java.util.List;
@Data
public class PicoBranchDetailsResponse {
    private PicoBranchDetailsDTO picoBranchDetailsDTO;

    private List<PicoBranchDetailsDTO> data;

    private long totalPages;

    private long recordsTotal;

    private long currentRecords;

    private long recordsFiltered;

    private boolean success;

    private String error;
}
