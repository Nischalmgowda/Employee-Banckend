package Table.TablePicoBranch.Repository;

import Table.TablePicoBranch.Model.PicoBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PicoBranchRepository extends JpaRepository<PicoBranch,String> {
    List<PicoBranch> findAllByIsDeletedAndNameAndIdIsNot(int i, String name, String id) ;

    List<PicoBranch> findAllByIsDeletedAndName(int isDeleted, String name) ;

    List<PicoBranch> findAllByIsDeleted(int isDeleted);

    List<PicoBranch> findAllByIsDeleted(int isDeleted, Sort sort);

    Page<PicoBranch> findAllByIsDeleted(int isDeleted, Pageable page);

    Page<PicoBranch> findAll(Specification<PicoBranch> textInAllColumns, Pageable pageable);
}
