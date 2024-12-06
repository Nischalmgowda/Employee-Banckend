package Table.TablePicoBranchDetails.Specification;

import Table.TablePicoBranch.Model.PicoBranch;
import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import org.springframework.data.jpa.domain.Specification;

public class PicoBranchDetailsSpecification {
    public static Specification<PicoBranchDetails> textInAllColumns(String searchText){
        if(!searchText.contains("%")){
            searchText= "%"+searchText+"%";

        }
        String finalText= searchText;
        return(root,query,builder) -> builder.and (builder.or(builder.like(root.get("branchId"), finalText)
                ),
                builder.equal(root.get("isDeleted"),0));
    }
}