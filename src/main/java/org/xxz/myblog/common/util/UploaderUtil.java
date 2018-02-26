package org.xxz.myblog.common.util;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 上传工具类
 * @author xxzkid
 *
 */
public final class UploaderUtil {

    private static final Logger log = LoggerFactory.getLogger(UploaderUtil.class);

    /**
     * springmvc 文件上传
     * @param request
     * @param basePath 基础路径
     * @param formFileName 表单中file的name值
     * @param dir 上传目标文件夹 以/开头
     * @param allowedPattern 允许的格式
     * @param fileSize 允许上传的大小 单位KB
     * @return
     */
    public static UploadResult uploader(HttpServletRequest request, String basePath, String formFileName, String dir, List<String> allowedPattern, long fileSize) {
        String successCode = UploadResult.CODE_0;
        String successInfo = UploadResult.MSG_0;

        UploadResult result = new UploadResult();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        File fileDir = new File(basePath + dir);
        if (!fileDir.exists() && !fileDir.mkdirs()) {
            log.error("upload mkdirs failed.");
            throw new RuntimeException("upload mkdirs failed.");
        }

        /* 文件路径集合 */
        List<String> list = new ArrayList<>();

        /* 页面控件的文件流 */
        List<MultipartFile> multipartFileList = multipartRequest.getFiles(formFileName);
        if (multipartFileList.isEmpty()) {
            result.setCode(successCode);
            // 添加一个空路径
            list.add(UploadResult.EMPTY_URL);
            result.setData(list);
            result.setMsg(successInfo);
            return result;
        }

        if (allowedPattern == null || allowedPattern.isEmpty()) {
            allowedPattern = new ArrayList<>();
            allowedPattern.add(".gif");
            allowedPattern.add(".jpg");
            allowedPattern.add(".jpeg");
            allowedPattern.add(".png");
        }

        if (fileSize <= 0) {
            fileSize = 1L * 1024 * 1024;
        } else {
            fileSize *= 1024;
        }

        try {
            for (MultipartFile multipartFile : multipartFileList) {
                String originalFilename = multipartFile.getOriginalFilename();
                if ("".equals(originalFilename)) {
                    result.setCode(successCode);
                    // 添加一个空路径
                    list.add(UploadResult.EMPTY_URL);
                    result.setData(list);
                    result.setMsg(successInfo);
                    return result;
                }
                /* 获取文件的后缀 */
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                if (!allowedPattern.contains(suffix.toLowerCase())) {
                    result.setCode(UploadResult.CODE_1);
                    result.setData(null);
                    result.setMsg(UploadResult.MSG_1);
                    return result;
                }
                if (multipartFile.getSize() > fileSize) {
                    result.setCode(UploadResult.CODE_1);
                    result.setData(null);
                    result.setMsg(String.format(UploadResult.MSG_2, fileSize));
                    return result;
                }
                /* 使用时间戳生成文件名称 */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String nowTime = sdf.format(new Date());
                String filename = nowTime + suffix;// 构建文件名称

                /** 拼成完整的文件保存路径加文件 **/
                String fullFilename = fileDir + "/" + filename;
                File file = new File(fullFilename);
                multipartFile.transferTo(file);
                list.add(dir + "/" + filename);
            }
            result.setCode(successCode);
            result.setData(list);
            result.setMsg(successInfo);
        } catch (IllegalStateException e) {
            log.error(String.format("%s", e));
        } catch (IOException e) {
            log.error(String.format("%s", e));
        }
        log.info("返回内容为：" + result.toString());
        return result;
    }

    /**
     * 上传后返回结果
     * @author xxzkid
     */
    @Data
    public static class UploadResult {

        /** @see UploadResult#MSG_0 */
        public static final String CODE_0 = "0";
        /** @see UploadResult#MSG_1 UploadResult#MSG_2 */
        public static final String CODE_1 = "-1";

        /** 成功 success */
        public static final String MSG_0 = "成功";
        /** 上传文件格式不正确 upload pattern not true */
        public static final String MSG_1 = "上传文件格式不正确";
        /** 上传文件大小超过了%sKB upload file size gt %s KB */
        public static final String MSG_2 = "上传文件大小超过了%sKB";

        /** 空路径 */
        public static final String EMPTY_URL = "";

        private String code; // 返回码
        private String msg; // 描述
        private List<String> data = new ArrayList<>(); // 上传文件路径的集合

    }

}
