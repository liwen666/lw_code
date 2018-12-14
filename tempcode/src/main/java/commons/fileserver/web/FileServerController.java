
package commons.fileserver.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.longtu.businessframework.common.fudsclient.impl.FUDSFile;
import com.longtu.businessframework.common.fudsclient.service.IFUDSService;
import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.fileserver.po.AttachmentPO;
import com.tjhq.commons.fileserver.service.IFileServerCtrlService;

/**
 * 文件服务器控制类
 * @author YAN
 * @CreateDate 2014-3-3
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/commons/fileserver")
public class FileServerController {
    @Resource
    IFileServerCtrlService fileServerCtrlService;

    @RequestMapping(value = "uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, String storeFilePath,
                           String limitSuffix, String limitSize) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // UserDTO user = SecureUtil.getCurrentUser();
        JSONObject resultObj = new JSONObject();
        if (!ServletFileUpload.isMultipartContent(request)) {
            resultObj.put("success", false);
            out.print(resultObj.toString());
            out.close();
            return;
        }
        try {
            Map<String, String> limitSuffixMap = new HashMap<String, String>();
            // 如果有尾缀限制
            if (limitSuffix != null && !limitSuffix.trim().equals("")) {
                String[] limitSuffixList = limitSuffix.split(",");
                for (int i = 0; i < limitSuffixList.length; i++) {
                    limitSuffixMap.put(limitSuffixList[i].toLowerCase(), "");
                }
            }
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("files[]");

            for (MultipartFile fileItem : fileList) {
                if (fileItem.isEmpty()) {
                    continue;
                }
                // 如果有大小限制,单位M
                if (limitSize != null && !limitSize.trim().equals("")) {
                    // 取得文件尾缀
                    if (Integer.parseInt(limitSize) * 1024 * 1024 < Integer.valueOf(String.valueOf(fileItem
                            .getSize()))) {
                        throw new Exception("上传的单个文件大小不能超过" + limitSize + "M。");
                    }
                }
                String fileName = fileItem.getOriginalFilename();
                if (fileName.indexOf("\\") != -1) {
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                }
                // 加入文件名单引号限制，防止引起内外网同步出错
                if (fileName.indexOf("'") != -1) {
                    throw new Exception("上传的文件名称中不能包含单引号。");
                }
                // 如果有尾缀限制
                if (limitSuffix != null && !limitSuffix.trim().equals("")) {
                    // 取得文件尾缀
                    int lastPointIndex = fileName.lastIndexOf(".");
                    if (lastPointIndex == -1) {
                        throw new Exception("上传的文件必须为" + limitSuffix + "格式的文件。");
                    }
                    String fileSuffix = fileName.substring(lastPointIndex + 1);
                    if (!limitSuffixMap.containsKey(fileSuffix.toLowerCase())) {
                        throw new Exception("上传的文件必须为" + limitSuffix + "格式的文件。");
                    }
                }
                AttachmentPO attachmentPO = new AttachmentPO();
                attachmentPO.setAttachmentFileName(fileName);
                attachmentPO.setAttachmentName(fileName);
                attachmentPO.setAttachmentSize(Integer.valueOf(String.valueOf(fileItem.getSize())));

                String uploadTime = getCurrentTime();
                if (storeFilePath != null && !storeFilePath.trim().equals("")) {

                    storeFilePath = storeFilePath.replaceAll(",", "/") + "/" + uploadTime;
                } else {
                    storeFilePath = uploadTime;
                }
                attachmentPO.setAttachmentPath(storeFilePath);

                // 文件服务器调用
                IFUDSService service = (IFUDSService) ServiceFactory.getBean("fuds.service");

                ByteArrayInputStream uploadFileStream = new ByteArrayInputStream(fileItem.getBytes());
                /*
                 * FileOutputStream fos = new FileOutputStream("D:\\FUDS\\AAA.AAA"); byte[] b = new byte[1024]; int n = 0; while((n=uploadFileStream.read(b))!=-1){ fos.write(b, 0, n); } fos.close();
                 */
                // 调用接口进行上传，返回上传成功文件的guid
                String attachmentID = service.uploadFile(uploadFileStream, attachmentPO.getAttachmentFileName(),
                        "fuds");
                uploadFileStream.close();
                // String finYear = "2015";//request.getParameter("finYear");
                // // 将登录信息存储到上下文中.
                // LoginContext loginContext = new LoginContext();
                // loginContext.setFinYear(finYear);
                // //loginContext.setDbName(dbName);
                // LoginContextHolder.setLoginContext(loginContext);
                // fileServerCtrlService.getTestTableData();
                attachmentPO.setAttachmentID(attachmentID);
                fileServerCtrlService.insertAttachment(attachmentPO);
                resultObj.put("attachmentID", attachmentID);
                resultObj.put("attachmentName", fileName);
            }
            resultObj.put("success", true);
            out.print(resultObj.toString());
        } catch (SizeLimitExceededException se) {
            resultObj.put("success", false);
            resultObj.put("erroInfo", "上传的单个文件大小不能超过" + limitSize + "M。");
            out.print(resultObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resultObj.put("success", false);
            resultObj.put("erroInfo", e.getMessage());
            out.print(resultObj.toString());
        } finally {
            out.flush();
            out.close();
        }
    }

    @RequestMapping(value = "downloadFile")
    public void downloadFile(String attachmentID, HttpServletResponse response) throws Exception {
        response.reset();
        response.setContentType("APPLICATION/OCTET-STREAM;");
        ServletOutputStream out = response.getOutputStream();
        try {
            AttachmentPO attachmentPO = fileServerCtrlService.getAttachmentByID(attachmentID);
            if (attachmentPO == null) {
                throw new Exception("不能找到附件。");
            }

            // 文件服务器调用
            IFUDSService service = (IFUDSService) ServiceFactory.getBean("fuds.service");

            // // 通过FTP服务器取到附件
            // FTPServerInfo ftpServerInfo = ftpCtrlImpl.getFTPServerInfo();
            // FTPUtils.connectFtpServer(ftpClient,
            // ftpServerInfo.getServerName(),
            // Integer.parseInt(ftpServerInfo.getServerPort()),
            // ftpServerInfo.getServerUser(),
            // ftpServerInfo.getServerPassword());
            //
            // InputStream fileStream = FTPUtils.downloadFtpFile(ftpClient,
            // attachmentInfo.getAttachmentPath(), fileName);
            FUDSFile file = service.downloadFiles("fuds", attachmentID);
            if (file.getFilesize() == 0) {
                throw new Exception("无法从文件服务器获取有效数据!");
            }
            
            String fileName = new String(attachmentPO.getAttachmentFileName().getBytes("GBK"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.addHeader("Content-Length", "" + attachmentPO.getAttachmentSize());
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Expires", "0");

            InputStream fileStream = file.getInputStream();
            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = fileStream.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            fileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("附件下载出错：" + e.toString());
        } finally {
            out.flush();
            out.close();
        }
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
}
