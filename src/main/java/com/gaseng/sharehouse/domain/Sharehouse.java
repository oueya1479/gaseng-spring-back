package com.gaseng.sharehouse.domain;

import com.gaseng.file.domain.File;
import com.gaseng.global.common.BaseTimeEntity;
import com.gaseng.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sharehouse")
public class Sharehouse extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false)
    private Member member;

    private String shrTitle;

    private String shrDescription;

    private String shrAddress;

    private String shrAddressDetail;

    private String shrLatitude;

    private String shrLongitude;

    private String shrPoster;

    private SharehouseStatus shrStatus;

    @Builder
    public Sharehouse(Member member,String shrTitle,String shrDescription,String shrAddress,String shrAddressDetail,String shrLatitude,String shrLongitude,String shrPoster,SharehouseStatus shrStatus) {
        this.member = member;
        this.shrTitle = shrTitle;
        this.shrDescription = shrDescription;
        this.shrAddress=shrAddress;
        this.shrAddressDetail= shrAddressDetail;
        this.shrLatitude= shrLatitude;
        this.shrLongitude= shrLongitude;
        this.shrPoster=shrPoster;
        this.shrStatus=shrStatus;
    }

    public void update(Sharehouse updateSharehouse) {
        this.shrTitle = updateSharehouse.shrTitle;
        this.shrDescription = updateSharehouse.shrDescription;
        this.shrAddress=updateSharehouse.shrAddress;
        this.shrAddressDetail= updateSharehouse.shrAddressDetail;
        this.shrLatitude= updateSharehouse.shrLatitude;
        this.shrLongitude= updateSharehouse.shrLongitude;
        this.shrPoster=updateSharehouse.shrPoster;
        this.shrStatus=updateSharehouse.shrStatus;
    }

    /*public void addFile(File file) {
        this.file.add(file);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(file.getBoard() != this)
            // 파일 저장
            file.setBoard(this);
    }*/
}
