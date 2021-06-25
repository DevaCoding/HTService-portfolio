package com.springboot2.htservice.domain.photo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "photo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Photo extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_ID")
    private TrainerBoard trainerBoard;

    @Column(name = "P_ORIGPHOTONAME", columnDefinition = "TEXT")
    private String origPhotoName;

    @Column(name = "P_PHOTOPATH", columnDefinition = "TEXT")
    private String photoPath;
    
    @Builder
    public Photo(Long id, TrainerBoard trainerBoard , String origPhotoName, String photoPath) {
        this.id = id;
        this.trainerBoard = trainerBoard;
        this.origPhotoName = origPhotoName;
        this.photoPath = photoPath;
    }

}
