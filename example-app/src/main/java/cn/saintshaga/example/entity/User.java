package cn.saintshaga.example.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements IUser, Serializable {

    private String userId;
    private String name;
}
