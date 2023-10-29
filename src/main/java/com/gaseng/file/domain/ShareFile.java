package com.gaseng.file.domain;

import com.gaseng.sharehouse.domain.Sharehouse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "share_file")
public class ShareFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shrFileId;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shr_id", nullable = false)
    private Sharehouse sharehouse;
    
    @Builder
    public ShareFile(File file, Sharehouse sharehouse) {
    	this.file = file;
    	this.sharehouse = sharehouse;
    }
}
