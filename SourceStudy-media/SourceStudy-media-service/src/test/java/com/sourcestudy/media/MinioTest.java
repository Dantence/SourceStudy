package com.sourcestudy.media;/**
 * @author Dantence
 * @description:
 * @date 2023/2/13
 */

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.media
 * @className: MinioTest
 * @author: Dantence
 * @description: 测试minio上传删除查询文件
 * @date: 2023/2/13 21:55
 * @version: 1.0
 */
public class MinioTest {
    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://101.43.234.193:9000")
                    .credentials("yyw", "qazntiop4301nlf")
                    .build();


    //上传文件
    @Test
    public void upload() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("test1").build());
            //检查testbucket桶是否创建，没有创建自动创建
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("test1").build());
            } else {
                System.out.println("Bucket 'testbucket' already exists.");
            }
            //上传1.mp4
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("test1")
                            .object("test1.jpg")

                            .filename("D:\\myfile\\其他文件\\tmp\\profile.jpg")
                            .build());
//            //上传1.avi,上传到avi子目录
//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                            .bucket("testbucket")
//                            .object("avi/1.avi")
//                            .filename("D:\\develop\\upload\\1.avi")
//                            .build());
            System.out.println("上传成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }

}
