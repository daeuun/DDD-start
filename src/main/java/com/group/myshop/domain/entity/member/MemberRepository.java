package com.group.myshop.domain.entity.member;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member findById();
}
