package com.example.cryptoanalyzer.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    FILE_ALREADY_EXISTS_ERROR("Named file already exists");

    private final String description;
}
