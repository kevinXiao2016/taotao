package com.xiaoyue.controller;

import com.xiaoyue.service.PictureService;
import com.xiaoyue.utils.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult pictureUpload(MultipartFile uploadFile) {
        PictureResult pictureResult = pictureService.uploadPicture(uploadFile);
        return pictureResult;

    }

}
