package com.springboot2.htservice.domain.trainerboard;

import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class TrainerBoard extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "U_ID")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "T_ID")
    private List<Photo> photoList;

    @Column(name = "T_THUMBNAILNAME", columnDefinition = "TEXT")
    private String thumbnailName;

    @Column(name = "T_THUMBNAILPATH", columnDefinition = "TEXT")
    private String thumbnailPath;

    @Column(name = "T_INTRODUCE")
    private String introduce;

    @Column(name = "T_PROGRAM", columnDefinition = "TEXT")
    private String program;

    @Column(name = "T_PRICE10")
    private String price10;
    @Column(name = "T_PRICE20")
    private String price20;
    @Column(name = "T_PRICE30")
    private String price30;

    @Column(name = "T_PERMIT")
    private boolean permit;

    @Column(name = "T_ADDRESS")
    private String address;

    @Column(name = "category")
    private String category;



    @Builder
    public TrainerBoard(Long id, User user, String thumbnailName, String thumbnailPath, String introduce, String program,
                        String price10, String price20, String price30, String address, String category) {
        this.id = id;
        this.user = user;
        this.thumbnailName = thumbnailName;
        this.thumbnailPath = thumbnailPath;
        this.introduce = introduce;
        this.program = program;
        this.price10 = price10;
        this.price20 = price20;
        this.price30 = price30;
        this.permit = false;
        this.address = address;
        this.category = category;

    }

    public void update(String thumbnailName, String thumbnailPath, String introduce, String program,
                       String price10, String price20, String price30, String address, String category) {
        this.thumbnailName = thumbnailName;
        this.thumbnailPath = thumbnailPath;
        this.introduce = introduce;
        this.program = program;
        this.price10 = price10;
        this.price20 = price20;
        this.price30 = price30;
        this.address = address;
        this.category = category;


    }

    public void permitUpdate() {
        this.permit = true;
    }

   /* public void permitDown() { this.permit = false; }*/

}

