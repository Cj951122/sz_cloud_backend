package com.lunz.fin.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author al
 * @date 2019/5/27 19:31
 * @description
 */
@Slf4j
public class OSSClientUtil {

//    @Value("${oss.endpoint}")
    private String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
//    @Value("${oss.accessKeyId}")
    private String accessKeyId = "LTAIkeT4Wx14yHj0";
//    @Value("${oss.accessKeySecret}")
    private String accessKeySecret = "COrhbosk7PMgbroAGdaY59ymzmZBhX";
//    @Value("${oss.bucketName}")
    private String bucketName = "algebra-test";

    private String checkpointFile = "./uploadFile.ucp";


    public static OSSClient getOssClient(String endpoint,String accessKeyId,String accessKeySecret) {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }


    /**
     * 断点续上传
     * @param filePath 上传文件的路径
     */
    public void breakpointUpload(String filePath, String objectName) {

        OSSClient ossClient = getOssClient(endpoint, accessKeyId, accessKeySecret);

        try{
            ObjectMetadata meta = new ObjectMetadata();
            // 指定上传的内容类型。
            meta.setContentType("text/plain");

            // 通过UploadFileRequest设置多个参数。
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,objectName);

            // 通过UploadFileRequest设置单个参数。
            // 设置存储空间名称。
            //uploadFileRequest.setBucketName("<yourBucketName>");
            // 设置文件名称。
            //uploadFileRequest.setKey("<yourObjectName>");
            // 指定上传的本地文件。
            uploadFileRequest.setUploadFile(filePath);
            // 指定上传并发线程数，默认为1。
            uploadFileRequest.setTaskNum(5);
            // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
            uploadFileRequest.setPartSize(1 * 1024 * 1024);
            // 开启断点续传，默认关闭。
            uploadFileRequest.setEnableCheckpoint(true);
            // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
            uploadFileRequest.setCheckpointFile(checkpointFile);
            // 文件的元数据。
            uploadFileRequest.setObjectMetadata(meta);
            // 设置上传成功回调，参数为Callback类型。
//        uploadFileRequest.setCallback("<yourCallbackEvent>");

            // 断点续传上传。
            ossClient.uploadFile(uploadFileRequest);
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message: " + oe.getErrorMessage());
            log.info("Error Code:       " + oe.getErrorCode());
            log.info("Request ID:      " + oe.getRequestId());
            log.info("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 断点续下载
     * @param filePath
     */
    public void breakpointDownload(String filePath, String objectName) {

        // 创建OSSClient实例。
        OSSClient ossClient = getOssClient(endpoint, accessKeyId, accessKeySecret);
        try{
            // 下载请求，10个任务并发下载，启动断点续传。
            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
            downloadFileRequest.setDownloadFile(filePath);
            downloadFileRequest.setPartSize(1 * 1024 * 1024);
            downloadFileRequest.setTaskNum(10);
            downloadFileRequest.setEnableCheckpoint(true);
            downloadFileRequest.setCheckpointFile(checkpointFile);

            // 下载文件。
            DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
            // 下载成功时，会返回文件元信息。
            ObjectMetadata objectMetadata = downloadRes.getObjectMetadata();
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message: " + oe.getErrorCode());
            log.info("Error Code:       " + oe.getErrorCode());
            log.info("Request ID:      " + oe.getRequestId());
            log.info("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

    }

    public void flowDownload(String objectName){
        // 创建OSSClient实例。
        OSSClient ossClient = getOssClient(endpoint, accessKeyId, accessKeySecret);
        BufferedReader reader = null;
        try{
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);

            // 读取文件内容。
            log.info("Object content:");
            reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
//            while (true) {
//                String line = reader.readLine();
//                if (line == null) break;
//                log.info("\n" + line);
//            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }



    public  void test() throws IOException {

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        OSSObject ossObject = ossClient.getObject(bucketName, "test.xml");

        BufferedInputStream in=new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream("E:\\Oss-test\\test.xml"));
        int len=-1;
        byte[] b=new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b,0,len);
        }

        in.close();
        out.close();
        ossClient.shutdown();

    }

    public static void main(String[] args) throws IOException {

        OSSClientUtil util = new OSSClientUtil();
        util.test();

    }


}
