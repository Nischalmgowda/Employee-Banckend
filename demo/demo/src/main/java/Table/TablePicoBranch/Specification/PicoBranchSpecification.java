package Table.TablePicoBranch.Specification;

import Table.TablePicoBranch.Model.PicoBranch;
import org.springframework.data.jpa.domain.Specification;

public class PicoBranchSpecification {
    public static Specification<PicoBranch> textInAllColumns(String searchText){
        if(!searchText.contains("%")){
            searchText="%"+searchText+"%";

        }
        String finalText= searchText;
        return(root,query,builder) -> builder.and (builder.or(builder.like(root.get("name"), finalText)
                ),
                builder.equal(root.get("isDeleted"),0));
    }
}
