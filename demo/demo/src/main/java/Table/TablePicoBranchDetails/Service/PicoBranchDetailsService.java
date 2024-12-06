package Table.TablePicoBranchDetails.Service;

import Table.TablePicoBranch.Model.PicoBranch;
import Table.TablePicoBranch.Response.PicoBranchResponse;
import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import Table.TablePicoBranchDetails.Response.PicoBranchDetailsResponse;

import java.util.Map;
import java.util.Optional;

public interface PicoBranchDetailsService {
    Optional<PicoBranchDetails> findById(String id) throws Exception;

    PicoBranchDetails save(PicoBranchDetails picoBranchDetails) throws Exception;

    PicoBranchDetailsResponse savePicoBranchDetails(PicoBranchDetails picoBranchDetails) throws Exception;

    PicoBranchDetailsResponse moveToTrash(Map<String,String> formData) throws Exception;

    PicoBranchDetailsResponse get(Map<String,String> formData) throws Exception;

    PicoBranchDetailsResponse getAll() throws Exception;

    PicoBranchDetailsResponse getDeleted() throws Exception;

    PicoBranchDetailsResponse getPaginated(Map<String,String> formData) throws Exception;

    PicoBranchDetailsResponse searchPaginated(Map<String,String> formData) throws Exception;

    PicoBranchDetailsResponse searchPaginatedThroughRepository(Map<String,String> formData) throws Exception;



}
