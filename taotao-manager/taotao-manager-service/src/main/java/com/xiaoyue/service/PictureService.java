package com.xiaoyue.service;

import com.xiaoyue.utils.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    PictureResult uploadPicture(MultipartFile uploadPicture);

}
