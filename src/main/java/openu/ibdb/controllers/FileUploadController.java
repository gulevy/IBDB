package openu.ibdb.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import openu.ibdb.Application;
import openu.ibdb.models.ResultData;


/**
 * This class responsible for file upload web services actions
 * @author gulevy
 *
 */
@Controller
public class FileUploadController {

	
    /**
     * upload image file from client to server
     * @param name
     * @param file
     * @param redirectAttributes
     * @param folderName
     * @return
     */
	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("name") String name,
								   @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes,String folderName) {
		
		ResultData res ;
		String imgPath;
		if (folderName.equalsIgnoreCase("books")) {
			imgPath = Application.BOOK_IMG_PATH + "/" + name;
		} else {
			imgPath = Application.USER_IMG_PATH + "/" + name;
		}
		
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
			res = new ResultData(false, "Failed to upload image - Folder separators not allowed");
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
			
			res = new ResultData(false, "Failed to upload image - Relative pathnames not allowed");
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(imgPath)));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + name + "!");
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to upload " + name + " => " + e.getMessage());
				
				res = new ResultData(false, "Failed to upload image - You failed to upload " + name + " => " + e.getMessage());
		        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to upload " + name + " because the file was empty");
			
			res = new ResultData(false, "Failed to upload image - You failed to upload " + name + " because the file was empty");
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		
		res = new ResultData(true, "image was upload successfully");
        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}
}