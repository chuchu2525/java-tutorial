package com.example.library.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("REGULAR")
@NoArgsConstructor
public class RegularMember extends Member {
    public RegularMember(String name) {
        super(name);
    }

    @Override
    public int getMaxBorrowingLimit() {
        return 3;
    }
}
