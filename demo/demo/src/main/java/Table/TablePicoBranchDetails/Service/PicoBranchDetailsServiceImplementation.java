package Table.TablePicoBranchDetails.Service;

import Table.TablePicoBranch.Service.PicoBranchServiceImplementation;
import Table.TablePicoBranchDetails.DTO.PicoBranchDetailsDTO;
import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import Table.TablePicoBranchDetails.Repository.PicoBranchDetailsRepository;
import Table.TablePicoBranchDetails.Response.PicoBranchDetailsResponse;
import Table.TablePicoBranchDetails.Specification.PicoBranchDetailsSpecification;
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
public class PicoBranchDetailsServiceImplementation implements PicoBranchDetailsService {
    @Autowired
    PicoBranchDetailsRepository picoBranchDetailsRepository;

    private static final Logger logger=  LoggerFactory.getLogger(PicoBranchServiceImplementation.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Optional<PicoBranchDetails> findById(String id) throws Exception {
        return picoBranchDetailsRepository.findById(id);
    }
    
    @Override
    public PicoBranchDetails save(PicoBranchDetails picoBranchDetails) throws Exception {
        return picoBranchDetailsRepository.save(picoBranchDetails);
    }

    @Override
    public PicoBranchDetailsResponse savePicoBranchDetails(PicoBranchDetails picoBranchDetails) throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            if(checkDuplicate(picoBranchDetails)){
                picoBranchDetailsResponse.setPicoBranchDetailsDTO(new PicoBranchDetailsDTO(picoBranchDetails));
                picoBranchDetailsResponse.setSuccess(false);
                picoBranchDetailsResponse.setError("Duplicate pico branch!");
            }
            else{
                if(picoBranchDetails.getId()==null){
                    picoBranchDetails.setCreatedBy("User");
                    picoBranchDetails.setCreationTime(new Date());
                }else{
                    picoBranchDetails.setModifiedBy("User");
                    picoBranchDetails.setModificationTime(new Date());
                }

                picoBranchDetailsResponse.setPicoBranchDetailsDTO(new PicoBranchDetailsDTO(this.save(picoBranchDetails)));
                picoBranchDetailsResponse.setSuccess(true);
                picoBranchDetailsResponse.setError("");

            }
            logger.trace("Completed Successfully");
        }
        catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        return picoBranchDetailsResponse;
    }

    @Override
    public PicoBranchDetailsResponse moveToTrash(Map<String, String> formData) throws Exception {
        logger.info("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<PicoBranchDetails> optionalPicoBranchDetails=this.findById(valueOf(formData.get("id")));
            if(optionalPicoBranchDetails.isPresent()){
                PicoBranchDetails picoBranchDetails=optionalPicoBranchDetails.get();
                picoBranchDetails.setIsDeleted(1);
                picoBranchDetails.setDeletedBy("User");
                picoBranchDetails.setDeletionTime(new Date());
                picoBranchDetailsResponse.setSuccess(true);
                picoBranchDetailsResponse.setError("");
                picoBranchDetailsResponse.setPicoBranchDetailsDTO(new PicoBranchDetailsDTO(this.save(picoBranchDetails)));
            }
            else{
                picoBranchDetailsResponse.setSuccess(false);
                picoBranchDetailsResponse.setError("Error occurred while moving branch details to trash!! Please Check it!");
            }

        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @Override
    public PicoBranchDetailsResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<PicoBranchDetailsDTO> optionalPicoBranchDetails=picoBranchDetailsRepository.findData(valueOf(formData.get("id")));

            if(optionalPicoBranchDetails.isPresent()){
                picoBranchDetailsResponse.setSuccess(true);
                picoBranchDetailsResponse.setError("");
                picoBranchDetailsResponse.setPicoBranchDetailsDTO(optionalPicoBranchDetails.get());
            }
            else {
                picoBranchDetailsResponse.setSuccess(false);
                picoBranchDetailsResponse.setError("Error occurred while fetching branch details!! Please try again!");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @Override
    public PicoBranchDetailsResponse getAll() throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();

        try{
            Sort sort=Sort.by(Sort.Direction.ASC,"branchId");
           //picoBranchDetailsResponse.setData(getPicoBranchDetailsDTOS(picoBranchDetailsRepository.findAllByIsDeleted(0,sort)));
           picoBranchDetailsResponse.setData(picoBranchDetailsRepository.findByIsDeleted(0,sort));
            picoBranchDetailsResponse.setSuccess(true);
            picoBranchDetailsResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }



    private List<PicoBranchDetailsDTO> getPicoBranchDetailsDTOS(List<PicoBranchDetails> picoBranchDetails1) {
        List<PicoBranchDetailsDTO> picoBranchDetailsDTOS=new ArrayList<>();
        for(PicoBranchDetails picobranchDetails:picoBranchDetails1){
            picoBranchDetailsDTOS.add(new PicoBranchDetailsDTO(picobranchDetails));
        }
        return picoBranchDetailsDTOS;
    }



    @Override
    public PicoBranchDetailsResponse getDeleted() throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
          //  picoBranchDetailsResponse.setData(getPicoBranchDetailsDTOS(picoBranchDetailsRepository.findAllByIsDeleted(1)));
            picoBranchDetailsResponse.setData(picoBranchDetailsRepository.findAllByIsDeleted(1));
            picoBranchDetailsResponse.setSuccess(true);
            picoBranchDetailsResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @Override
    public PicoBranchDetailsResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String sortFiled=formData.get("sort_field")==null? "orgId":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
            Page<PicoBranchDetails> page=picoBranchDetailsRepository.findAllByIsDeleted(0,  pageable);
            picoBranchDetailsResponse.setRecordsTotal(page.getTotalElements());
            picoBranchDetailsResponse.setRecordsFiltered(page.getTotalElements());
            picoBranchDetailsResponse.setTotalPages(page.getTotalPages());
            picoBranchDetailsResponse.setData(getPicoBranchDetailsDTOS(page.getContent()));
            picoBranchDetailsResponse.setCurrentRecords(picoBranchDetailsResponse.getData().size());
            picoBranchDetailsResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        }

        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;

    }

    @Override
    public PicoBranchDetailsResponse searchPaginated(Map<String, String> formData) throws Exception {
     logger.trace("Entering");
     PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
     try{
         logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
         int pageNumber=formData.get("current_page")==null?0:Integer.parseInt(formData.get("current_page"));
         int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
         String searchText=formData.get("search_text")==null?null: String.valueOf(formData.get("search_text"));
         String sortFiled=formData.get("sort_field")==null? "orgId":formData.get("sort_field");
         String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
         Sort sort;
         if (sortOrder.equals("asc")){
             sort=Sort.by(Sort.Direction.ASC, sortFiled);
         }
         else{
             sort=Sort.by(Sort.Direction.DESC, sortFiled);
         }
         Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
         Page<PicoBranchDetails> page;
         if(searchText==null){
             page=picoBranchDetailsRepository.findAllByIsDeleted(0,pageable);
         }
         else{
             page=picoBranchDetailsRepository.findAll(PicoBranchDetailsSpecification.textInAllColumns(searchText),pageable);
         }
         picoBranchDetailsResponse.setRecordsTotal(page.getTotalElements());
         picoBranchDetailsResponse.setRecordsFiltered(page.getTotalElements());
         picoBranchDetailsResponse.setTotalPages(page.getTotalPages());
         picoBranchDetailsResponse.setData(getPicoBranchDetailsDTOS(page.getContent()));
         picoBranchDetailsResponse.setCurrentRecords(picoBranchDetailsResponse.getData().size());
         picoBranchDetailsResponse.setSuccess(true);
         logger.trace("Completed successfully");
     }
     catch(Exception ex){
         logger.error(ex.getMessage(),ex);
         picoBranchDetailsResponse.setSuccess(false);
         picoBranchDetailsResponse.setError(ex.getMessage());

     }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @Override
    public PicoBranchDetailsResponse searchPaginatedThroughRepository(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null?0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String searchText=formData.get("search_text")==null?null: String.valueOf(formData.get("search_text"));
            String sortFiled=formData.get("sort_field")==null? "branchId":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
            if(!searchText.contains("%")){
                searchText= "%"+searchText+"%";

            }
            Page<PicoBranchDetailsDTO> page= picoBranchDetailsRepository.searchPaginated(searchText,pageable);
            picoBranchDetailsResponse.setRecordsTotal(page.getTotalElements());
            picoBranchDetailsResponse.setRecordsFiltered(page.getTotalElements());
            picoBranchDetailsResponse.setTotalPages(page.getTotalPages());
            picoBranchDetailsResponse.setData(page.getContent());
            picoBranchDetailsResponse.setCurrentRecords(picoBranchDetailsResponse.getData().size());
            picoBranchDetailsResponse.setSuccess(true);
            logger.trace("Completed successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }




    private boolean checkDuplicate(PicoBranchDetails picoBranchDetails) {
        List<PicoBranchDetails> picoBranchDetails1;
        if(picoBranchDetails.getId()==null){
            picoBranchDetails1=picoBranchDetailsRepository.findAllByIsDeletedAndBranchId(0,picoBranchDetails.getBranchId());
        }
        else{
            picoBranchDetails1=picoBranchDetailsRepository.findAllByIsDeletedAndBranchIdAndIdIsNot(0,picoBranchDetails.getBranchId(),picoBranchDetails.getId());
        }
        return !picoBranchDetails1.isEmpty();
    }

}





