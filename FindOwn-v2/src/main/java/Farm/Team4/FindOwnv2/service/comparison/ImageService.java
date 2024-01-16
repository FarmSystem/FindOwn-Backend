package Farm.Team4.FindOwnv2.service.comparison;

import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${cloud.aws.s3.bucket}")
    private String s3Bucket;
    public String uploadImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty())
            throw new FindOwnException(CustomErrorCode.NO_IMAGE);
        log.info("이미지 체크 성공");
        return connectToS3(image.getInputStream());
    }
    public String uploadImageFromUrl(String downloadUrl) throws IOException {
        log.info("이미지 받기 성공");
        return connectToS3(extractImage(downloadUrl));
    }
    private String connectToS3(InputStream inputStream){
        ObjectMetadata metadata= new ObjectMetadata();
        metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        String fileName = generateFileName();
        log.info("s3 전달 직전");
        amazonS3Client.putObject(new PutObjectRequest(s3Bucket, fileName,  inputStream, metadata));
        log.info("s3 전달 성공");
        return amazonS3Client.getUrl(s3Bucket, fileName).toString();
    }
    private InputStream extractImage(String imageUrl) throws IOException {
        // Accept 헤더 설정
        RequestCallback requestCallback = request -> {
            request.getHeaders().setAccept(List.of(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG));
        };

        // 응답 추출기 설정
        ResponseExtractor<BufferedImage> responseExtractor = response -> {
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new FindOwnException(CustomErrorCode.CANT_GET_IMAGE);
            }
            InputStream inputStream = response.getBody();
            return ImageIO.read(inputStream);
        };

        // 이미지 다운로드 및 추출
        BufferedImage bufferedImage = restTemplate.execute(imageUrl, HttpMethod.GET, requestCallback, responseExtractor);
        log.info("이미지 추출 성공");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        log.info("이미지 -> inputStream 변환 성공");
        return new ByteArrayInputStream(baos.toByteArray());
    }
    private String generateFileName(){
        return "findown-" + UUID.randomUUID() + ".png";
    }
}
