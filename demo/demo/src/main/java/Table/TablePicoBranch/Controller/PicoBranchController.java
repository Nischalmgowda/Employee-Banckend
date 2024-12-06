package Table.TablePicoBranch.Controller;

import Table.TablePicoBranch.Response.PicoBranchResponse;
import Table.TablePicoBranch.Service.PicoBranchService;
import Table.TablePicoBranch.Model.PicoBranch;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:63342")
@RestController
@RequestMapping(value="/pico_branch")
public class PicoBranchController {
    private static final Logger logger= LoggerFactory.getLogger(PicoBranchController.class);


    @Autowired
    PicoBranchService picoBranchService;


    @RequestMapping(value="/save",method= RequestMethod.POST)
    public PicoBranchResponse save(@Valid @RequestBody PicoBranch picoBranch){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService
                    .savePicoBranch(picoBranch);
        }catch(Exception ex){
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/trash", method=RequestMethod.POST)
    public PicoBranchResponse moveToTrash(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.moveToTrash(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/get", method=RequestMethod.POST)
    public PicoBranchResponse get(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.get(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/get_deleted", method=RequestMethod.POST)
    @PostMapping("/get-deleted")
    public PicoBranchResponse getDeleted(){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.getDeleted();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/get_all", method=RequestMethod.POST)
    public PicoBranchResponse getAll(){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.getAll();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/get_pico_branch", method=RequestMethod.POST)
    public PicoBranchResponse getPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.getPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }

    @RequestMapping(value="/search_pico_branch", method=RequestMethod.POST)
    public PicoBranchResponse searchPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        PicoBranchResponse picoBranchResponse=new PicoBranchResponse();
        try{
            picoBranchResponse=picoBranchService.searchPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            picoBranchResponse.setSuccess(false);
            picoBranchResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return picoBranchResponse;
    }
}
