package com.example.blogex.domain.S3.Cotroller;


import com.example.blogex.common.dto.ResultResponse;
import com.example.blogex.domain.S3.Service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.blogex.common.Code.ResultCode.BASED_SUCCESS;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping(value = "/upload",consumes = {"multipart/form-data"})
    public ResponseEntity<ResultResponse> upload(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,s3Service.upload(file)));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/object")
    public ResponseEntity<ResultResponse> delete(@RequestParam("key") String key) {
        s3Service.delete(key);
        return ResponseEntity.ok(ResultResponse.of(BASED_SUCCESS,null));
    }

}
