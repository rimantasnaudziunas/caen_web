package lt.dsprojektai.objects;

import org.springframework.web.multipart.MultipartFile;

public class EpcFile {
    private MultipartFile file;

    public EpcFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
