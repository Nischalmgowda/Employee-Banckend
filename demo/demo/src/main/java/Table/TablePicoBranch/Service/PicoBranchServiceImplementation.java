package Table.TablePicoBranch.Service;

import Table.TablePicoBranch.DTO.PicoBranchDTO;
import Table.TablePicoBranch.Response.PicoBranchResponse;
import Table.TablePicoBranch.Model.PicoBranch;
import Table.TablePicoBranch.Repository.PicoBranchRepository;
import Table.TablePicoBranch.Specification.PicoBranchSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.String.valueOf;

@Service
public class PicoBranchServiceImplementation implements PicoBranchService{

    @Autowired
    PicoBranchRepository picoBranchRepository;

    private static final Logger logger=  LoggerFactory.getLogger(PicoBranchServiceImplementation.class);

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Optional<PicoBranch> findById(String id) throws Exception {
        return picoBranchRepository.findById(id);
    }

    @Override
    public PicoBranch save(PicoBranch picoBranch) throws Exception {

        return picoBranchRepository.save(picoBranch);
    }

    @Override
    public PicoBranchResponse savePicoBranch(PicoBranch picoBranch) throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            if(checkDuplicate(picoBranch)){
                picoBranchResponse.setPicoBranchDTO(new PicoBranchDTO(picoBranch));
                picoBranchResponse.setSuccess(false);
                picoBranchResponse.setError("Duplicate pico branch!");
            }
            else{
                if(picoBranch.getId()==null){
                    picoBranch.setCreatedBy("User");
                    picoBranch.setCreationTime(new Date());
                }else{
                    picoBranch.setModifiedBy("User");
                    picoBranch.setModificationTime(new Date());
                }
                picoBranchResponse.setPicoBranchDTO(
                        new PicoBranchDTO(
                                this.save(picoBranch)
                        ));
                picoBranchResponse.setSuccess(true);
                picoBranchResponse.setError("");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        return picoBranchResponse;


    }
    @Override
    public PicoBranchResponse moveToTrash(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<PicoBranch> optionalPicoBranch=this.findById(valueOf(formData.get("id")));
            if(optionalPicoBranch.isPresent()){
                PicoBranch picoBranch=optionalPicoBranch.get();
                picoBranch.setIsDeleted(1);
                picoBranch.setDeletedBy("User");
                picoBranch.setDeletionTime(new Date());
                picoBranchResponse.setSuccess(true);
                picoBranchResponse.setError("");
                picoBranchResponse.setPicoBranchDTO(new PicoBranchDTO(this.save(picoBranch)));
            }
            else{
                picoBranchResponse.setSuccess(false);
                picoBranchResponse.setError("Error occurred while moving branch details to trash!! Please Check it!");
            }

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;

    }

    @Override
    public PicoBranchResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<PicoBranch> optionalPicoBranch=this.findById(valueOf(formData.get("id")));
            if(optionalPicoBranch.isPresent()){
                PicoBranch picoBranch=optionalPicoBranch.get();
                picoBranchResponse.setSuccess(true);
                picoBranchResponse.setError("");
                picoBranchResponse.setPicoBranchDTO(new PicoBranchDTO(this.save(picoBranch)));
            }
            else {
                picoBranchResponse.setSuccess(false);
                picoBranchResponse.setError("Error occurred while fetching branch details!! Please try again!");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }



    private List<PicoBranchDTO> getPicoBranchDTOS(List<PicoBranch> picoBranches) {
        List<PicoBranchDTO> picoBranchDTOS=new ArrayList<>();
        for(PicoBranch picobranch:picoBranches){
            picoBranchDTOS.add(new PicoBranchDTO(picobranch));
        }
        return picoBranchDTOS;
    }

    private boolean checkDuplicate(PicoBranch picoBranch) {
        List<PicoBranch> picoBranches;
        if(picoBranch.getId()==null){
            picoBranches=picoBranchRepository.findAllByIsDeletedAndName(0,picoBranch.getName());
        }
        else{
            picoBranches=picoBranchRepository.findAllByIsDeletedAndNameAndIdIsNot(0,picoBranch.getName(),picoBranch.getId());
        }
        return !picoBranches.isEmpty();
    }

    @Override
    public PicoBranchResponse getDeleted() throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse.setData(
                    getPicoBranchDTOS(picoBranchRepository.findAllByIsDeleted(1)));
            picoBranchResponse.setSuccess(true);
            picoBranchResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }
    @Override
    public PicoBranchResponse getAll() throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            Sort sort=Sort.by(Sort.Direction.ASC,"name");
            picoBranchResponse.setData(getPicoBranchDTOS(picoBranchRepository.findAllByIsDeleted(0,sort)));
            picoBranchResponse.setSuccess(true);
            picoBranchResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }
    @Override
    public PicoBranchResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String sortFiled=formData.get("sort_field")==null? "name":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
            Page<PicoBranch> page=picoBranchRepository.findAllByIsDeleted(0,  pageable);
            picoBranchResponse.setRecordsTotal(page.getTotalElements());
            picoBranchResponse.setRecordsFiltered(page.getTotalElements());
            picoBranchResponse.setTotalPages(page.getTotalPages());
            picoBranchResponse.setData(getPicoBranchDTOS(page.getContent()));
            picoBranchResponse.setCurrentRecords(picoBranchResponse.getData().size());
            picoBranchResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        }

        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return picoBranchResponse;

    }


    @Override
    public PicoBranchResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null ? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null? 10:Integer.parseInt(formData.get("page_size"));
            String searchText=formData.get("search_text")==null? null: String.valueOf(formData.get("search_text"));
            String sortFiled=formData.get("sort_field")==null? "name":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null?"asc":formData.get("sort_order");
            Sort sort;
            if(sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC,sortFiled);
            }
            Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
            Page<PicoBranch> page;
            if(searchText==null){
                page=picoBranchRepository.findAllByIsDeleted(0,pageable);
            }
            else{
                page=picoBranchRepository.findAll(PicoBranchSpecification.textInAllColumns(searchText),pageable);
            }
            picoBranchResponse.setRecordsTotal(page.getTotalElements());
            picoBranchResponse.setRecordsFiltered(page.getTotalElements());
            picoBranchResponse.setTotalPages(page.getTotalPages());
            picoBranchResponse.setData(getPicoBranchDTOS(page.getContent()));
            picoBranchResponse.setCurrentRecords(picoBranchResponse.getData().size());
            picoBranchResponse.setSuccess(true);
            logger.trace("Completed successfully");

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }


}
