package com.example.tdd_security_second_demo.share.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NameValueList {

    @Builder.Default
    private List<NameValue> nameValues = new ArrayList<>();


    public void add(NameValue nameValue) {
        nameValues.add(nameValue);
    }

}
