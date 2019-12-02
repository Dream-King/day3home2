package com.qf.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 长风 on 2019/11/20.
 */
@Table(name = "items")
@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Column(nullable = false)
    private String name;
    //    @Column(nullable = false)
    private Float price;
    private String detail;
    //    @Column(nullable = false)
    private String pic;
    //    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;


}
