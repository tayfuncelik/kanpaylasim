package com.kan.config;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadingController {
    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @RequestMapping("/file")
    public String uploading(Model model) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());
        return "uploading";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile uploadingFiles) throws IOException {
//        for(MultipartFile uploadedFile : uploadingFiles) {
//            File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
//            uploadedFile.transferTo(file);
//        }

        return "redirect:/";
    }
    
    
    @RequestMapping(value = "/saveUserDataAndFile", method = RequestMethod.POST,consumes = "multipart/form-data")
	@ResponseBody
	public Object saveUserDataAndFile( @RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
		
		System.out.println("Inside File upload" );
		 
		return null;
		
	}
    
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file){

        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File("F:/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
      }
    
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/users/profile")
    public ResponseEntity<?> handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam(name="file", required=false) MultipartFile file) {

//        log.info(" name : {}", name);
//        if(file!=null)
//        {   
//            log.info("image : {}", file.getOriginalFilename());
//            log.info("image content type : {}", file.getContentType());
//        }
        return new ResponseEntity<String>("Uploaded",HttpStatus.OK);
    }
}
