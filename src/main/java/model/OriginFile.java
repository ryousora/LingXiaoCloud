package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 真实原始文件信息
 */
@Entity
@Table(name = "origin_file")
public class OriginFile implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer originFileId;

    @Column(name = "file_md5", unique = true, nullable = false)
    private String fileMd5;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_url", unique = true, nullable = false)
    private String fileUrl;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    public OriginFile() {
    }

    public OriginFile(String fileMd5, String fileType) {
        this.fileMd5 = fileMd5;
        this.fileType = fileType;
    }

    public Integer getOriginFileId() {
        return originFileId;
    }

    public void setOriginFileId(Integer originFileId) {
        this.originFileId = originFileId;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5 == null ? null : fileMd5.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OriginFile{" +
                "originFileId=" + originFileId +
                ", fileMd5='" + fileMd5 + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}