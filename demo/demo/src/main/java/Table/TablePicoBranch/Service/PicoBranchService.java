package Table.TablePicoBranch.Service;

import Table.TablePicoBranch.Response.PicoBranchResponse;
import Table.TablePicoBranch.Model.PicoBranch;

import java.util.Map;
import java.util.Optional;

public interface PicoBranchService {
    Optional<PicoBranch> findById(String id) throws Exception;

    PicoBranch save(PicoBranch picoBranch) throws Exception;

    PicoBranchResponse savePicoBranch(PicoBranch picoBranch) throws Exception;

    PicoBranchResponse moveToTrash(Map<String,String> formData) throws Exception;

    PicoBranchResponse get(Map<String,String> formData) throws Exception;

    PicoBranchResponse getDeleted() throws Exception;

    PicoBranchResponse getAll() throws Exception;

    PicoBranchResponse getPaginated(Map<String,String> formData) throws Exception;

    PicoBranchResponse searchPaginated(Map<String,String> formData) throws Exception;
}
