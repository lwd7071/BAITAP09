package vn.iotstar.service;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
@Service public class CloudinaryService {
    @Value("${cloudinary.cloud-name:}") String cloud; @Value("${cloudinary.api-key:}") String key; @Value("${cloudinary.api-secret:}") String secret;
    public String upload(MultipartFile file) { try { if(file==null||file.isEmpty()) return null; String type=file.getContentType(); if(type==null||!type.startsWith("image/")) throw new IllegalArgumentException("Chỉ chấp nhận file ảnh"); if(file.getSize()>5*1024*1024) throw new IllegalArgumentException("Ảnh không được vượt quá 5MB"); if(cloud.isBlank()||key.isBlank()||secret.isBlank()) throw new IllegalStateException("Chưa cấu hình Cloudinary"); Cloudinary client=new Cloudinary(Map.of("cloud_name",cloud,"api_key",key,"api_secret",secret)); Map result=client.uploader().upload(file.getBytes(),Map.of("folder","vidu1/products","resource_type","image")); return String.valueOf(result.get("secure_url")); } catch(java.io.IOException e){throw new IllegalStateException("Không thể upload ảnh",e);} }
}
