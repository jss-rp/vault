package com.jss.vault.domain;

public record Credential(
    String title,
    String username,
    String password,
    String notes,
    String url) {
}
