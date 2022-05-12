package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonFilter("UserInfo")
@NoArgsConstructor
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    // 현재회원가입 날짜는 미래데이터 사용 X  과거데이터 O
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요")
    private Date joinDate;

    @ApiModelProperty(notes = "사용자 비밀번호를 입력해 주세요")
    private String password;

    @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요")
    private String ssn;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
