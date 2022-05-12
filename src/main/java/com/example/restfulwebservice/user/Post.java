package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // User : Post -> 1:N , Main : Sub > Parent : child
    // 지연 로딩방식 LAZY 사용자 Entity 조회할때 필요한 사용자 데이터를 가져옴
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
