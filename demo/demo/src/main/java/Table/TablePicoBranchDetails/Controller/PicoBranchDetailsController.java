package Table.TablePicoBranchDetails.Controller;

import Table.TablePicoBranch.Controller.PicoBranchController;
import Table.TablePicoBranchDetails.DTO.PicoBranchDetailsDTO;
import Table.TablePicoBranchDetails.Model.PicoBranchDetails;
import Table.TablePicoBranchDetails.Response.PicoBranchDetailsResponse;
import Table.TablePicoBranchDetails.Service.PicoBranchDetailsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/pico_branch_details")
public class PicoBranchDetailsController {
    private static final Logger logger= LoggerFactory.getLogger(PicoBranchController.class);
    @Autowired
    PicoBranchDetailsService picoBranchDetailsService;


    @RequestMapping(value="/save",method= RequestMethod.POST)
    public PicoBranchDetailsResponse save(@Valid @RequestBody PicoBranchDetails picoBranchDetails){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService
                    .savePicoBranchDetails(picoBranchDetails);
        }catch(Exception ex){
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @RequestMapping(value="/trash", method=RequestMethod.POST)
    public PicoBranchDetailsResponse moveToTrash(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.moveToTrash(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }
    @RequestMapping(value="/get", method=RequestMethod.POST)
    public PicoBranchDetailsResponse get(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.get(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }
    @RequestMapping(value="/get_all", method=RequestMethod.POST)
    public PicoBranchDetailsResponse getAll(){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.getAll();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @RequestMapping(value="/get_deleted", method=RequestMethod.POST)
    public PicoBranchDetailsResponse getDeleted(){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.getDeleted();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @RequestMapping(value="/get_pico_branch_details", method=RequestMethod.POST)
    public PicoBranchDetailsResponse getPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.getPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @RequestMapping(value="/search_pico_branch_details", method=RequestMethod.POST)
    public PicoBranchDetailsResponse searchPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.searchPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }

    @RequestMapping(value="/search_pico_branch_details_repository", method=RequestMethod.POST)
    public PicoBranchDetailsResponse searchPaginatedThroughRepository(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchDetailsResponse picoBranchDetailsResponse=new PicoBranchDetailsResponse();
        try{
            picoBranchDetailsResponse=picoBranchDetailsService.searchPaginatedThroughRepository(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchDetailsResponse.setSuccess(false);
            picoBranchDetailsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchDetailsResponse;
    }
}
