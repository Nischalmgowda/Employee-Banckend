package Table.TablePicoBranch.Response;

import Table.TablePicoBranch.DTO.PicoBranchDTO;
import lombok.Data;

import java.util.List;
@Data
public class PicoBranchResponse {
    private PicoBranchDTO picoBranchDTO;

    private List<PicoBranchDTO> data;

    private long totalPages;

    private long recordsTotal;

    private long currentRecords;

    private long recordsFiltered;

    private boolean success;

    private String error;
}
