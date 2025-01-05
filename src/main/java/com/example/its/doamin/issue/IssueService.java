package com.example.its.doamin.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueEntity> findAll() {
        return issueRepository.findAll();
    }

    @Transactional
    public void create(String summary, String description) {
        issueRepository.insert(summary, description);
        // わざと例外を発生させてロールバックさせることを確認するためのコード
//        try {
//            throw new IllegalAccessException("NG");
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }

    public IssueEntity findById(Long issueId) {
        return issueRepository.findById(issueId);
    }

    @Transactional
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }

    @Transactional
    public void deleteAllIssues() {
        issueRepository.deleteAll();
        issueRepository.resetAutoIncrement();
    }

    @Transactional
    public void updateIssue(Long issueId, String summary, String description) {
        issueRepository.update(issueId, summary, description);
    }
}
