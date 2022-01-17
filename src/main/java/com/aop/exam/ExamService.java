package com.aop.exam;

import com.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
