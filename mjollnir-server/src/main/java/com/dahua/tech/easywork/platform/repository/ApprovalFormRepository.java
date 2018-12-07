package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.ApprovalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public interface ApprovalFormRepository extends JpaRepository<ApprovalForm,Long> {
    boolean existsByBusinessId(long businessId);
}
