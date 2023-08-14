package com.zlc.springboot.model;


import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Setter
@Getter
public class User implements Serializable {
    private String name;
    private String age;
}
