package com.example.its.doamin.issue;

import lombok.AllArgsConstructor;
import lombok.Data;

// lombokを使ってコンストラクタとgetter/setterを自動生成
@AllArgsConstructor
@Data
public class IssueEntity {
    private long id;
    private String summary;
    private String description;

}
