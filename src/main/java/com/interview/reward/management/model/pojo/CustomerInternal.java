package com.interview.reward.management.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerInternal {

    private Long id;
    private String name;
    private String surname;
    private Long bonusPoints;

}
