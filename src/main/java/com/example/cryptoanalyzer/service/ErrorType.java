package com.example.cryptoanalyzer.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    DATA_IS_NULL("Input data empty or null"),
    FILE_EXISTS("File already exists");

    private final String description;
}
