package com.xiaoyue.service;

import com.xiaoyue.utils.FtpUtil;
import com.xiaoyue.utils.IDUtils;
import com.xiaoyue.utils.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Value("${PICTURE_UPLOAD_PATH}")
    private String PICTURE_UPLOAD_PATH;
    @Value("${FTP_SERVER_IP}")
    private String FTP_SERVER_IP;
    @Value("${FTP_SERVER_PORT}")
    private Integer FTP_SERVER_PORT;
    @Value("${FTP_SERVER_USERNAME}")
    private String FTP_SERVER_USERNAME;
    @Value("${FTP_SERVER_PASSWORD}")
    private String FTP_SERVER_PASSWORD;

    @Override
    public PictureResult uploadPicture(MultipartFile uploadPicture) {

        Integer error = 0;
        String url = "";
        String message = "";


        //上传文件名
        String originalFilename = uploadPicture.getOriginalFilename();
        //路径细分,以日期分开
        String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date())
                + "/" + new SimpleDateFormat("MM").format(new Date())
                + "/" + new SimpleDateFormat("dd").format(new Date());
        //文件存储名
        String newFilename = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            boolean result = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD, PICTURE_UPLOAD_PATH, filePath, newFilename, uploadPicture.getInputStream());
            if (!result) {
                error = 1;
            }
            url = IMAGE_BASE_URL + filePath + "/" + newFilename;
        } catch (IOException e) {
            error = 1;
            message = "上传失败";
            e.printStackTrace();
        }

        PictureResult pictureResult = new PictureResult(error, url, message);
        return pictureResult;
    }
}
