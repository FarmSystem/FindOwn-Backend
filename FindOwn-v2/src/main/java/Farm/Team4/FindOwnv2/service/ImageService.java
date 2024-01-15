package Farm.Team4.FindOwnv2.service;

import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String s3Bucket;
    public String uploadImage(MultipartFile image){
        if (image == null || image.isEmpty())
            throw new FindOwnException(CustomErrorCode.NO_IMAGE);
        String fileName = generateFileName();
        try{
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(image.getSize());
            amazonS3Client.putObject(new PutObjectRequest(s3Bucket, fileName, image.getInputStream(), metadata));
            return amazonS3Client.getUrl(s3Bucket, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateFileName(){
        return "findown-" + UUID.randomUUID() + ".png";
    }
}
