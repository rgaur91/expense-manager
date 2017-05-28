package com.pr.expense.controller;

import com.pr.commons.csv.ParseResult;
import com.pr.commons.dto.Message;
import com.pr.expense.csv.ActivityCsv;
import com.pr.expense.service.CSVIOService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * Created by jhanay on 1/15/2017.
 */
@RestController
public class IOController {

    @Autowired
    private CSVIOService csvioService;

    @ApiOperation(value = "User CSV",
            notes = "This Request is used for uploading CSV file")
    @RequestMapping(value = "v1/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String upload(@RequestBody MultipartFile data){

        return csvioService.upload(getData(data));
    }


	@ApiOperation(value = "User CSV",
			notes = "This Request is used for Validate CSV file")
	@RequestMapping(value = "v1/validate", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, ParseResult<ActivityCsv>> validate(  @RequestBody MultipartFile data, HttpServletRequest requestHeader){
		return Collections.singletonMap("data",csvioService.validate(getData(data)));
	}

	@ApiOperation(value = "Category CSV",
			notes = "This Request is used for Upload categories via CSV file")
	@RequestMapping(value = "v1/category/upload", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Message uploadCategory(  @RequestBody MultipartFile data){
		return csvioService.uploadCategory(getData(data));
	}

    private String getData(MultipartFile data) {
        if (data == null) {
            return null;
        }
        try {
            InputStream inputStream = data.getInputStream();
            if (inputStream == null) {
                return null;
            }
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
			e.printStackTrace();
            return null;
        }
    }
}
